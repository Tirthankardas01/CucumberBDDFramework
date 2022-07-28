package Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	
	private static WebDriver driver;
	//public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	public static WebDriver init_driver() {
		System.setProperty("webdriver.chrome.driver", ".//ChromeDriver//chromedriver.exe");
		driver = new ChromeDriver();
		return driver;		
	}
}
