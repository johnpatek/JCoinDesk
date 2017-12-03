package coindesk.historical;

import coindesk.AbstractData;
import coindesk.CoinDeskException;
import java.time.LocalDate;

/**
 *
 * @author John Patek
 */
public class HistoricalData extends AbstractData {

    private static final String HISTORICAL_ENDPOINT = COIN_DESK_URL_BASE + "/historical/close.json";
    
    public static final String USD_INDEX = "USD";

    public static final String CNY_INDEX = "CNY";

    public HistoricalData() {
        super();
    }

    public HistoricalData(int bufferSize) {
        super(bufferSize);
    }

    @Override
    public void getBPI() throws CoinDeskException{
        getBPI(HISTORICAL_ENDPOINT);
    }

    public void getBPI(String currency) throws CoinDeskException{
        getBPI(currency, USD_INDEX);
    }

    public void getBPI(String currency, String index) throws CoinDeskException{
        getBPI(String.format("%s?currency=%s&index=%s", HISTORICAL_ENDPOINT, currency, index));
    }

    public void getBPI(LocalDate start, LocalDate end) throws CoinDeskException{
        getBPI(start, end, "USD", USD_INDEX);
    }
    
    public void getBPI(LocalDate start, LocalDate end, String currency) throws CoinDeskException{
        getBPI(start, end, currency, USD_INDEX);
    }

    public void getBPI(LocalDate start, LocalDate end, String currency, String index) throws CoinDeskException{
        getBPI(String.format("%s?start=%s&end=%s&currency=%s&index=%s", HISTORICAL_ENDPOINT, start, end, currency, index));
    }

    public void getBPIForYesterday() throws CoinDeskException{
        getBPIForYesterday("USD", USD_INDEX);
    }
    
    public void getBPIForYesterday(String currency) throws CoinDeskException{
        getBPIForYesterday(currency, USD_INDEX);
    }

    public void getBPIForYesterday(String currency, String index) throws CoinDeskException{
        
        mUrl = new URL(String.format("%s?for=yesterday&currency=%s&index=%s", HISTORICAL_ENDPOINT, currency, index));
    }
}
