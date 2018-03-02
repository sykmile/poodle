package main.java.encrypt;

/**
 * Created by sykmile on 2018/3/2.
 */
public class StringUtils
{
    public static Boolean isEmpty(String inputStr)
    {
        if ((null == inputStr) || ("".equals(inputStr))) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(false);
    }

    public static Boolean isNotEmpty(String inputStr)
    {
        if ((null == inputStr) || ("".equals(inputStr))) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(true);
    }
}
