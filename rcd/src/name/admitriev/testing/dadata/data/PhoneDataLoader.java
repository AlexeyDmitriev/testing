package name.admitriev.testing.dadata.data;

public class PhoneDataLoader extends CSVDataLoader<PhoneData> {
	public PhoneDataLoader() {
		super(1);
	}

	@Override
	protected PhoneData generateObject(String[] parts) {
		// comment ignored
		return new PhoneData().withPhone(parts[0]).withType(parts[1]).withOperator(parts[2]).withRegion(parts[3]);
	}
}
