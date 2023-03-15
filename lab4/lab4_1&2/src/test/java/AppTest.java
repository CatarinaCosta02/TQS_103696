import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class AppTest {
    static final Logger log = getLogger(lookup().lookupClass());

    private ChromeDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
