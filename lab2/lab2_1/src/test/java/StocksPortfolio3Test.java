import org.example.IStockmarketService;
import org.example.Stock;
import org.example.StocksPortfolio;
import org.hamcrest.number.IsCloseTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolio3Test {
    // 1. Prepare a mock to substitute the remote service (@Mock annotation)
    // 2. Create an instance of the subject under test (SuT) and use the mock to set
    //the (remote) service instance.
    //StocksPortfolio myStocks = new StocksPortfolio(mockSTockMarket);
    // 3. Load the mock with the proper expectations (when...thenReturn)
    //4. Execute the test (use the service in the SuT)
    //5. Verify the result (assert) and the use of the mock (verify)

    @Mock
    IStockmarketService market;

    @Mock
    StocksPortfolio portfolio;


    @BeforeEach
    void setUp() {
        
        portfolio = new StocksPortfolio(market);
    }


    @Test
    public void getTotalValueAnnot(){
        when(market.lookUpPrice("EBAY")).thenReturn(4.0);
        when(market.lookUpPrice("MSFT")).thenReturn(1.5);
        when(market.lookUpPrice("NOTUSED")).thenReturn(1.5);

        //4. Execute the test (use the service in the SuT)
        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        portfolio.addStock(new Stock("NOTUSED", 4));

        // 5. Verify the result (assert) and the use of the mock (verify)
        double total = 4.0 * 2 + 1.5 * 4 + 1.5 * 4;
        // assertThat(1.2, closeTo(1, 0.5)); exemplo de compração com hamcrest em inteiros
        assertThat(portfolio.getTotalValue(), IsCloseTo.closeTo(total,0));

        //verify(market, times(2)).lookUpPrice(anyString());
        Mockito.verify(portfolio, Mockito.times(2)).getPrice(Mockito.anyString());

    }


    /*private IStockmarketService verify(IStockmarketService market, VerificationMode times) {
        return null;
    }*/
}
