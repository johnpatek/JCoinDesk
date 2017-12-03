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

    private HttpURLConnection mConnection;
    private BufferedReader mResponseReader;
    private final StringBuilder mStringBuilder;

    private URL mURL;

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
     * @param url a URL representing a valid CoinDesk API call
     * @throws coindesk.CoinDeskException
     */
    protected void getBPI(URL url) throws CoinDeskException {
        String line;
        try {
            mConnection = (HttpURLConnection) url.openConnection();
            mConnection.setRequestMethod("GET");//TODO: alternative
            mConnection.setReadTimeout(0);//TODO: alternative
            mResponseReader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
            while ((line = mResponseReader.readLine()) != null) {//TODO: remove duct tape
                mStringBuilder.append(line).append(NEWLINE);
            }
        } catch (IOException ioException) {
            throw new CoinDeskException(ioException.getMessage(), CoinDeskException.IO_ERROR);
        }
    }

    /**
     *
     * @return A String representing the most recent JSON response for this
     * object
     */
    public String getLastResponse() {
        return mStringBuilder.toString();
    }

    protected URL getURL() {
        return mURL;
    }

    protected void setURL(String urlString) throws CoinDeskException {
        if (mURL == null || !urlString.equals(mURL.toString())) {
            try {
                mURL = new URL(urlString);
            } catch (MalformedURLException malformedURLException) {
                throw new CoinDeskException(malformedURLException.getMessage(), CoinDeskException.URL_ERROR);
            }
        }
    }

    @Override
    public String toString() {
        return getLastResponse();
    }
}
