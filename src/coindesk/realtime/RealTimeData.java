package coindesk.realtime;

import coindesk.AbstractData;
import coindesk.CoinDeskException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author John Patek
 */
public class RealTimeData extends AbstractData {

    private static final String REAL_TIME_ENDPOINT = COIN_DESK_URL_BASE + "/currentprice.json";

    /**
     *
     * @throws coindesk.CoinDeskException
     * @see AbstractData
     */
    public RealTimeData() throws CoinDeskException {
        super();
        try {
            mURL = new URL(REAL_TIME_ENDPOINT);
        } catch (MalformedURLException malformedURLException) {
            throw new CoinDeskException(malformedURLException.getMessage(), CoinDeskException.URL_ERROR);
        }
    }

    @Override
    public void getBPI() throws CoinDeskException {
        if (mURL.toString().equals(REAL_TIME_ENDPOINT)) {
            try {

                mURL = new URL(REAL_TIME_ENDPOINT);
            } catch (MalformedURLException malformedURLException) {
                throw new CoinDeskException(malformedURLException.getMessage(), CoinDeskException.URL_ERROR);
            }
            getBPI(mURL);
        }
    }

    public void getBPI(String currencyCode) throws CoinDeskException {
        try {
            mURL = new URL(REAL_TIME_ENDPOINT.replace(".json", String.format("/%s.json", currencyCode)));
        } catch (MalformedURLException malformedURLException) {
            throw new CoinDeskException(malformedURLException.getMessage(), CoinDeskException.URL_ERROR);
        }
    }
}
