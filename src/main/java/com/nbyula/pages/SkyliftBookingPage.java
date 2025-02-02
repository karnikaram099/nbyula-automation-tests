package com.nbyula.pages;

import com.nbyula.constants.Constants;
import com.nbyula.utils.CommonUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SkyliftBookingPage {

    public WebDriver driver;

    // Web Elements
    @FindBy(xpath = "//input[@type=\"text\"]")
    WebElement txt_searchInputField;

    @FindBy(xpath = "(//button[contains(text(), 'Book')])[1]")
    WebElement btn_onBookCTA;

    @FindBy(xpath = "//h1[text()='15']")
    WebElement btn_selectDate;

    @FindBy(xpath = "//span[contains(text(),'Book a Free Session for 15 Feb 2025')]")
    WebElement btn_createASession;

    @FindBy(xpath = "//*[local-name()='svg' and @class='absolute top-6 right-6 w-4 h-4 cursor-pointer']")
    WebElement btn_closeCTA;

    @FindBy(xpath = "//div[contains(text(), 'Session booked successfully')]")
    WebElement lbl_sessionBookedSuccessfully;

    public SkyliftBookingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToSkyliftBookingPage() {
        driver.get(Constants.SKYLIFT_URL);
        driver.manage().window().maximize();
    }

    public void clickOnSearchBar() {
        CommonUtils.waitForElement(txt_searchInputField);
        txt_searchInputField.click();
    }

    public void enterSkyliftBookingOnSearchBar() {
        CommonUtils.waitForElement(txt_searchInputField);
        txt_searchInputField.sendKeys("Skylift Booking");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }

    private void switchToNewWindow(String parentWindowHandle) {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void completeBookSession() throws InterruptedException {
        CommonUtils.waitForElement(txt_searchInputField);
        String parentWindowHandle = driver.getWindowHandle();
        CommonUtils.waitForElement(btn_onBookCTA);
        btn_onBookCTA.click();
        switchToNewWindow(parentWindowHandle);
        CommonUtils.waitForElement(btn_selectDate);
        selectDate();
        CommonUtils.waitForElement(btn_createASession);
        clickOnCreateAFreeSession();
        CommonUtils.waitForElement(btn_closeCTA);
        clickOnCloseCTA();
    }

    public void selectDate() {
        btn_selectDate.click();
    }

    public void clickOnCreateAFreeSession() {
        btn_createASession.click();
    }

    public void clickOnCloseCTA() {
        btn_closeCTA.click();
    }

    public boolean isSearchBarDisplayed() {
        return txt_searchInputField.isDisplayed();
    }

    public boolean isSearchInputCorrect(String expectedInput) {
        return txt_searchInputField.getAttribute("value").equals(expectedInput);
    }

}