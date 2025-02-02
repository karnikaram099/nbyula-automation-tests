package com.nbyula.tests;

import com.nbyula.base.BaseTest;
import com.nbyula.pages.FeedPage;
import com.nbyula.utils.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class FeedPageTest extends BaseTest {
    private FeedPage feedPage;

    @BeforeMethod
    public void setUp() {
        log.info("Setting up Feed Page Test...");
        feedPage = new FeedPage(DriverManager.getDriver());
        feedPage.navigateToFeedPage();

        test.log(com.aventstack.extentreports.Status.INFO, "Navigated to the Feed Page.");
        log.info("Navigated to the Feed Page.");
    }

    @Test
    public void testAtLeastOneQuestPresent() {
        test.log(com.aventstack.extentreports.Status.INFO, "Starting test to verify at least one quest is present...");
        log.info("Starting the test to verify at least one quest is present...");

        boolean isQuestPresent = feedPage.isAtLeastOneQuestPresent();
        test.log(com.aventstack.extentreports.Status.INFO, "Checking if at least one quest is present on the page...");

        Assert.assertTrue(isQuestPresent, "No quest found on the page!");
        test.log(com.aventstack.extentreports.Status.PASS, "At least one quest is present.");

        log.info("Printing all quest titles...");
        feedPage.printAllQuestTitles();

        test.log(com.aventstack.extentreports.Status.PASS, "Test to verify at least one quest is present completed successfully.");
        log.info("Test completed successfully.");
    }

    @Test
    public void verifyWorkAbroadScenario() throws InterruptedException {
        test.log(com.aventstack.extentreports.Status.INFO, "Starting the Work Abroad scenario test...");
        log.info("Starting the Work Abroad scenario test...");

        test.log(com.aventstack.extentreports.Status.INFO, "Clicking on the 'Work Abroad' option...");
        feedPage.clickOnWorkAbroad();

        test.log(com.aventstack.extentreports.Status.INFO, "Verifying the 'Profile Evaluation' text...");
        String profileEvaluationText = feedPage.getProfileEvaluationText();
        Assert.assertEquals(profileEvaluationText, "Profile Evaluation", "Profile Evaluation text is not displayed.");
        test.log(com.aventstack.extentreports.Status.PASS, "Verified Profile Evaluation text.");

        test.log(com.aventstack.extentreports.Status.INFO, "Clicking on the 'Profile Evaluation' button...");
        feedPage.clickOnProfileEvaluation();

        test.log(com.aventstack.extentreports.Status.INFO, "Verifying if the Profile Evaluation screen is displayed...");
        Assert.assertTrue(feedPage.isProfileEvaluationScreenDisplayed(), "Profile Evaluation screen is not displayed.");
        test.log(com.aventstack.extentreports.Status.PASS, "Profile Evaluation screen displayed successfully.");

        test.log(com.aventstack.extentreports.Status.INFO, "Entering user details...");
        Assert.assertTrue(feedPage.isNameFieldDisplayed(), "Name field is not displayed.");
        feedPage.enterName("Ram Karnika");
        test.log(com.aventstack.extentreports.Status.PASS, "Entered Name: Ram Karnika.");

        Assert.assertTrue(feedPage.isEmailFieldDisplayed(), "Email field is not displayed.");
        feedPage.enterEmail("karnika.ramaswamy@gmail.com");
        test.log(com.aventstack.extentreports.Status.PASS, "Entered Email: karnika.ramaswamy@gmail.com.");

        Assert.assertTrue(feedPage.isDescriptionFieldDisplayed(), "Description field is not displayed.");
        feedPage.enterDescription("Nbyula is a German technology brand founded in 2014...");
        test.log(com.aventstack.extentreports.Status.PASS, "Entered Description.");

        Assert.assertTrue(feedPage.isSubmitButtonEnabled(), "Submit button is not enabled.");
        test.log(com.aventstack.extentreports.Status.PASS, "Submit button is enabled.");

        test.log(com.aventstack.extentreports.Status.INFO, "Clicking on the Submit button...");
        feedPage.clickOnSubmitButton();

        if (feedPage.isSuccessfulMessage()) {
            test.log(com.aventstack.extentreports.Status.PASS, "Success message verified: 'Thanks! We'll get in touch with you soon.'");
        } else {
            test.log(com.aventstack.extentreports.Status.INFO, "Verifying the error message...");
            String errorMessage = feedPage.getErrorMessage();
            Assert.assertEquals(errorMessage, "Unable to submit your request. Write to us at support@nbyula.com", "Error message is incorrect.");
            test.log(com.aventstack.extentreports.Status.PASS, "Error message verified: " + errorMessage);
        }

        test.log(com.aventstack.extentreports.Status.PASS, "Work Abroad scenario test completed successfully.");
        log.info("Work Abroad scenario test completed successfully.");
    }
}
