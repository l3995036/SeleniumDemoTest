package com.example.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringBootTest
public class SendTest extends AbstractTestNGSpringContextTests {
    WebDriver browser;
    @BeforeMethod
    public void openBrowser()  {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");//实例化对象
        browser=new ChromeDriver();
    }
    @Test
    public void sendTest() throws InterruptedException {
        LoginTest.loginTest(browser,"l3995036","liu3995036");
        WebDriverWait wait=new WebDriverWait(browser,30);//显示等待，页面未加载，找不到元素的处理
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"dvNavTop\"]/ul/li[2]")));
        browser.findElement(By.xpath("//*[@id=\"dvNavTop\"]/ul/li[2]")).click();
        browser.findElement(By.className("nui-editableAddr-ipt")).sendKeys("l3995036@163.com");
        browser.findElement(By.xpath("//*[@aria-label=\"邮件主题输入框，请输入邮件主题\"]/input")).sendKeys("test");
        WebElement element = browser.findElement(By.className("APP-editor-iframe"));
        browser.switchTo().frame(element);
        browser.findElement(By.xpath("//*[@class=\"nui-scroll\"]/p")).sendKeys("test23");
        browser.switchTo().defaultContent();
        browser.findElement(By.xpath("//*[text()=\"发送\"]")).click();//通过文本定位元素text()
        /*
        后续定位发送成功页面元素，进行校验
         */
    }
/*    @AfterMethod
    public void closeBrowser() {
        browser.quit();
    }*/
}
