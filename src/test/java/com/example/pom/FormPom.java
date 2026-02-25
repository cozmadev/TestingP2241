package com.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.example.utils.Utils;

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
        // 20);
        scrollToElement(practiceForms);

        practiceForms.click();
    }

    public void setFirstName(String firstNameParam) {
        Utils.explicitWait(driver, ExpectedConditions.visibilityOf(practiceForms),
                10);

        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setLastName(String lastNameParams) {

        lastName.clear();
        lastName.sendKeys(lastNameParams);
    }

    public void setEmail(String userEmailParams) {

        email.clear();
        email.sendKeys(userEmailParams);
    }

    public void setNumber(String numberParam) {
        scrollToElement(practiceForms);
        number.clear();
        number.sendKeys(numberParam);
    }

    public void setDateOfBirth(String dateParam) {
        scrollToElement(practiceForms);
        dateOfBirth.sendKeys(Keys.CONTROL, "a");
        dateOfBirth.sendKeys(dateParam);
        dateOfBirth.sendKeys(Keys.ENTER);
    }

    public void setSubject(String subjectParam) {
        scrollToElement(practiceForms);
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
    }

    public void setHobbies(String hobbyParam) {
        scrollToElement(practiceForms);
        WebElement ddState = driver
                .findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbyParam + "']/../input"));
        ddState.sendKeys(" ");
    }

    public void setState(String stateParam) {
        scrollToElement(practiceForms);
        state.click();
        WebElement optionState = state.findElement(By.xpath(".//*[text()='" + stateParam + "']"));
        optionState.click();

    }

    public void setCity(String cityParam) {
        scrollToElement(practiceForms);
        city.click();
        WebElement optionCity = city.findElement(By.xpath(".//*[text()='" + cityParam + "']"));
        optionCity.click();
    }

    public void setGender(String genderParam) {
        scrollToElement(practiceForms);
        WebElement gender = driver
                .findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
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
}
