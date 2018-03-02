package main.java.encrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sykmile on 2018/3/2.
 */
public class PatternValidateUtils
{
    public static Boolean validateMobile(String mobile)
    {
        String regEx = "^1[0-9]{10}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(mobile);
        return Boolean.valueOf(matcher.matches());
    }

    public static Boolean validateRealNumber(String mobile)
    {
        String regEx = "^[+-]?\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(mobile);
        return Boolean.valueOf(matcher.matches());
    }
}
