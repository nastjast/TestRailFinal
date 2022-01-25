package ui_tests;

import objects.ui.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DataSupplierInfo;

public class TestCaseTest extends BaseTest {

    @Test(dataProvider = "Test case positive", dataProviderClass = DataSupplierInfo.class,
            description = "Create a test case filling out all and only required fields and asserting success message",
            groups = {"Smoke", "Positive", "Regression"})
    public void createPositiveTestCaseTest(TestCase testCase) {
        testCaseSteps.addTestCase(testCase);
        Assert.assertEquals(caseDetailsPage.getConfirmationMessageAddCaseBtn(), TEST_CASE_SUCCESS_MSG);
        Assert.assertEquals(caseDetailsPage.getTestCaseTitle(), testCase.getTitle());
        Assert.assertFalse(caseDetailsPage.getTestCaseDetails("Type").isEmpty());
    }

    @Test(dataProvider = "Test case negative", dataProviderClass = DataSupplierInfo.class,
            description = "Create a test case filling out only unrequired fields and leaving all fields empty and asserting error message",
            groups = {"Negative", "Regression"})
    public void createNegativeTestCaseTest(TestCase testCase) {
        loginSteps.loginAndClickLoginBtn(EMAIL_UI, PASSWORD_UI);
        testCaseSteps.addTestCase(testCase);
        Assert.assertEquals(testCasePage.getMessageError(), TEST_CASE_ERROR_MSG);
    }

    @Test(dataProvider = "Test case positive", dataProviderClass = DataSupplierInfo.class,
            description = "Create a test case filling out all and only required fields and asserting success message",
            groups = {"Positive", "Regression"})
    public void createTestCaseAndAddNewTest(TestCase testCase) {
        loginSteps.loginAndClickLoginBtn(EMAIL_UI, PASSWORD_UI);
        testCaseSteps.addTestCaseAndAddNewOne(testCase);
        Assert.assertEquals(testCasePage.getConfirmationMessageAddAndNextBtn(), TEST_CASE_ADD_AND_NEXT_SUCCESS_MSG);
    }

    @Test(description = "Open test case page and click Cancel btn. Ensure that you are redirected to Test Cases List page", groups = {"Smoke", "Positive", "Regression"})
    public void openTestCaseAndClickCancelBtnTest() {
        loginSteps.loginAndClickLoginBtn(EMAIL_UI, PASSWORD_UI);
        testCaseSteps.openTestCasePageAndClickCancel();
        Assert.assertTrue(testCasesListPage.isTestCaseListTitleVisible());
    }

    @Test(description = "Edit test case by changing its title", groups = {"Smoke", "Positive", "Regression"})
    public void editTestCasePositiveTest() {
        testCaseSteps.loginAndOpenTestCasesListPage(EMAIL_UI, PASSWORD_UI);
        editTestCaseSteps.editTestCaseAndSave(10, "New title");
        reviewChangesPage.clickOkBtn();
        Assert.assertTrue(testCasesListPage.isUpdateSuccessMsgVisible());
        Assert.assertEquals(testCasesListPage.getTitleByIndex(10), "New title");
    }

    @Test(description = "Ensure modal page is opened when saving changes", groups = "Regression")
    public void reviewChangesModalOpensTest() {
        testCaseSteps.loginAndOpenTestCasesListPage(EMAIL_UI, PASSWORD_UI);
        editTestCaseSteps.editTestCaseAndSave(10, "New title");
        Assert.assertTrue(reviewChangesPage.isReviewChangesPageDisplayed());
    }

    @Test(description = "Save edit test page without any changes done", groups = {"Negative", "Regression"})
    public void editTestCaseNegativeTest() {
        testCaseSteps.loginAndOpenTestCasesListPage(EMAIL_UI, PASSWORD_UI);
        editTestCaseSteps.editTestWithNoFieldsChangesAndClickSave(10);
        Assert.assertTrue(editTestCasePage.isErrorMessageDisplayed());
        Assert.assertTrue(editTestCasePage.isTitleVisible());
    }

    @Test(description = "Delete test case by index clicking on Mark as Deleted button", groups = {"Smoke", "Positive", "Regression"})
    public void deleteTest() {
        testCaseSteps.loginAndOpenTestCasesListPage(EMAIL_UI, PASSWORD_UI);
        int testCaseNumberBeforeDelete = testCasesListPage.getTestCasesNumber();
        testCaseSteps.deleteCertainTestCase(20);
        confimationModalPage.clickOnMarkAsDeletedBtn();
        int testCaseNumberAfterDelete = testCasesListPage.getTestCasesNumber();
        Assert.assertFalse(testCaseNumberBeforeDelete == testCaseNumberAfterDelete);
    }

    @Test(description = "Delete test case by index clicking on Delete permanently button", groups = {"Smoke", "Positive", "Regression"})
    public void deleteTestPermanentlyTest() {
        testCaseSteps.loginAndOpenTestCasesListPage(EMAIL_UI, PASSWORD_UI);
        int testCaseNumberBeforeDelete = testCasesListPage.getTestCasesNumber();
        testCaseSteps.deleteCertainTestCase(20);
        confimationModalPage.clickOnDeletePermanentlyBtn();
        confimationModalPage.clickOnDeletePermanentlySecondPage();
        int testCaseNumberAfterDelete = testCasesListPage.getTestCasesNumber();
        Assert.assertFalse(testCaseNumberBeforeDelete == testCaseNumberAfterDelete);
    }
}
