package name.admitriev.testing.dadata.framework;


import name.admitriev.testing.dadata.data.PhoneData;
import org.openqa.selenium.WebElement;

public class PhoneHelper extends BaseHelper {
	public PhoneHelper(AppManager app) {
		super(app);
	}

	public PhoneData enterPhone(String phone) {
		driver.get(app.baseURL);
		type(by("phone-field"), phone);
		click(by("phone-button"));

		PhoneData result = new PhoneData().withPhone(phone);

		WebElement element = driver.findElement(by("phone-result"));
		for (WebElement row : element.findElements(by("phone-row"))) {
			String l = row.findElement(by("phone-row-l")).getText();
			String r = row.findElement(by("phone-row-r")).getText();
			if(l.equals("Мобильный") || l.equals("Городской") || l.equals("Рабочий")) {
				result.withType(l);
			}
			if(l.equals("Оператор"))
				result.withOperator(r);
			if(l.equals("Регион"))
				result.withRegion(r);
		}


		return result;
	}
}
