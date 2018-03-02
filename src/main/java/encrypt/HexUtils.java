package main.java.encrypt;

/**
 * Created by sykmile on 2018/3/2.
 */
public class HexUtils
{
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String ENPTY_STR = "";

    public static String bytesToHexStr(byte[] byteArr)
    {
        if ((null == byteArr) || (byteArr.length < 1)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte t : byteArr)
        {
            if ((t & 0xF0) == 0) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(t & 0xFF));
        }
        return sb.toString();
    }

    public static byte[] hexStrToBytes(String hexStr)
    {
        if ((null == hexStr) || (hexStr.length() < 1)) {
            return null;
        }
        int byteLen = hexStr.length() / 2;
        byte[] result = new byte[byteLen];
        char[] hexChar = hexStr.toCharArray();
        for (int i = 0; i < byteLen; i++) {
            result[i] = ((byte)(Character.digit(hexChar[(i * 2)], 16) << 4 | Character.digit(hexChar[(i * 2 + 1)], 16)));
        }
        return result;
    }

    public static String strToHexStr(String inputStr)
    {
        try
        {
            return bytesToHexStr(inputStr.getBytes("UTF-8"));
        }
        catch (Exception e) {}
        return "";
    }

    public static String hexStrToStr(String inputHexStr)
    {
        try
        {
            return new String(hexStrToBytes(inputHexStr), "UTF-8");
        }
        catch (Exception e) {}
        return "";
    }
}
