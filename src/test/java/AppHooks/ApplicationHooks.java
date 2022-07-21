package AppHooks;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import Factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ApplicationHooks {
	
	private WebDriver driver; 
	
	@Before() public void setUp() {
		driver = DriverFactory.init_driver();
		System.out.println("Browser Launched");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		System.out.println("Application launched");
	}
	

	@After() public void tearDown() {
		driver.close();
		driver.quit();
		driver=null;
		System.out.println("Browser closed");
	}
}
