package name.admitriev.testing.rcd.data;

public class LoginDataLoader extends CSVDataLoader<LoginData> {


	@Override
	protected LoginData generateObject(String[] parts) {
		return new LoginData()
				.withLogin(parts[0])
				.withPass(parts[1])
				.withExpectedResult(parts[2]);
	}
}