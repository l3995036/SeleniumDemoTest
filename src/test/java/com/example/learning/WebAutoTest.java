package com.example.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


@SpringBootTest
public class WebAutoTest extends AbstractTestNGSpringContextTests {
    WebDriver browser;
    @BeforeMethod
    public void openBrowser()  {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");//实例化对象
        browser=new ChromeDriver();
    }
    @Test
    public void browserTester1() throws InterruptedException {
        browser.get("http://www.baidu.com");
        Thread.sleep(3000);//线程等待，抛出异常
        browser.navigate().back();//后退
        Thread.sleep(3000);
        browser.navigate().forward();//前进
        Thread.sleep(3000);
        browser.navigate().refresh();//刷新
    }

    @Test
    public void browserTester2() throws InterruptedException {
        browser.get("http://www.baidu.com");
        Thread.sleep(2000);
        Dimension Demension = new Dimension(400, 400);//设置浏览器窗口大小
        browser.manage().window().setSize(Demension);
        browser.manage().window().maximize();//浏览器窗口最大化
        String currentUrl = browser.getCurrentUrl();//获取浏览器当前url
        System.out.println("currentUrl:" + currentUrl);
        String title = browser.getTitle();//获取浏览器当前页面的标题
        System.out.println("title:" + title);
        Thread.sleep(3000);
        Assert.assertEquals(currentUrl, "https://www.baidu.com/");
    }
    @AfterMethod
    public void closeBrowser() {
        // browser.close();//关闭当前窗口
        browser.quit();
    }
}
