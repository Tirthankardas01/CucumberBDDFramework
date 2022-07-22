package utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ElementUtils {
	
	//private static String filePath = "./screenshots/test.png";
	
	public static void takeScreenshot(WebDriver driver, String filePath) {
		TakesScreenshot scrShot = (TakesScreenshot)driver;
		File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
