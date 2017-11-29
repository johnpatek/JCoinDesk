package coindesk.realtime;

import coindesk.AbstractData;

/**
 *
 * @author John Patek
 */
public class RealTimeData extends AbstractData{
    private static final String REAL_TIME_ENDPOINT = COIN_DESK_URL_BASE + "/currentprice.json";
    
    /**
     * 
     *@see AbstractData
     */
    public RealTimeData()
    {
        super();
    }
    
    
    @Override
    public String getPrice()
    {
        return getBPI(REAL_TIME_ENDPOINT);
    }
    
    @Override
    public String getPrice(String currencyCode)
    {
        return getBPI(REAL_TIME_ENDPOINT.replace(".json", String.format("/%s.json", currencyCode)));
    }
}
