package name.admitriev.wikitests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import static org.testng.Assert.assertEquals;

public class RegistrationTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> data() throws IOException {
		return wrapForDataProvider(new RegDataLoader().loadDataFromCsvFile(new File("regtests.csv")));
	}

	@Test(dataProvider = "data")
	public void testLogin(RegistrationData regData) throws Exception {
		assertEquals(doRegister(regData), "Ошибка создания учётной записи\n" + regData.getExpectedResult(), "test login failed: ");
	}

	private String doRegister(RegistrationData regData) {
		driver.get("http://ru.wikipedia.org/wiki/Заглавная_страница");
		driver.findElement(By.linkText("Создать учётную запись")).click();
		driver.findElement(By.id("wpName2")).sendKeys(regData.getLogin());
		driver.findElement(By.id("wpPassword2")).sendKeys(regData.getPassword());
		driver.findElement(By.id("wpRetype")).sendKeys(regData.getPasswordConfirmation());
		driver.findElement(By.id("wpEmail")).sendKeys(regData.getEmail());

		driver.findElement(By.id("wpCreateaccount")).click();

		try {
			return driver.findElement(By.className("errorbox")).getText();
		} catch (Exception e) {
			WebElement element = driver.findElement(By.id("wpCreateaccount"));
			if (element == null) {
				return "SUCCESS";
			} else {
				return null;
			}

		}
	}

}
