package com.nbyula.tests;

import com.nbyula.base.BaseTest;
import com.nbyula.model.AccommodationCard;
import com.nbyula.pages.AccommodationPage;
import com.nbyula.utils.DriverManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Slf4j
public class AccommodationPageTest extends BaseTest {
    private AccommodationPage accommodationPage;

    @BeforeMethod
    public void setUp() {
        log.info("Setting up Accommodation Page Test...");

        accommodationPage = new AccommodationPage(DriverManager.getDriver());
        accommodationPage.navigateToAccommodationPage();

        test.log(com.aventstack.extentreports.Status.INFO, "Navigated to the Accommodation Page.");
        log.info("Navigated to the Accommodation Page.");
    }

    @Test
    public void testScrapeAccommodationCards() {
        test.log(com.aventstack.extentreports.Status.INFO, "Starting accommodation card scraping test...");
        log.info("Starting accommodation card scraping test...");

        List<AccommodationCard> cardDataList = accommodationPage.scrapeAllCards();

        test.log(com.aventstack.extentreports.Status.INFO, "Checking if accommodation cards were found...");
        log.info("Checking if accommodation cards were found...");

        Assert.assertFalse(cardDataList.isEmpty(), "No accommodation cards were found!");
        test.log(com.aventstack.extentreports.Status.PASS, "Accommodation cards found successfully.");

        test.log(com.aventstack.extentreports.Status.INFO, "Validating each accommodation card data...");
        log.info("Validating each accommodation card data...");

        for (AccommodationCard cardData : cardDataList) {
            Assert.assertNotNull(cardData.getTitle(), "Title is missing!");
            Assert.assertNotNull(cardData.getPrice(), "Price is missing!");
            Assert.assertNotNull(cardData.getLink(), "Link is missing!");
            test.log(com.aventstack.extentreports.Status.PASS, "Validated card: " + cardData.getTitle());
        }

        test.log(com.aventstack.extentreports.Status.INFO, "Printing all scraped accommodation cards...");
        log.info("Printing all scraped accommodation cards...");
        accommodationPage.printAllCards(cardDataList);

        test.log(com.aventstack.extentreports.Status.PASS, "Accommodation card scraping test completed successfully.");
        log.info("Accommodation card scraping test completed successfully.");
    }
}
