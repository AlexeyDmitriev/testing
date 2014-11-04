package name.admitriev.testing.rcd.tests;

import name.admitriev.testing.rcd.data.DictionaryData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DictionaryRemovingTests extends TestBase {

	@Test
	public void testRemovingDictionary1() {
		testRemovingEachOf(1);
	}

	@Test
	public void testRemovingDictionary2() {
		testRemovingEachOf(2);
	}

	@Test
	public void testRemovingDictionaryEmpty() {
		testRemovingEachOf(0);
	}

	@Test
	public void testRemovingDictionary5() {
		testRemovingEachOf(5);
	}

	/**
	 * Creates group of size `size` and deletes first dict, then recreates and removes second one, etc
	 */
	public void testRemovingEachOf(int size) {
		for (int index = 0; index < size; ++index) {
			String groupName = app.properties.getProperty("group");
			app.getGroupHelper().prepareGroup(groupName, size);
			List<DictionaryData> initialList = app.getDictionaryHelper().getDictionariesList(groupName);
			testRemovingDictionary(initialList.get(index).getName());
		}
	}

	public void testRemovingDictionary(String name) {
		String groupName = app.properties.getProperty("group");
		List<DictionaryData> initialList = app.getDictionaryHelper().getDictionariesList(groupName);
		List<DictionaryData> expectedList = new ArrayList<>(initialList.size() - 1);
		for (DictionaryData data : initialList) {
			if (!data.getName().equals(name)) {
				expectedList.add(data);
			}
		}

		app.getDictionaryHelper().removeDictionary(groupName, name);

		assertEquals(
				app.getDictionaryHelper().getDictionariesList(groupName),
				expectedList,
				"After deletion dictionary should be deleted from list"
		);

	}
}
