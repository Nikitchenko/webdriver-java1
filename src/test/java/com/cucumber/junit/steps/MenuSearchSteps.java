package com.cucumber.junit.steps;

import com.cucumber.junit.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class MenuSearchSteps {

    private HomePage homePage = new HomePage();

    @When("the user opens Bookdepisitory site")
    public void userOpensBookdepisitorySite() {
        homePage.openBookdepositoryWebsite();
    }

    @When("the user looks at the Main Header")
    public void userLookAtMainHeader() {
    }

    @Then("the user sees {string} which is input field")
    public void userSeesMenuSearchThatInputField(String menuSearch) {
    }

    @Then("the {string} has a correct placeholder {string}")
    public void menuSearchHasCorrectPlaceholder(String menuSearch, String placeholder) {
        assertEquals( "Not correct placeholder", homePage.getMenuSearch().getAttribute("placeholder"));
    }
}
