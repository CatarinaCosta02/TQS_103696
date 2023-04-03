package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Main {

    WebDriver driver;

    @FindBy(name = "fromPort")
    WebElement fromPort;

    @FindBy(name = "toPort")
    WebElement toPort;

    @FindBy(css = ".btn-primary")
    WebElement findFlightsButton;

    @FindBy(css = "tr:nth-child(2) .btn")
    WebElement selectFlight;

    @FindBy(css = ".btn-primary")
    WebElement purchaseFlight;

    @FindBy(tagName = "h1")
    WebElement successMessage;


    //Constructor
    public Main(WebDriver driver){
        this.driver=driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
        driver.get("http://blazedemo.com/");
    }

    public void selectFlight(String from, String to){

        Select fromPortSelect = new Select(fromPort);
        fromPortSelect.selectByValue(from);

        Select toPortSelect = new Select(toPort);
        toPortSelect.selectByValue(to);

        findFlightsButton.click();
        selectFlight.click();
        purchaseFlight.click();
    }

    public String getSuccessMessage(){
        return successMessage.getText().contains("Thank you for your purchase today!") ? "Success" : "Failure";
    }


}