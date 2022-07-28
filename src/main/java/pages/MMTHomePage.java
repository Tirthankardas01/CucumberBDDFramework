package pages;
import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MMTHomePage {
	
	private WebDriver driver;
	private static Properties prop;
	
	@FindBy(id = "city")
	private WebElement cityName;
	
	@FindBy(xpath ="//div[@role='combobox']/child::input")
	private WebElement cityNameTxtBox;
	
	@FindBy(xpath = "(//p[text()='Delhi, India'])[1]")
	private WebElement firstCityItem;
	
	@FindBy(xpath = "((//div[@class='DayPicker-Month'])[2]/div/div)[1]")
	private WebElement dateLabel;
	
	@FindBy(xpath = "//div[@class='DayPicker-NavBar']/span[2]")
	private WebElement nxtMonthBtn;
	
	@FindBy(xpath = "//button[text()='APPLY']")
	private WebElement applyBtn;
	
	@FindBy(xpath = "//button[text()='Search']")
	private WebElement searchBtn;
			
	public MMTHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectCity(String city) throws Exception{
		cityName.click();
		System.out.println("City Field is clicked");
		cityNameTxtBox.sendKeys(city);
		System.out.println("City Name entered from Properties file");
		String citySelection = firstCityItem.getText().strip();
		firstCityItem.click();	
		System.out.println("City selected is:" +citySelection); 
	}
	
	public static String breakMonthAndYear(String s) {
		int dx=s.length()-4;
		StringBuilder str=new StringBuilder(s);
		str.insert(dx, " ");
		return str.toString();	
	}
	
	static Properties readPropMMT() throws Exception {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/config/Config.properties");
		prop.load(ip);
		return prop;
	}
	
	public void selectCheckinDateandCheckoutDate() throws Exception {
		
		prop=readPropMMT();
		//Reading all details from Properties file
		String checkinDate=prop.getProperty("checkinDate");
		String checkinMonth=prop.getProperty("checkinMonth");
		String checkinYear=prop.getProperty("checkinYear");
		String checkoutDate=prop.getProperty("checkoutDate");
		String checkoutMonth=prop.getProperty("checkoutMonth");
		String checkoutYear=prop.getProperty("checkoutYear");
		
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(dateLabel));
		String dateRetrieved=dateLabel.getText();
		System.out.println("Date Retrieved is:" +dateRetrieved);
		
		//Insert space between month & year
		String monthAndYear=breakMonthAndYear(dateRetrieved);
		System.out.println(monthAndYear);

		//Split string into two parts
		String[] strArray=monthAndYear.split(" ");
		
		if (checkinMonth.equalsIgnoreCase("February") && Integer.parseInt(checkinDate)>29) {
			System.out.println("Date does not exist!");
			return;
		}

		//While Loop to navigate to future months
		while(!(strArray[0].equals(checkinMonth) && strArray[1].equals(checkinYear))){
			if(nxtMonthBtn.isDisplayed()) 
				nxtMonthBtn.click();
			dateRetrieved=dateLabel.getText();
			monthAndYear=breakMonthAndYear(dateRetrieved);
			strArray=monthAndYear.split(" ");
			System.out.println(strArray[0]);		
		}
		
		driver.findElement(By.xpath("(//div[text()='" + checkinDate + "'])[2]")).click();
		System.out.println("Checkin Date Selected");
	
	
		Thread.sleep(2000);
		if (checkoutMonth.equalsIgnoreCase("February") && Integer.parseInt(checkoutDate)>29) {
			System.out.println("Date does not exist!");
			return;
		}
		
		if(checkoutDate.equalsIgnoreCase(checkinDate) && checkoutMonth.equalsIgnoreCase(checkinMonth)) {
			System.out.println("Checkout Date can't be same as Checkin Date");
			return;
		}
		
		while(!(strArray[0].equals(checkoutMonth) && strArray[1].equals(checkoutYear))){
			if(nxtMonthBtn.isDisplayed()) 
				nxtMonthBtn.click();
			dateRetrieved=dateLabel.getText();
			monthAndYear=breakMonthAndYear(dateRetrieved);
			strArray=monthAndYear.split(" ");
			System.out.println(strArray[0]);		
		}

		driver.findElement(By.xpath("//div[text()='" + checkoutDate + "' and @aria-disabled='false']")).click();
		
		System.out.println("Checkout Date Selected");
	}
	
	public void clickOnApplyAndSearch() {
		
		 String adult=prop.getProperty("Adults");
		 String children=prop.getProperty("Children");
		 
		 if(Integer.parseInt(adult)>12 || Integer.parseInt(children)>4) {
			 System.out.println("Incorrect Data");
			 return;
		 }
		 
		//span[text()='ADULTS (12y +)']/parent::p/following-sibling::ul[1]/li[text()=]
		driver.findElement(By.xpath("//span[text()='ADULTS (12y +)']/parent::p/following-sibling::ul[1]/li[text()='"+ adult +"']")).click();
		//ul[@class='guestCounter font12 darkText'][2]/li[text()='']
		driver.findElement(By.xpath("//ul[@class='guestCounter font12 darkText'][2]/li[text()='"+ children +"']")).click();
		applyBtn.click();
		searchBtn.click();
	}
	
	public void validateSearchPage() {
		String expectedCity=prop.getProperty("cityName");
		
		boolean flag=driver.findElement(By.xpath("//p[text()='Showing Properties in "+ expectedCity +"']")).isDisplayed();
		if (flag=true) {
			System.out.println("Search Results are correct");
		}
		
	}
}
