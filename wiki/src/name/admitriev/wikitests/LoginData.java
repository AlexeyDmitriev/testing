package name.admitriev.wikitests;

public class LoginData {

	private String login;
	private String pass;
	private String expectedResult;

	public String getLogin() {
		return login;
	}

	public LoginData withLogin(String login) {
		this.login = login;
		return this;
	}

	public String getPass() {
		return pass;
	}

	public LoginData withPass(String pass) {
		this.pass = pass;
		return this;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public LoginData withExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
		return this;
	}

	public LoginData() {
		login = "";
		pass = "";
		expectedResult = "";
	}

	@Override
	public String toString() {
		return "LoginData [login= " + login + " password= " + pass + "expected result = " + expectedResult;
	}

}