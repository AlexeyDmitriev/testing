package name.admitriev.wikitests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestBase {

	protected WebDriver driver;

	@BeforeClass
	public void testInit() {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	public static Iterator<Object[]> wrapForDataProvider(List<?> input) {
		List<Object[]> list = new ArrayList<Object[]>(input.size());
		for (Object login : input) {
			list.add(new Object[]{login});
		}
		return list.iterator();
	}

}

