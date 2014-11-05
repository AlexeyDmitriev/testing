package name.admitriev.testing.dadata.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class AppManager {

	public WebDriver driver;
	public String baseURL;
	public Properties properties;

	private PhoneHelper phoneHelper;

	public AppManager(Properties properties) {
		this.properties = properties;
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		baseURL = properties.getProperty("baseURL");
		driver.get(baseURL);
	}

	public PhoneHelper getPhoneHelper() {
		if(phoneHelper == null)
			phoneHelper = new PhoneHelper(this);
		return phoneHelper;
	}

	public void stop() {
		driver.close();
		driver.quit();
	}
}
