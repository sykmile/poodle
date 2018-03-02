package main.java.encrypt;

/**
 * Created by sykmile on 2018/3/2.
 */
public class BusinessException
        extends RuntimeException
{
    private String errorCode;

    public BusinessException(String errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String message)
    {
        super(message);
        this.errorCode = "0001";
    }

    public String getErrorCode()
    {
        return this.errorCode;
    }
}