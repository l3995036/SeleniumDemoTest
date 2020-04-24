package com.example.learning.seleniumtest.logic;

import com.example.learning.seleniumtest.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login{
public static void loginTest(WebDriver browser, String email, String password) throws InterruptedException {
        browser.get("https://mail.163.com/");
        browser.manage().window().maximize();
        browser.findElement(By.id("switchAccountLogin")).click();
        Thread.sleep(2000);
        WebElement element = browser.findElement(By.tagName("iframe"));
        browser.switchTo().frame(element);
        browser.findElement(LoginPage.emailinput).sendKeys(email);
        browser.findElement(LoginPage.pwdinput).sendKeys(password);
        browser.findElement(LoginPage.loginbutton).click();
    }
}
