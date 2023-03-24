package blaze;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlazeSteps {
    private WebDriver driver;
    @Given("I navigate to {string}")
    public void i_navigate_to(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }


    @When("I selected the departure city {string} and the destination city {string}")
    public void iSelecteDestination(String departure, String destination) {
        driver.findElement(By.name("fromPort")).sendKeys(departure);
        driver.findElement(By.name("toPort")).sendKeys(destination);
    }


    @And("Click find flights")
    public void clickFindFlights() {
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    }


    @And("I choose the flight {int}")
    public void iChooseTheFlight(int flight) {
        driver.findElement(By.cssSelector("input[value='Choose This Flight']")).click();
    }


    @And("I fill the form")
    public void iFillTheForm() {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Catarina");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("Rua Santa Maria");
        driver.findElement(By.id("city")).sendKeys("Aveiro");
        driver.findElement(By.id("state")).sendKeys("Aveiro");
        driver.findElement(By.id("zipCode")).sendKeys("123123123");
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("961111111");
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys("2022");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("Catarina");

        driver.findElement(By.cssSelector(".btn-primary")).click();
    }


    @And("I should see the message of confirmation")
    public void iShouldSeeTheMessageOfConfirmation() {
        assertEquals("Thank you for your purchase today!", driver.findElement(By.cssSelector("h1")).getText());
    }

    @Then("Check purchase information in the database")
    public void checkPurchaseInformationInTheDatabase() {
        driver.quit();
    }
}
