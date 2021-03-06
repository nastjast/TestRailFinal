package ui_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test(description = "Login using valid creds", groups = {"Smoke", "Regression", "Positive"})
    public void loginWithValidCredsTest() {
        loginSteps.loginAndClickLoginBtn(EMAIL_UI, PASSWORD_UI);
        Assert.assertTrue(dashboardPage.isDashboardVisible());
    }

    @Test(description = "Login using invalid creds", groups = {"Regression", "Negative"})
    public void loginWithInvalidCredsTest() {
        loginSteps.loginAndClickLoginBtn(INVALID_EMAIL, INVALID_PASSWORD);
        Assert.assertEquals(loginPage.getErrorText(), ERROR_MESSAGE_LOGIN);
    }

    @Test(description = "Login only with email", groups = {"Regression", "Negative"})
    public void loginWithEmailTest() {
        loginSteps.loginAndClickLoginBtn(EMAIL_UI, "");
        Assert.assertEquals(loginPage.getMissingRequiredFieldText(), MISSING_PASSWORD_TEXT);
    }

    @Test(description = "Login only with password", groups = {"Regression", "Negative"})
    public void loginWithPasswordTest() {
        loginSteps.loginAndClickLoginBtn(" ", PASSWORD_UI);
        Assert.assertEquals(loginPage.getMissingRequiredFieldText(), MISSING_EMAIL_TEXT);
    }

    @Test(description = "Login and log out from Dashboard page", groups = {"Smoke", "Regression", "Positive"})
    public void logOutTest() {
        loginSteps.logInAndLogOut(EMAIL_UI, PASSWORD_UI);
        Assert.assertEquals(loginPage.getLoginBoxText(), LOGIN_BOX_TEXT);
    }
}
