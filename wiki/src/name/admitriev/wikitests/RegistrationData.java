package name.admitriev.wikitests;

public class RegistrationData {
	private String login;
	private String password;
	private String passwordConfirmation;
	private String email;
	private String expectedResult;

	public String getExpectedResult() {
		return expectedResult;
	}

	public RegistrationData withExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
		return this;
	}

	public String getLogin() {
		return login;
	}

	public RegistrationData withLogin(String login) {
		this.login = login;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public RegistrationData withPassword(String password) {
		this.password = password;
		return this;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public RegistrationData withPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public RegistrationData withEmail(String email) {
		this.email = email;
		return this;
	}

	@Override
	public String toString() {
		return "RegistrationData{" +
				"login='" + login + '\'' +
				", password='" + password + '\'' +
				", passwordConfirmation='" + passwordConfirmation + '\'' +
				", email='" + email + '\'' +
				", expectedResult='" + expectedResult + '\'' +
				'}';
	}
}
