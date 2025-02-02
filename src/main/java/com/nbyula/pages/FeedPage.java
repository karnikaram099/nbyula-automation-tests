package com.nbyula.pages;

import com.nbyula.base.BasePage;
import com.nbyula.constants.Constants;
import com.nbyula.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FeedPage extends BasePage {

    @FindBy(partialLinkText = "Work Abroad")
    WebElement btn_workAbroad;

    @FindBy(xpath = "(//span[@class='ml-2 text-base font-medium'])[3]")
    WebElement txt_profileEvaluation;

    @FindBy(xpath = "//div[contains(text(), 'Profile Evaluation')]")
    WebElement getTxt_profileEvaluationScreen;

    @FindBy(id = "name")
    WebElement nameField;

    @FindBy(name = "email")
    WebElement emailField;

    @FindBy(id = "requirement")
    WebElement requirementField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_submit;

    @FindBy(xpath = "//div[text()='Unable to submit your request. Write to us at support@nbyula.com']")
    WebElement txt_errorMsg;

    @FindBy(xpath = "//div[@class=\"Toastify__toast-icon Toastify--animate-icon Toastify__zoom-enter\"]")
    WebElement txt_successfulMessage;

    @FindBy(css = "article")
    List<WebElement> quests;

    public FeedPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSuccessfulMessage() throws InterruptedException {
        Thread.sleep(1000);
        CommonUtils.waitForElement(txt_successfulMessage);
        txt_successfulMessage.isDisplayed();
        return true;
    }

    public boolean isAtLeastOneQuestPresent() {
        CommonUtils.waitForElement(quests.get(0));
        return !quests.isEmpty();
    }

    public void printAllQuestTitles() {
        for (WebElement quest : quests) {
            System.out.println(quest.getText());
        }
    }

        public void navigateToFeedPage () {
            driver.get(Constants.FEEDPAGE_URL);
            driver.manage().window().maximize();
        }

        public void clickOnWorkAbroad () {
            CommonUtils.waitForElement(btn_workAbroad);
            btn_workAbroad.click();
        }

        public String getProfileEvaluationText () {
            CommonUtils.waitForElement(txt_profileEvaluation);
            return txt_profileEvaluation.getText();
        }

        public void clickOnProfileEvaluation () {
            CommonUtils.waitForElement(txt_profileEvaluation);
            txt_profileEvaluation.click();
        }

        public boolean isProfileEvaluationScreenDisplayed () {
            CommonUtils.waitForElement(getTxt_profileEvaluationScreen);
            return getTxt_profileEvaluationScreen.isDisplayed();
        }

        public void enterName (String name){
            CommonUtils.waitForElement(nameField);
            nameField.sendKeys(name);
        }

        public boolean isNameFieldDisplayed () throws InterruptedException {
            Thread.sleep(3000); // using this thread.sleep because the error on opening page taking 3 sec time to appear one more error message
            nameField.isDisplayed();
            return true;
        }

        public void enterEmail (String email){
            CommonUtils.waitForElement(emailField);
            emailField.sendKeys(email);
        }

        public boolean isEmailFieldDisplayed () {
            emailField.isDisplayed();
            return true;
        }

        public void enterDescription (String description){
            CommonUtils.waitForElement(requirementField);
            requirementField.sendKeys(description);
        }

        public boolean isDescriptionFieldDisplayed () {
            requirementField.isDisplayed();
            return true;
        }

        public boolean isSubmitButtonEnabled () {
            CommonUtils.waitForElement(btn_submit);
            return btn_submit.isEnabled();
        }

        public void clickOnSubmitButton () {
            CommonUtils.waitForElement(btn_submit);
            btn_submit.click();
        }

        public String getErrorMessage () {
            CommonUtils.waitForElement(txt_errorMsg);
            return txt_errorMsg.getText();
        }
    }
