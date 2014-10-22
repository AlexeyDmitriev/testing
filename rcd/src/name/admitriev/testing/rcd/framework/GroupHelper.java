package name.admitriev.testing.rcd.framework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Collections;

import static org.testng.Assert.assertEquals;

public class GroupHelper extends BaseHelper {

	public GroupHelper(AppManager manager) {
		super(manager);
	}

	public void removeGroupIfExists(String group) {
		app.getNavigationHelper().gotoGroupsPage();
		for (WebElement element : driver.findElements(by("group-element"))) {
			String name = element.findElement(by("group-name")).getText();
			if (name.equals(group)) {
				new Actions(driver)
						.moveToElement(element.findElement(by("trash-button")))
						.click();
				return;
			}
		}
	}

	public void prepareEmptyGroup(String group) {
		removeGroupIfExists(group);
		addGroup(group);
		assertEquals(app.getDictionaryHelper().getDictionariesList(group), Collections.emptyList(), "After group creation it should not have any inner dictionaries");
	}

	public void addGroup(String group) {
		click(by("group-add"));
		type(by("group-add-name-field"), group);
		click(by("group-add-submit"));
	}


}
