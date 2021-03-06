package steps;

import objects.ui.TestCase;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.CreateTestCasePage;
import pages.TestCasesListOverviewPage;

public class TestCaseSteps {

    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public CreateTestCasePage createTestCasePage;
    TestCasesListOverviewPage testCasesListOverviewPage;

    public TestCaseSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        createTestCasePage = new CreateTestCasePage(driver);
        testCasesListOverviewPage = new TestCasesListOverviewPage(driver);
    }

    public TestCaseSteps addTestCase(TestCase testCase) {
        dashboardPage.clickOnTestCasesLink()
                .clickOnAddTestCaseBtn()
                .fillAllTestCaseFields(testCase)
                .clickAddTestCaseBtn();
        return this;
    }

    public TestCaseSteps addTestCaseAndAddNewOne(TestCase testCase) {
        dashboardPage.clickOnTestCasesLink()
                .clickOnAddTestCaseBtn()
                .fillAllTestCaseFields(testCase)
                .clickAddAndNextBtn();
        return this;
    }

    public TestCaseSteps openTestCasePageAndClickCancel() {
        dashboardPage.clickOnTestCasesLink()
                .clickOnAddTestCaseBtn()
                .clickCancelCaseBtn();
        return this;
    }

    public TestCaseSteps loginAndOpenTestCasesListPage(String email, String password) {
        loginPage.openLoginPage()
                .enterLoginCreds(email, password)
                .clickLogInBtn()
                .clickOnTestCasesLink();
        return this;
    }

    public TestCaseSteps deleteCertainTestCase(int index) {
        testCasesListOverviewPage.chooseCaseCheckboxByIndex(index)
                .clickDeleteCaseBtn();
        return this;
    }
}
