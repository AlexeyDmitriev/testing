package name.admitriev.testing.rcd.framework;

import name.admitriev.testing.rcd.data.DictionaryData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DictionaryHelper extends BaseHelper {
	public DictionaryHelper(AppManager manager) {
		super(manager);
	}

	public String tryAddDictionary(String group, DictionaryData dictionaryData) {
		app.getNavigationHelper().gotoGroupPage(group);
		click(by("dictionary-add"));
		type(by("dictionary-add-name-field"), dictionaryData.getName());
		type(by("dictionary-add-description-field"), dictionaryData.getDescription());
		click(by("dictionary-add-submit"));
		try {
			return getText(by("dictionary-add-error"));
		}
		catch (NoSuchElementException ignored) {
			return "OK";
		}
	}

	public void addDictionary(String group, DictionaryData dictionaryData) {
		assertEquals(tryAddDictionary(group, dictionaryData), "OK");
	}

	public List<DictionaryData> getDictionariesList(String group) {
		app.getNavigationHelper().gotoGroupPage(group);
		List<DictionaryData> result = new ArrayList<>();
		for (WebElement element : driver.findElements(by("dictionary-element"))) {
			if(true)throw new AssertionError(element.getText());
			DictionaryData dictionaryData = new DictionaryData()
					.withName(element.findElement(by("dictionary-name")).getText())
					.withDescription(element.findElement(by("dictionary-description")).getText());
			result.add(dictionaryData);
		}
		return result;

	}

	public void removeDictionary(String group, String name) {
		List<DictionaryData> dictionaryDataList = getDictionariesList(group);
		for(int i = 0; i < dictionaryDataList.size(); ++i) {
			if(dictionaryDataList.get(i).getName().equals(name)) {
				removeDictionaryByIndex(group, i);
			}
		}
	}

	private void removeDictionaryByIndex(String group, int n) {
		++n; // css satrt indicies from 1
		app.getNavigationHelper().gotoGroupPage(group);
		while (true) {
			try {
				new Actions(driver).moveToElement(driver.findElement(by("dictionary-delete-row", n))).perform();
				click(by("dictionary-delete-symbol", n));
				click(by("dictionary-delete-button"));
				return;
			}
			catch (StaleElementReferenceException e) {
				continue;
			}
		}
	}


	public String editDictionaryName(String groupName, DictionaryData data, String newName) {
		int index = getDictionariesList(groupName).indexOf(data);
		return editDictionaryNameByIndex(groupName, index, newName);
	}

	private String editDictionaryNameByIndex(String groupName, int index, String newName) {
		++index;
		while (true) {
			try {
				WebElement element = driver.findElement(by("dictionary-edit-row", index));
				Actions action = new Actions(driver);
				new Actions(driver).moveToElement(element).perform();
				click(by("dictionary-edit-symbol", index));

				WebElement field = driver.findElement(by("dictionary-edit-name-field"));
				field.clear();
				field.sendKeys(newName);
				field.sendKeys(Keys.ENTER);
				break;
			}
			catch (StaleElementReferenceException e) {
				continue;
			}
		}

		if (isElementPresent(by("alerts-container"))) {
			return getText(by("alerts-container"));
		} else {
			return "";
		}
	}
}
