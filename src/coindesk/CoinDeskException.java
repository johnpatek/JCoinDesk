package coindesk;

/**
 *
 * @author John Patek
 */
public class CoinDeskException extends Exception{
    public static final int IO_ERROR = 0;
    public static final int URL_ERROR = 1; 
    private final int mErrorCode;
    
    
    public CoinDeskException(String message,int errorCode)
    {
        super(message);
        mErrorCode = errorCode;
    }
    
    public int getErrorCode()
    {
        return mErrorCode;
    }
}
