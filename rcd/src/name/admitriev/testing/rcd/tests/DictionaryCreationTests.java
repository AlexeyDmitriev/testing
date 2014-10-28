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

public class DictionaryCreationTests extends TestBase {

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
		assertEquals(app.getDictionaryHelper().tryAddDictionary(groupName, data), data.getExpectedResult());
		if(data.getExpectedResult().equals("OK"))
			assertEquals(app.getDictionaryHelper().getDictionariesList(groupName), Collections.singletonList(data), "After adding there should be exactly one element");
		else
			assertEquals(app.getDictionaryHelper().getDictionariesList(groupName), Collections.singletonList(data), "If dictionary was not added group should be still empty");
	}
}
