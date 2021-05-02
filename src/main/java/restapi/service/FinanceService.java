package restapi.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;

@Singleton
public class FinanceService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(FinanceService.class);

    public String getFinancialData(String provider, String stockIndex) throws IOException {
        JSONObject jo = new JSONObject();
        if (provider.equals("yahoo")) {
            LOGGER.info(stockIndex);
            Stock stock = YahooFinance.get(stockIndex);
            BigDecimal price = stock.getQuote().getPrice();
            BigDecimal change = stock.getQuote().getChangeInPercent();
            BigDecimal peg = stock.getStats().getPeg();
            BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();
            jo.put("stock", stock);
            jo.put("price", price);
            jo.put("change", change);
            jo.put("peg", peg);
            jo.put("dividend", dividend);
        } else jo.put("message", "ony yahoo provider implemented");
        return jo.toString();
    }
}
