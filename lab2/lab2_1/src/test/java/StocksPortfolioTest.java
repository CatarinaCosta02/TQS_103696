import org.example.IStockmarketService;
import org.example.Stock;
import org.example.StocksPortfolio;
import org.hamcrest.number.IsCloseTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {

    @Mock
    private IStockmarketService market;

    @InjectMocks
    private StocksPortfolio portfolio;

    @BeforeEach
    void setUp() {
        portfolio = new StocksPortfolio(market);
    }


    @Test
    public void getTotalValueAnnot(){
        when(market.lookUpPrice("EBAY")).thenReturn(4.0);
        when(market.lookUpPrice("MSFT")).thenReturn(1.5);
        when(market.lookUpPrice("NOTUSED")).thenReturn(1.5);

        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("MSFT", 4));
        portfolio.addStock(new Stock("NOTUSED", 4));

        double total = 4.0 * 2 + 1.5 * 4 + 1.5 * 4;
        assertThat(portfolio.getTotalValue(), IsCloseTo.closeTo(total,0));

        Mockito.verify(market, times(3)).lookUpPrice(anyString());
        //Mockito.verify(market, Mockito.times(2)).getPrice(Mockito.anyString());

    }
}
