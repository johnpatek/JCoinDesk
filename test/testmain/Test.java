package testmain;

import coindesk.CoinDeskData;
import coindesk.CoinDeskException;
import coindesk.historical.HistoricalData;
import coindesk.realtime.RealTimeData;

/**
 *
 * @author John Patek
 */
public class Test {

    public static void main(String args[]) {
        try {
            CoinDeskData realTimeData = new RealTimeData();
            realTimeData.getBPI();
            System.out.println(realTimeData.getLastResponse());
        } catch (CoinDeskException coinDeskException) {
            coinDeskException.printStackTrace(System.err);
        }
    }
}
