package name.admitriev.testing.rcd.framework;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class NavigationHelper extends BaseHelper {

	public NavigationHelper(AppManager manager) {
		super(manager);
	}

	public void gotoLoginPage() {
		driver.manage().deleteAllCookies();
		driver.get(app.baseURL);
	}

	public void gotoGroupsPage() {
		app.getLoginHelper().performCorrectLogin();
		driver.get(app.baseURL + "/admin/");
	}

	public void gotoGroupPage(String group) {

		gotoGroupsPage();
		while(true) {
			try {

				for (WebElement element : driver.findElements(by("group-element"))) {
					String name = element.findElement(by("group-name")).getText();
					if (name.equals(group)) {
						element.findElement(by("group-link")).click();
						return;
					}
				}
				throw new AssertionError("No group " + group);
			}
			catch (StaleElementReferenceException e) {

			}
		}
	}
}
