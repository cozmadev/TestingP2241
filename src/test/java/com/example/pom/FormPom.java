package com.example.pom;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.example.utils.Utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class FormPom {
    static public WebDriver driver;
    static public JavascriptExecutor js;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "/html/body/div/div/div/div/div[1]/div/div/div[2]/div/ul/li/a/span")
    WebElement practiceForms;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    WebElement lastName;

    @FindBy(xpath = "//*[@id=\"userEmail\"]")
    WebElement email;

    @FindBy(xpath = "//*[@id=\"userNumber\"]")
    WebElement number;

    @FindBy(xpath = "//*[@id=\"dateOfBirthInput\"]")
    WebElement dateOfBirth;

    @FindBy(xpath = "//*[@id=\"subjectsInput\"]")
    WebElement subjectsInput;

    @FindBy(xpath = "//*[@id=\"state\"]")
    WebElement state;

    @FindBy(xpath = "//*[@id=\"city\"]")
    WebElement city;

    @FindBy(xpath = "// *[@id=\"submit\"]")
    WebElement submitButton;

    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        js = (JavascriptExecutor) driverParam;
        PageFactory.initElements(driver, this);
    }

    public void clickForms() {

        // Utils.explicitWait(driver, ExpectedConditions.visibilityOf(forms),
        // 1);

        scrollToElement(forms);

        forms.click();
    }

    public void clickPracticeForms() {
        // Utils.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForms),
        // 20)
        scrollToElement(practiceForms);

        practiceForms.click();
    }

    @Step("Set first name")
    public void setFirstName(String firstNameParam) {
        takeScreenshot("Before set first name");
        Utils.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForms),
                10);

        firstName.clear();
        firstName.sendKeys(firstNameParam);
        takeScreenshot("After set first name");
    }

    @Step("Set last name")
    public void setLastName(String lastNameParams) {
        takeScreenshot("Before set last name");
        lastName.clear();
        lastName.sendKeys(lastNameParams);
        takeScreenshot("After set last name");
    }

    @Step("Set email")
    public void setEmail(String userEmailParams) {
        takeScreenshot("Before set email");
        email.clear();
        email.sendKeys(userEmailParams);
        takeScreenshot("After set email");
    }

    @Step("Set number")
    public void setNumber(String numberParam) {
        takeScreenshot("Before set number");
        scrollToElement(practiceForms);
        number.clear();
        number.sendKeys(numberParam);
        takeScreenshot("After set number");
    }

    @Step("Set date of birth")
    public void setDateOfBirth(String dateParam) {
        takeScreenshot("Before Set date of birth");
        scrollToElement(practiceForms);
        dateOfBirth.sendKeys(Keys.CONTROL, "a");
        dateOfBirth.sendKeys(dateParam);
        dateOfBirth.sendKeys(Keys.ENTER);
        takeScreenshot("After Set date of birth");
    }

    @Step("Set subject")
    public void setSubject(String subjectParam) {
        takeScreenshot("Before Set subject");
        scrollToElement(practiceForms);
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
        takeScreenshot("After Set subject");
    }

    @Step("Set hobbies")
    public void setHobbies(String hobbyParam) {
        takeScreenshot("Before Set hobbies");
        scrollToElement(practiceForms);
        WebElement ddState = driver
                .findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbyParam + "']/../input"));
        ddState.sendKeys(" ");
        takeScreenshot("After Set hobbies");
    }

    @Step("Set state")
    public void setState(String stateParam) {
        takeScreenshot("Before Set state");
        scrollToElement(practiceForms);
        state.click();
        WebElement optionState = state.findElement(By.xpath(".//*[text()='" + stateParam + "']"));
        optionState.click();
        takeScreenshot("After Set state");

    }

    @Step("Set city")
    public void setCity(String cityParam) {
        takeScreenshot("Before Set city");
        scrollToElement(practiceForms);
        city.click();
        WebElement optionCity = city.findElement(By.xpath(".//*[text()='" + cityParam + "']"));
        optionCity.click();
        takeScreenshot("After Set city");
    }

    @Step("Set gender")
    public void setGender(String genderParam) {
        takeScreenshot("Before Set gender");
        scrollToElement(practiceForms);
        WebElement gender = driver
                .findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
        takeScreenshot("After Set gender");
    }

    public void clickSubmit() {
        scrollToElement(submitButton);
        submitButton.click();
    }

    public String getTableData(String labelParam) {
        String value = driver.findElement(By.xpath("//table//*[text()='" + labelParam + "']/../*[2]")).getText();
        return value;
    }

    public void scrollToElement(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView(true);", element);

    }

    public void closeAdvert() {

        try {
            js.executeScript(
                    "var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
                            +
                            "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {
        }
        try {
            js.executeScript(
                    "var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
                            +
                            "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {
        }

    }

    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // private void takeScreenshot(String stepName) {
    // try {
    // byte[] screenshot = ((TakesScreenshot)
    // driver).getScreenshotAs(OutputType.BYTES);
    // Allure.addAttachment(stepName, "image/png", new
    // ByteArrayInputStream(screenshot), ".png");
    // } catch (Exception e) {
    // Allure.addAttachment("Screenshot Error", e.toString());
    // }
    // }

    @io.qameta.allure.Attachment(value = "{stepName}", type = "image/png")
    private byte[] takeScreenshot(String stepName) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
