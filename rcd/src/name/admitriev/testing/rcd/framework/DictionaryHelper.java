package name.admitriev.testing.rcd.framework;

import name.admitriev.testing.rcd.data.DictionaryData;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

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
			if(isElementPresent(by("dictionary-add-name-field")))
				return "";
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
}
