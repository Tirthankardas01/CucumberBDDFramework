package stepdefinitions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import Factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pages.AmazonPractice;

public class AmazonStepDefinition {
	
	private WebDriver driver = null;
	private AmazonPractice aP;
	private List<String> sideOp;
	
	@Before public void setUp() {
		driver = DriverFactory.init_driver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		aP = new AmazonPractice(driver);
	}
		
	@Given("user is on amazon main page")
	public void user_is_on_amazon_main_page() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Main page loaded");
	}

	@Given("page title is {string}")
	public void page_title_is_be(String title) {
		Assert.assertEquals(title, aP.getTitleAmazon());
		System.out.println("Page title matched");
	}

	@When("user clicks on {string} in All dropdown")
	public void user_clicks_on_in_all_dropdown(String string) {
	    aP.selectWatches(string);
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Option selected is :" + string);
	}

	@When("user clicks on search option")
	public void user_clicks_on_search_option() {
		aP.clickOnSearch();
		System.out.println("Search option clicked");
	}

	@Then("watches option should be displayed with all options in the left panel")
	public void watches_option_should_be_displayed_with_all_options_in_the_left_panel() {
		sideOp = new ArrayList<>();
		sideOp.add("Watches");
		sideOp.add("Men");
		sideOp.add("Women");
		sideOp.add("Boys");
		sideOp.add("Girls");
		sideOp.add("Accessories");
		
		Assert.assertEquals(sideOp, aP.checkSideOptions());
		System.out.println("Side options checked and verified");
	}
	
	@After public void tearDown() {
		driver.close();
		driver.quit();
		driver=null;
		System.out.println("Browser closed");
	}

}
