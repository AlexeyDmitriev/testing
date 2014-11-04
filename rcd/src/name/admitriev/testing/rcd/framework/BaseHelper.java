package name.admitriev.testing.rcd.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;


public class BaseHelper {
	protected AppManager app;
	protected WebDriver driver;

	public BaseHelper(AppManager app) {
		this.app = app;
		this.driver = app.driver;
	}

	protected void click(By by) {
		waitFor(by);
		driver.findElement(by).click();
	}

	public void waitFor(By by) {

	}

	protected void type(By by, String input) {
		waitFor(by);
		driver.findElement(by).sendKeys(input);
	}

	protected String getText(By by) {
		waitFor(by);
		return driver.findElement(by).getText();
	}

	protected boolean isElementPresent(By by) {
		waitFor(by);
		try {
			driver.findElement(by);
			return true;
		}
		catch (NoSuchElementException e) {
			return false;
		}
	}

	protected By by(String propertyName, Object... args) {
		String locator = app.properties.getProperty(propertyName);
		return byLocator(String.format(locator, args));
	}

	protected By by(String propertyName) {
		String locator = app.properties.getProperty(propertyName);
		return byLocator(locator);
	}

	private By byLocator(String locator) {
		String[] s = locator.split("=", 2);
		if (s.length != 2) {
			throw new RuntimeException("Wrong locator");
		}
		switch (s[0]) {
			case "id":
				return By.id(s[1]);
			case "css":
				return By.cssSelector(s[1]);
			case "xpath":
				return By.xpath(s[1]);
			case "tag":
				return By.tagName(s[1]);
			case "class":
				return By.className(s[1]);
			default:
				throw new RuntimeException("Wrong locator type");
		}

	}


}
