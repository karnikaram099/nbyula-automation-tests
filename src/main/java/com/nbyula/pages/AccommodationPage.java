package com.nbyula.pages;

import com.nbyula.constants.Constants;
import com.nbyula.model.AccommodationCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class AccommodationPage {
    private WebDriver driver;

    @FindBy(css = "div.rounded-3xl.cursor-pointer")
    List<WebElement> cards;

    @FindBy(css = "a.text-lg.font-bold.line-clamp-2.hover\\:underline")
    List<WebElement> titles;

    @FindBy(css = "p.text-18.font-medium")
    List<WebElement> prices;

    @FindBy(css = "a[href]")
    List<WebElement> links;

    public AccommodationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToAccommodationPage() {
        driver.get(Constants.ACCOMMODATION_URL);
        driver.manage().window().maximize();
    }

    public List<AccommodationCard> scrapeAllCards() {
        List<AccommodationCard> accommodationCards = new ArrayList<>();

        int totalCards = cards.size();
        int totalTitles = titles.size();
        int totalPrices = prices.size();
        int totalLinks = links.size();

        int minSize = Math.min(Math.min(totalCards, totalTitles), Math.min(totalPrices, totalLinks));


        for (int i = 0; i < minSize; i++) {
            String title = titles.get(i).getText();
            String price = prices.get(i).getText();
            String link = links.get(i).getAttribute("href");

            accommodationCards.add(new AccommodationCard(title, price, link));
        }

        return accommodationCards;
    }

    public void printAllCards(List<AccommodationCard> accommodationCards) {
        for (AccommodationCard card : accommodationCards) {
            System.out.println(card);
        }
    }
}