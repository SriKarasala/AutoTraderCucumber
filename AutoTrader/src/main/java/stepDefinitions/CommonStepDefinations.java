package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import wrappers.GenericWrappers;

public class CommonStepDefinations {
	
	GenericWrappers obj;
	
	@Before
	public void beforeHook() {
		obj = new GenericWrappers();
		obj.startReport();
		obj.loadData();
	}
	
		@After
	public void afterHook() {
		obj.closeAllBrowsers();
		obj.endTest();
		obj.endReport();
	}
	

@Given("Launch of Auto Trader")
public void launch_of_Auto_Trader() {
	   obj.invokeApp("chrome", "https://www.autotrader.com/");    
}

@Given("User Execute Test {string} with description as {string}")
public void user_Execute_Test_with_description_as(String string, String string2) {
	   obj.startTest(string, string2);
}

@Then("check if link {string} is present")
public void check_if_link_is_present(String string) {
obj.getPresenceOfElement(string);
}

@Then("check if Search button {string} is present")
public void check_if_Search_button_is_present(String string) {
	obj.getPresenceOfElement(string);
}

@When("Navigated to the advanced Serach page")
public void navigated_to_the_advanced_Serach_page() throws InterruptedException {
	obj.clickOnButtonorLink("lnk_Browse_By_AdvancedSearch");
	Thread.sleep(1000);
   obj.validateURLFor("advanced");
}

@Then("check if text box {string} is present")
public void check_if_text_box_is_present(String string) {
	obj.getPresenceOfElement(string);
}

@Then("check if label {string} is present")
public void check_if_label_is_present(String string) {
	obj.getPresenceOfElement(string);
}

@Then("check if checkbox {string} is present")
public void check_if_checkbox_is_present(String string) {
	obj.getPresenceOfElement(string);
}


@Then("check if dropdown {string} is present")
public void check_if_dropdown_is_present(String string) {
	obj.getPresenceOfElement(string);
}

@Then("check if search button {string} is present")
public void check_if_search_button_is_present(String string) {
	obj.getPresenceOfElement(string);
}

@When("enter {string} in {string}")
public void enter_in(String string, String string2) {
	obj.enterValueIntoTextField(string2,string);
}

@When("check the checkbox {string}")
public void check_the_checkbox(String string) throws InterruptedException {
obj.clickOnButtonorLink(string);

}

@When("select {string} from drop down {string}")
public void select_from_drop_down(String string, String string2) {
   obj.selctdropdown(string,string2);
}

@When("click button {string}")
public void click_button(String string) throws InterruptedException {
	obj.clickOnButtonorLink(string);
}

@Then("user should be able to see only {string} listings")
public void user_should_be_able_to_see_only_listings(String string) throws InterruptedException {
obj.validateAllListings(string);
}




	
	
}
