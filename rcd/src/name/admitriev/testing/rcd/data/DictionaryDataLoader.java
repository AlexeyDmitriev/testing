package name.admitriev.testing.rcd.data;

public class DictionaryDataLoader extends CSVDataLoader<DictionaryData> {
	@Override
	protected DictionaryData generateObject(String[] parts) {
		return new DictionaryData()
				.withName(parts[0])
				.withDescription(parts[1])
				.withExpectedResult(parts[2]);
	}
}
