package coindesk;

/**
 * A basic interface for accessing price data on the CoinDesk API. Only 2
 * specifications/recommendations:
 * <ol>
 * <li>Data is returned as JSON</li>
 * <li>Data can be retrieved for specified currency using ISO 4217
 * codes</li>
 * </ol>
 * How the data is handled or processed after it is returned is beyond the scope
 * of this interface.
 *
 *
 * @see AbstractData for an abstract framework that provides data via Java HTTP
 * tools.
 * @author John Patek
 */
public interface CoinDeskData {

    /**
     * in an effort to promote code cleanliness and reuse, the URL prefix is
     * provided
     */
    String COIN_DESK_URL_BASE = "https://api.coindesk.com/v1/bpi";

    /**
     *
     * @return the CoinDesk price data formatted as a JSON
     */
    String getPrice();

    /**
     *
     * @param currencyCode 3 letter ISO 4217 currency code
     * @return the CoinDesk price data for the specified currency
     */
    String getPrice(String currencyCode);
}
