package com.nbyula.tests;

import com.nbyula.base.BaseTest;
import com.nbyula.pages.SkyliftBookingPage;
import com.nbyula.utils.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class SkyliftBookingTest extends BaseTest {
    private SkyliftBookingPage skyliftBookingPage;

    @BeforeMethod
    public void setUp() {
        log.info("Setting up Skylift Booking Test...");

        skyliftBookingPage = new SkyliftBookingPage(DriverManager.getDriver());
        skyliftBookingPage.navigateToSkyliftBookingPage();

        test.log(com.aventstack.extentreports.Status.INFO, "Navigated to Skylift Booking Page.");
        log.info("Navigated to Skylift Booking Page.");
    }

    @Test
    public void testSkyliftBooking() throws InterruptedException {
        test.log(com.aventstack.extentreports.Status.INFO, "Starting Skylift Booking test...");
        log.info("Starting Skylift Booking test...");

        test.log(com.aventstack.extentreports.Status.INFO, "Clicking on the search bar...");
        skyliftBookingPage.clickOnSearchBar();
        Assert.assertTrue(skyliftBookingPage.isSearchBarDisplayed(), "Search bar is not displayed.");
        test.log(com.aventstack.extentreports.Status.PASS, "Search bar displayed successfully.");

        test.log(com.aventstack.extentreports.Status.INFO, "Entering 'Skylift Booking' in the search bar...");
        skyliftBookingPage.enterSkyliftBookingOnSearchBar();
        Assert.assertTrue(skyliftBookingPage.isSearchInputCorrect("Skylift Booking"), "Search input is incorrect.");
        test.log(com.aventstack.extentreports.Status.PASS, "Search input entered correctly.");

        test.log(com.aventstack.extentreports.Status.INFO, "Completing the book session...");
        skyliftBookingPage.completeBookSession();
        test.log(com.aventstack.extentreports.Status.PASS, "Book session completed successfully.");

        log.info("Skylift Booking test executed successfully.");
    }
}
