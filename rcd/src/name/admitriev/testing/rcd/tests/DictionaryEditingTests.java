package name.admitriev.testing.rcd.tests;


import name.admitriev.testing.rcd.data.DictionaryData;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class DictionaryEditingTests extends TestBase {
	@Test
	public void testEditName() {
		String groupName = app.properties.getProperty("group");

		app.getGroupHelper().prepareGroup(groupName, 5);

		List<DictionaryData> initial = app.getDictionaryHelper().getDictionariesList(groupName);
		int index = 3;
		String newName = "edited " + initial.get(index).getName();
		app.getDictionaryHelper().editDictionaryName(groupName, initial.get(index), newName);
		initial.get(index).withName(newName);
		List<DictionaryData> result = app.getDictionaryHelper().getDictionariesList(groupName);

		assertEquals(initial, result, "Changing name:");
	}

	@Test
	public void testEditToEmpty() {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareGroup(groupName, 5);

		List<DictionaryData> initial = app.getDictionaryHelper().getDictionariesList(groupName);
		int index = 3;

		app.getDictionaryHelper().editDictionaryName(groupName, initial.get(index), "");

		List<DictionaryData> result = app.getDictionaryHelper().getDictionariesList(groupName);

		assertEquals(initial, result, "Changing name to empty should not change anything");

	}

	@Test
	public void testEditToExisting() {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareGroup(groupName, 5);

		List<DictionaryData> initial = app.getDictionaryHelper().getDictionariesList(groupName);
		int index = 3;

		app.getDictionaryHelper().editDictionaryName(groupName, initial.get(index), initial.get(1).getName());

		List<DictionaryData> result = app.getDictionaryHelper().getDictionariesList(groupName);

		assertEquals(initial, result, "Changing name to existing should not change anything");
	}

}