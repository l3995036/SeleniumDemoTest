package com.example.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SpringBootTest
public class RegisterTest extends AbstractTestNGSpringContextTests {
    WebDriver browser;
    @BeforeMethod
    public void openBrowser()  {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");//实例化对象
        browser=new ChromeDriver();
    }
    @Test
    public void registerTest() throws InterruptedException {
        browser.get("https://mail.163.com/");
        browser.findElement(By.xpath("//*[@id=\"commonOperation\"]/a[2]")).click();
        String windowHandle = browser.getWindowHandle();
        for(String handles:browser.getWindowHandles()){
            if (handles.equals(windowHandle))
                continue;
            else browser.switchTo().window(handles);
        }
        JavascriptExecutor js=(JavascriptExecutor)browser;
        js.executeScript("document.getElementsByClassName(\"custom-checkbox service\")[0].className=\"custom-checkbox service active\"");
        browser.findElement(By.id("username")).sendKeys("l3995036");
        browser.findElement(By.id("password")).sendKeys("liu3995036");
        browser.findElement(By.id("phone")).sendKeys("17507138713");
        browser.findElement(By.linkText("立即注册")).click();
        Thread.sleep(2000);
        //WebDriverWait wait=new WebDriverWait(browser,30);//显示等待，页面未加载，找不到元素的处理
       // wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("立即注册")));//注册页面在动
        browser.findElement(By.linkText("立即注册")).click();
        Thread.sleep(3000);
        String text = browser.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[3]/div[2]")).getText();
        System.out.println("uuu"+text);
    }
    @AfterMethod
    public void closeBrowser() {
        browser.quit();
    }
}
