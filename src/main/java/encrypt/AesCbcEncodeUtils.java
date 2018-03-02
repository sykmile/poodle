package main.java.encrypt;

/**
 * Created by sykmile on 2018/3/2.
 */
import java.io.PrintStream;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesCbcEncodeUtils
{
    private static final String KEY_AES = "AES";
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String EMPTY_STR = "";
    public static final byte[] KEY_MOBILE = { 18, 6, 19, 80, 114, 31, -3, -82, -30, 20, 68, -27, 12, -124, 121, 66 };
    public static final byte[] KEY_USER_NAME = { 11, 16, 20, 18, -11, -95, -55, 120, -1, 42, 78, 96, -42, -74, 23, -29 };

    public static String defaultEncrypt(String inputStr, byte[] key)
    {
        try
        {
            if (StringUtils.isEmpty(inputStr).booleanValue()) {
                return "";
            }
            return HexUtils.bytesToHexStr(encrypt(inputStr.getBytes("UTF-8"), key));
        }
        catch (Exception e)
        {
            throw new BusinessException("AES加密异常");
        }
    }

    public static String defaultDecrypt(String inputStr, byte[] key)
    {
        try
        {
            if (StringUtils.isEmpty(inputStr).booleanValue()) {
                return "";
            }
            return new String(decrypt(HexUtils.hexStrToBytes(inputStr), key), "UTF-8");
        }
        catch (Exception e)
        {
            throw new BusinessException("AES解密异常");
        }
    }

    private static byte[] encrypt(byte[] data, byte[] key)
    {
        byte[] bytes = null;
        try
        {
            Key k = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, k);
            bytes = cipher.doFinal(data);
        }
        catch (Exception e)
        {
            throw new BusinessException("AES加密异常");
        }
        return bytes;
    }

    private static byte[] decrypt(byte[] data, byte[] key)
    {
        byte[] bytes = null;
        try
        {
            Key k = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, k);
            bytes = cipher.doFinal(data);
        }
        catch (Exception e)
        {
            throw new BusinessException("AES解密异常");
        }
        return bytes;
    }

    private static void init(String seed)
            throws Exception
    {
        SecureRandom secure = null;
        if (StringUtils.isNotEmpty(seed).booleanValue()) {
            secure = new SecureRandom(seed.getBytes("UTF-8"));
        } else {
            secure = new SecureRandom();
        }
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(secure);
        SecretKey key = generator.generateKey();
        byte[] keyArr = key.getEncoded();

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < keyArr.length; i++)
        {
            sb.append(keyArr[i]);
            if (i != keyArr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("};");
        System.out.print(sb.toString());
    }
}
