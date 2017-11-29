package coindesk.historical;

import coindesk.AbstractData;
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
    public String getPrice() {
        return getBPI(HISTORICAL_ENDPOINT);
    }

    @Override
    public String getPrice(String currency) {
        return getPrice(currency, USD_INDEX);
    }

    public String getPrice(String currency, String index) {
        return getBPI(String.format("%s?currency=%s&index=%s", HISTORICAL_ENDPOINT, currency, index));
    }

    public String getPrice(LocalDate start, LocalDate end) {
        return getPrice(start, end, "USD", USD_INDEX);
    }
    
    public String getPrice(LocalDate start, LocalDate end, String currency) {
        return getPrice(start, end, currency, USD_INDEX);
    }

    public String getPrice(LocalDate start, LocalDate end, String currency, String index) {
        return getBPI(String.format("%s?start=%s&end=%s&currency=%s&index=%s", HISTORICAL_ENDPOINT, start, end, currency, index));
    }

    public String getPriceForYesterday() {
        return getPriceForYesterday("USD", USD_INDEX);
    }
    
    public String getPriceForYesterday(String currency) {
        return getPriceForYesterday(currency, USD_INDEX);
    }

    public String getPriceForYesterday(String currency, String index) {
        return getBPI(String.format("%s?for=yesterday&currency=%s&index=%s", HISTORICAL_ENDPOINT, currency, index));
    }
}
