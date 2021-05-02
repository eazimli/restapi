package restapi.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restapi.service.FinanceService;

import javax.inject.Inject;
import java.io.IOException;

@Controller("/finance")
public class FinanceController {

    @Inject
    private FinanceService financeService;

    protected static final Logger LOGGER = LoggerFactory.getLogger(FinanceController.class);

    @Get(produces = MediaType.APPLICATION_JSON)
    public String getFinancialData(@QueryValue("provider") String provider,
                                   @QueryValue("stock_index") String stockIndex) throws IOException {

        LOGGER.info(provider);
        LOGGER.info(stockIndex);

        return financeService.getFinancialData(provider,stockIndex);
    }
}
