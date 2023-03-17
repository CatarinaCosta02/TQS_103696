package io.cucumber.skeleton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        Belly belly = new Belly();
        belly.eat(cukes);
    }

    @Then("my belly should growl")
    public void myBellyShouldGrowl() {

    }

    @When("I wait {int} hour")
    public void iWaitHour(int arg0) {

    }
}
