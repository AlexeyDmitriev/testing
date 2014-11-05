package name.admitriev.testing.dadata.tests;


import name.admitriev.testing.dadata.data.PhoneData;
import name.admitriev.testing.dadata.data.PhoneDataLoader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class PhoneTests extends TestBase {

	@DataProvider
	public Iterator<Object[]> dataProvider() throws IOException {
		return wrapListForDataProvider(data("dadata.csv"));
	}

	private List<PhoneData> data(String filename) throws IOException {
		return new PhoneDataLoader().loadDataFromCsvFile(filename);
	}

	@Test(dataProvider = "dataProvider")
	void testSpecificPhone(PhoneData phone) {
		PhoneData result = app.getPhoneHelper().enterPhone(phone.getPhone());
		assertEquals(result, phone);
	}

}
