package name.admitriev.wikitests;

import java.util.Arrays;

public class RegDataLoader extends CSVDataLoader<RegistrationData> {
	@Override
	protected RegistrationData generateObject(String[] parts) {
		try {
			return new RegistrationData()
					.withLogin(parts[0])
					.withPassword(parts[1])
					.withPasswordConfirmation(parts[2])
					.withEmail(parts[3])
					.withExpectedResult(parts[4]);
		}
		catch (Exception e) {
			throw new RuntimeException(Arrays.toString(parts));
		}
	}
}
