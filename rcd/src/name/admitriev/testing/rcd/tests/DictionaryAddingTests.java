package name.admitriev.testing.rcd.tests;


import name.admitriev.testing.rcd.data.DictionaryData;
import name.admitriev.testing.rcd.data.DictionaryDataLoader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DictionaryAddingTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> dataProvider() throws IOException {
		return wrapListForDataProvider(data("dictionaries.csv"));
	}

	private List<DictionaryData> data(String filename) throws IOException {
		return new DictionaryDataLoader().loadDataFromCsvFile(filename);
	}

	@Test(dataProvider = "dataProvider")
	public void testAddingSingleDictionary(DictionaryData data) {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareEmptyGroup(groupName);
		testAddingDictionary(data);
	}

	@Test(dataProvider = "dataProvider")
	public void testAddingDictionary1(DictionaryData data) {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareGroup(groupName, 1);
		testAddingDictionary(data);
	}

	@Test(dataProvider = "dataProvider")
	public void testAddingDictionary2(DictionaryData data) {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareGroup(groupName, 2);
		testAddingDictionary(data);
	}

	@Test(dataProvider = "dataProvider")
	public void testAddingDictionary5(DictionaryData data) {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareGroup(groupName, 5);
		testAddingDictionary(data);
	}

	@Test
	public void testDuplicatedNames() {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareEmptyGroup(groupName);
		DictionaryData data = new DictionaryData().withName("1").withExpectedResult("OK");
		testAddingDictionary(data);
		testAddingDictionary(data.withExpectedResult("Dictionary " + data.getName() + " already exist"));
	}

	@Test
	public void testDuplicatedDescriptions() {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareEmptyGroup(groupName);
		DictionaryData data = new DictionaryData().withName("1").withDescription("desc").withExpectedResult("OK");
		testAddingDictionary(data);
		testAddingDictionary(data.withName("2"));
	}

	@Test
	public void AddDictionaryWithLineBreakInDescription() {
		String groupName = app.properties.getProperty("group");
		app.getGroupHelper().prepareEmptyGroup(groupName);
		DictionaryData data = new DictionaryData().withName("1")
		                                          .withDescription("linebreak here\nblahblah")
		                                          .withExpectedResult("OK");

		testAddingDictionary(data);
	}


	public void testAddingDictionary(DictionaryData data) {
		String groupName = app.properties.getProperty("group");
		List<DictionaryData> initialList = app.getDictionaryHelper().getDictionariesList(groupName);

		assertEquals(app.getDictionaryHelper().tryAddDictionary(groupName, data), data.getExpectedResult());

		List<DictionaryData> result = app.getDictionaryHelper().getDictionariesList(groupName);
		Collections.sort(result);
		Collections.sort(initialList);
		if (data.getExpectedResult().equals("OK")) {
			initialList.add(data);
			assertEquals(
					result,
					initialList,
					"After adding there should has 1 element more: these element"
			);
		}
		else {
			assertEquals(
					result,
					initialList,
					"If dictionary was not added group should be still the same"
			);
		}
	}

}
