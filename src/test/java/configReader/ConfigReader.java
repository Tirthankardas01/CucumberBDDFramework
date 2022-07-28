package configReader;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	public static Properties prop;
	
	public static Properties readProperties() throws Exception {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/config/Config.properties");
		prop.load(ip);
		return prop;
	}

}
