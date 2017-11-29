package coindesk;

import coindesk.historical.HistoricalData;
import coindesk.realtime.RealTimeData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Provides much of the underlying logic and error handling required to access
 * the CoinDesk API via HTTP. If a developer wishes to create their own
 * implementation using Java HTTP, this class provides a useful framework.
 *
 * @see RealTimeData for an implementation that retrieves the current price
 * @see HistoricalData for an implementation that retrieves past closing prices
 * @author John Patek
 */
public abstract class AbstractData implements CoinDeskData {

    public static final String NEWLINE = System.getProperty("line.separator");

    public static final int DEFAULT_BUFFER_SIZE = 1024;

    private URL mUrl;
    private HttpURLConnection mConnection;
    private BufferedReader mResponseReader;
    private final StringBuilder mStringBuilder;

    /**
     * Creates a CoinDesk client object that uses a default-sized string
     * builder.
     */
    protected AbstractData() {
        this(DEFAULT_BUFFER_SIZE);
    }

    /**
     * Creates a CoinDesk client object that uses a string builder of specified
     * size.
     *
     * @param initialCapacity string builder size
     */
    protected AbstractData(int initialCapacity) {
        mStringBuilder = new StringBuilder(initialCapacity);
    }

    /**
     * @param endpoint a String representing a valid CoinDesk API call
     * @return the resulting BPI data from the given endpoint, in JSON format.
     * Returns an error message otherwise.
     */
    public String getBPI(String endpoint) {
        String line;
        try {
            mUrl = new URL(endpoint);
            mConnection = (HttpURLConnection) mUrl.openConnection();
            mConnection.setRequestMethod("GET");//TODO: alternative
            mConnection.setReadTimeout(0);//TODO: alternative
            mResponseReader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
            while ((line = mResponseReader.readLine()) != null) {//TODO: remove duct tape
                mStringBuilder.append(line).append(NEWLINE);
            }
        } catch (MalformedURLException malformedURLException) {
            return malformedURLException.getMessage();
        } catch (IOException ioException) {
            return ioException.getMessage();
        }
        return mStringBuilder.toString();
    }

    /**
     *
     * @return A String representing the most recent JSON response for this
     * object
     */
    public String getLastResponse() {
        return mStringBuilder.toString();
    }
}
