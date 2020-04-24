package com.example.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class PhantomJsTest {
        @Test
        public void phantomJsTest() throws InterruptedException {
            System.setProperty("phantomjs.binary.path","D:\\WebDriver\\phantomjs.exe");
            WebDriver browser=new PhantomJSDriver();
            browser.get("http://www.baidu.com");
            browser.findElement(By.id("kw")).sendKeys("hehe");//通过属性id定位
            Thread.sleep(2000);
            browser.quit();
        }
}
