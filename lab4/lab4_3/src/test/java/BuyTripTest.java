import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BuyTripTest {

    WebDriver driver;
    Main main;

    @Before
    public void setUp(){
        driver = WebDriverManager.chromedriver().create();
        main = new Main(driver);
    }

    @Test
    public void buyTrip(){
        main.selectFlight("Boston", "London");
        assertThat(main.getSuccessMessage()).isEqualTo("Success");
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
