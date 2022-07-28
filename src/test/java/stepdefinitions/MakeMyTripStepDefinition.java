package stepdefinitions;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import Factory.DriverFactory;
import configReader.ConfigReader;
import io.cucumber.java.en.*;
import pages.MMTHomePage;

public class MakeMyTripStepDefinition {
	
	private WebDriver driver;
	private Properties prop;
	private MMTHomePage mmt;
	
	@Given("browser is open and application is launched")
	public void browser_is_open_and_application_is_launched() {
		driver = DriverFactory.init_driver();
		driver.manage().window().maximize();
		try {
			prop = ConfigReader.readProperties();
		} catch (Exception e) {
			System.out.println("Properties File Issue");
		}
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@When("user enters city name and selects checkin date and selects checkout date")
	public void user_enters_city_name_and_selects_checkin_date_and_selects_checkout_date() throws Exception {
	   mmt = new MMTHomePage(driver);
	   mmt.selectCity(prop.getProperty("cityName"));
	   mmt.selectCheckinDateandCheckoutDate();   
	}

	@When("user clicks on apply and clicks on search button")
	public void user_clicks_on_apply_and_clicks_on_search_button() {
		mmt.clickOnApplyAndSearch();
	}

	@Then("List of hotels is displayed for the city")
	public void list_of_hotels_is_displayed_for_the_city() {
		mmt.validateSearchPage();
		driver.quit();
		driver=null;
	}

}
