package name.admitriev.testing.rcd.framework;

import name.admitriev.testing.rcd.data.LoginData;

import static org.testng.Assert.assertEquals;

public class LoginHelper extends BaseHelper {

	public LoginHelper(AppManager manager) {
		super(manager);
	}

	public String doLogin(LoginData loginData) {
		app.getNavigationHelper().gotoLoginPage();
		type(by("login-username-field"), loginData.getLogin());
		type(by("login-password-field"), loginData.getPass());
		click(by("login-button"));

		if (isElementPresent(by("header-link-logout"))) {
			return "OK";
		}

		return getText(by("login-text-error-selector"));
	}

	public void performCorrectLogin() {
		LoginData data = new LoginData()
				.withLogin(app.properties.getProperty("correct-login"))
				.withPass(app.properties.getProperty("correct-pass"));
		assertEquals(doLogin(data), "OK");
	}


}
