package com.example.testng;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.example.pom.FormPom;
import com.example.utils.Driver;

public class FormTest {

    public static WebDriver driver;
    static public String URL = "https://demoqa.com/";

    static public String FIRST_NAME = "Cozma";
    static public String LAST_NAME = "Vasile";
    static public String EMAIL = "vcozma06@gmail.com";
    static public String GENDER = "Male";
    static public String NUMBER = "0987654321";
    static public String DATE = "14 Jan 2006";
    static public String SUBJECT = "Maths";
    static public String HOBBY = "Sports";
    static public String STATE = "Rajasthan";
    static public String CITY = "Jaipur";

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        driver = Driver.getRemoteDriver();
        // driver = Driver.getAutoLocalDriver();
        driver.manage().window().maximize();
    }

    @SuppressWarnings("null")
    @Test(invocationCount = 1)
    public void formTest() {
        // demoqa.com
        System.out.println("\n---START TEST---\n");
        driver.get(URL);

        FormPom formPom = new FormPom(driver);

        formPom.pause(1000);

        formPom.clickForms();
        formPom.pause(1000);
        formPom.clickPracticeForms();

        formPom.setFirstName(FIRST_NAME);
        formPom.setLastName(LAST_NAME);
        formPom.setEmail(EMAIL);
        formPom.setGender(GENDER);
        formPom.setNumber(NUMBER);
        formPom.setDateOfBirth(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobbies(HOBBY);
        formPom.setState(STATE);
        formPom.setCity(CITY);
        formPom.clickSubmit();

        String actualName = formPom.getTableData("Student Name");
        Assert.assertEquals(actualName, FIRST_NAME + " " + LAST_NAME);

        System.out.println("---FINISH TEST---");
        // formPom.pause(10000);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}