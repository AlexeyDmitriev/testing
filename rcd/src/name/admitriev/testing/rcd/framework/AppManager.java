package name.admitriev.testing.rcd.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class AppManager {

	public WebDriver driver;
	public String baseURL;
	public Properties properties;

	private GroupHelper groupHelper = null;
	private LoginHelper loginHelper;
	private NavigationHelper navigationHelper;
	private DictionaryHelper dictionaryHelper;

	public AppManager(Properties properties) {
		this.properties = properties;
		if (Objects.equals(properties.getProperty("browser"), "firefox"))
			driver = new FirefoxDriver();
		else if (Objects.equals(properties.getProperty("browser"), "chrome"))
			driver = new ChromeDriver();
		else
			throw new RuntimeException("Unknown browser");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		baseURL = properties.getProperty("baseURL");
		driver.get(baseURL);
	}

	public GroupHelper getGroupHelper() {
		if (groupHelper == null) {
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}



	public LoginHelper getLoginHelper() {
		if (loginHelper == null) {
			loginHelper = new LoginHelper(this);
		}
		return loginHelper;
	}

	public NavigationHelper getNavigationHelper() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}

	public DictionaryHelper getDictionaryHelper() {
		if (dictionaryHelper == null) {
			dictionaryHelper = new DictionaryHelper(this);
		}
		return dictionaryHelper;
	}

	public void stop() {
		driver.close();
		driver.quit();
	}
}
