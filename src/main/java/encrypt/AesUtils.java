package main.java.encrypt;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AesUtils extends Frame
{
    private static final String CHECKBOX_TYPE_ENCRYPT = "编码";
    private static final String CHECKBOX_TYPE_DECRYPT = "解码";
    private static final String CHECKBOX_DATA_TYPE_MOBILE = "手机";
    private static final String CHECKBOX_DATA_TYPE_NAME = "姓名";
    private static final String BTN_TYPE_OPERATE = "编解码";
    private static final String BTN_TYPE_CLEAR = "重置";
    Panel panel = new Panel();
    Checkbox encryptCheckbox = new Checkbox("编码", true);
    Checkbox decryptCheckbox = new Checkbox("解码", true);
    CheckboxGroup checkboxGroup = new CheckboxGroup();
    Checkbox mobileCheckbox = new Checkbox("手机", true);
    Checkbox nameCheckbox = new Checkbox("姓名", true);
    CheckboxGroup dataTypeGroup = new CheckboxGroup();
    Button operateBtn = new Button("编解码");
    Button clearBtn = new Button("重置");
    Label inputLabel = new Label("输入：");
    Label outputLabel = new Label("输出：");
    TextArea inputTextArea = new TextArea();
    TextArea outputTextArea = new TextArea();

    public AesUtils()
    {
        super("AES编解码工具");
        setVisible(true);
        setLocation(500, 300);

        this.panel.setLayout(null);
        this.panel.setSize(450, 280);
        this.panel.setBackground(new Color(241, 241, 241));

        this.encryptCheckbox.setCheckboxGroup(this.checkboxGroup);
        this.decryptCheckbox.setCheckboxGroup(this.checkboxGroup);
        this.decryptCheckbox.setState(true);

        this.mobileCheckbox.setCheckboxGroup(this.dataTypeGroup);
        this.nameCheckbox.setCheckboxGroup(this.dataTypeGroup);
        this.mobileCheckbox.setState(true);

        this.inputTextArea.setColumns(100);
        this.inputTextArea.setRows(500);
        this.outputTextArea.setColumns(100);
        this.outputTextArea.setRows(500);
        this.outputTextArea.setEditable(false);

        this.operateBtn.setBackground(new Color(255, 51, 51));
        this.operateBtn.addActionListener(new ButtonListen());
        this.clearBtn.setBackground(new Color(0, 255, 255));
        this.clearBtn.addActionListener(new ButtonListen());

        this.inputLabel.setBounds(0, 0, 80, 30);
        this.inputTextArea.setBounds(0, 30, 450, 80);
        this.decryptCheckbox.setBounds(0, 120, 60, 30);
        this.encryptCheckbox.setBounds(60, 120, 60, 30);
        this.mobileCheckbox.setBounds(0, 150, 60, 30);
        this.nameCheckbox.setBounds(60, 150, 60, 30);
        this.operateBtn.setBounds(120, 150, 80, 30);
        this.clearBtn.setBounds(210, 150, 80, 30);
        this.outputLabel.setBounds(0, 180, 80, 30);
        this.outputTextArea.setBounds(0, 210, 450, 80);

        this.panel.add(this.inputLabel);
        this.panel.add(this.inputTextArea);
        this.panel.add(this.encryptCheckbox);
        this.panel.add(this.decryptCheckbox);
        this.panel.add(this.mobileCheckbox);
        this.panel.add(this.nameCheckbox);
        this.panel.add(this.operateBtn);
        this.panel.add(this.clearBtn);
        this.panel.add(this.outputLabel);
        this.panel.add(this.outputTextArea);

        add(this.panel);
        pack();

        addWindowListener(new WindowMonitor());
    }

    class WindowMonitor
            extends WindowAdapter
    {
        WindowMonitor() {}

        @Override
        public void windowClosing(WindowEvent e)
        {
            AesUtils.this.setVisible(false);
            System.exit(0);
        }
    }

    class ButtonListen
            implements ActionListener
    {
        ButtonListen() {}

        @Override
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String btnType = e.getActionCommand();
                if ("编解码".equals(btnType))
                {
                    AesUtils.this.buttonOperateService();
                }
                else if ("重置".equals(btnType))
                {
                    AesUtils.this.buttonClearService();
                }
                else
                {
                    AesUtils.this.operateException("按钮类型异常");
                    return;
                }
            }
            catch (Exception ee)
            {
                AesUtils.this.operateException(ee.getMessage());
                return;
            }
        }
    }

    private void buttonClearService()
    {
        this.encryptCheckbox.setState(true);
        this.decryptCheckbox.setState(false);
        this.mobileCheckbox.setState(true);
        this.nameCheckbox.setState(false);
        this.inputTextArea.replaceRange("", 0, this.inputTextArea.getText().length());
        this.outputTextArea.replaceRange("", 0, this.outputTextArea.getText().length());
    }

    private void buttonOperateService()
    {
        String inputStr = this.inputTextArea.getText().trim();

        Checkbox selectedDataCheckbox = this.dataTypeGroup.getSelectedCheckbox();
        String dataType = selectedDataCheckbox.getLabel();

        Checkbox selectedOperateCheckbox = this.checkboxGroup.getSelectedCheckbox();
        String operateType = selectedOperateCheckbox.getLabel();

        String outputStr = null;
        if ("编码".equals(operateType))
        {
            if ("手机".equals(dataType))
            {
                if (!PatternValidateUtils.validateMobile(inputStr).booleanValue())
                {
                    operateException("手机号格式异常！");
                    return;
                }
                outputStr = AesCbcEncodeUtils.defaultEncrypt(inputStr, AesCbcEncodeUtils.KEY_MOBILE);
            }
            else
            {
                outputStr = AesCbcEncodeUtils.defaultEncrypt(inputStr, AesCbcEncodeUtils.KEY_USER_NAME);
            }
        }
        else if ("解码".equals(operateType))
        {
            if ("手机".equals(dataType)) {
                outputStr = AesCbcEncodeUtils.defaultDecrypt(inputStr, AesCbcEncodeUtils.KEY_MOBILE);
            } else {
                outputStr = AesCbcEncodeUtils.defaultDecrypt(inputStr, AesCbcEncodeUtils.KEY_USER_NAME);
            }
        }
        else
        {
            operateException("单选框类型异常");
            return;
        }
        this.outputTextArea.setText(outputStr);
    }

    private void operateException(String exceptionInfo)
    {
        if ((null == exceptionInfo) || ("".equals(exceptionInfo))) {
            this.outputTextArea.setText("操作异常！");
        } else {
            this.outputTextArea.setText(exceptionInfo);
        }
    }

    public static void main(String[] args)
    {
        AesUtils at = new AesUtils();
        at.setVisible(true);
    }
}