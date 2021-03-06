package constants;

import util.PropertyReader;

public interface ITestConstantsUI {

    String ERROR_MESSAGE_LOGIN = "Email/Login or Password is incorrect. Please try again.";
    String LOGIN_BOX_TEXT = "Log into Your Account";
    String INVALID_EMAIL = "hello";
    String INVALID_PASSWORD = "world";
    String MISSING_PASSWORD_TEXT = "Password is required.";
    String MISSING_EMAIL_TEXT = "Email/Login is required.";
    String EMAIL_UI = System.getenv().getOrDefault("email", PropertyReader.getProperty("email"));
    String PASSWORD_UI = System.getenv().getOrDefault("password", PropertyReader.getProperty("password"));
    String TEST_CASE_SUCCESS_MSG = "Successfully added the new test case. Add another";
    String TEST_CASE_ERROR_MSG = "Field Title is a required field.";
    String TEST_CASE_ADD_AND_NEXT_SUCCESS_MSG = "Successfully added the new test case. View test case";
    String MILESTONE_ADDED_MSG = "Successfully added the new milestone.";
    String MILESTONE_STARTED_MSG = "Successfully started the milestone.";
    String MILESTONE_DELETED_MSG = "Successfully added the new milestone.";
    String MILESTONE_EDITED_MSG = "Successfully updated the milestone.";
    String MILESTONE_PERCENT_TEXT = "0%";
}
