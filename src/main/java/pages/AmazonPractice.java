package pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AmazonPractice {
	
	private WebDriver driver;
	public String amazonUrl = "https://www.amazon.in/";
	
	@FindBy(id = "searchDropdownBox")
	private WebElement searchDropdownBox;
	
	@FindBy(id = "nav-search-submit-button")
	private WebElement searchOption;
	
	@FindBy(xpath ="//span[text()='Men']/ancestor::ul/child::li")
	private List<WebElement> sidePanelOptions;
	
	public AmazonPractice(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitleAmazon() {
		return driver.getTitle();
	}
	
	public void selectWatches(String option) {
		Select sel = new Select(searchDropdownBox);
		sel.selectByVisibleText(option);
	}
	
	public void clickOnSearch() {
		searchOption.click();
	}
	
	public List<String> checkSideOptions() {
		List<String> sidePanelnames = new ArrayList<>();
		for (WebElement e : sidePanelOptions) {
			String text = e.getText();
			sidePanelnames.add(text);
		}
		return sidePanelnames;
	}
	

}
