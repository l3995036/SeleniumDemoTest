package com.example.learning;

import ch.qos.logback.core.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.util.FileCopyUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OperatElementsTest {
    WebDriver browser;

    @BeforeTest
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");//实例化对象
        browser = new ChromeDriver();
    }

    @Test
    public void clickTest() {
        browser.get("http://www.baidu.com");
        WebElement news = browser.findElement(By.linkText("新闻"));//定位新闻链接
        news.click();//点击新闻链接
        String currentUrl = browser.getCurrentUrl();//获取当前页面链接
        Assert.assertEquals(currentUrl, "http://news.baidu.com/");
    }

    @Test
    public void sendkeysTest() throws InterruptedException {
        browser.get("http://www.baidu.com");
        WebElement kw = browser.findElement(By.id("kw"));
        kw.sendKeys("selenium");
        WebElement su = browser.findElement(By.id("su"));
        su.click();
        Thread.sleep(2000);

    }

    @Test
    public void clearTest() {
        browser.get("http://www.baidu.com");
        WebElement kw = browser.findElement(By.id("kw"));
        kw.sendKeys("selenium");
        kw.clear();
    }

    @Test
    public void getATest() {
        browser.get("http://www.baidu.com");
        String attribute = browser.findElement(By.id("su")).getAttribute("value");
        boolean su = browser.findElement(By.id("su")).isDisplayed();
        boolean su1 = browser.findElement(By.id("su")).isSelected();
        boolean su2 = browser.findElement(By.id("su")).isEnabled();
        Assert.assertEquals(attribute, "百度一下");
        Assert.assertTrue(su);
    }

    @Test
    public void shotTest() throws InterruptedException {
        browser.get("http://www.baidu.com");
        File screenshotAs = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        try {
            FileCopyUtils.copy(screenshotAs,new File("D:\\new\\shot\\test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void alertTest() throws InterruptedException {
        browser.get("D:\\new\\shot\\test.html");
        List<WebElement> input = browser.findElements(By.tagName("input"));
        WebElement alert =input.get(0);
        alert.click();
        Thread.sleep(2000);
        Alert alert1 = browser.switchTo().alert();
        alert1.accept();
        Thread.sleep(2000);
    }
    @Test
    public void comfirmTest() throws InterruptedException {
        browser.get("D:\\new\\shot\\test.html");
        WebElement comfirm = browser.findElement(By.id("comfirm"));
        comfirm.click();
        Thread.sleep(2000);
        Alert alert = browser.switchTo().alert();
        alert.dismiss();
        Thread.sleep(2000);
        alert.accept();
        Thread.sleep(1000);
    }
    @Test
    //ChromeDriver不支持，firfox支持
    public void promptTest() throws InterruptedException {
        String time = String.valueOf(System.currentTimeMillis() / 100);
        browser.get("D:\\new\\shot\\test.html");
        WebElement prompt = browser.findElement(By.id("prompt"));
        prompt.click();
        Thread.sleep(2000);
        Alert alert = browser.switchTo().alert();
        alert.sendKeys("test");
        Thread.sleep(2000);
        alert.accept();
        alert.sendKeys("22");
        alert.accept();
        Thread.sleep(2000);
    }
    @Test
    public void iframe() throws InterruptedException {
        browser.get("http://yanshi.sucaihuo.com/modals/40/4078/demo/");
        browser.findElement(By.xpath("//*[@id=\"side-menu\"]/li[6]/a/span[1]")).click();
        Thread.sleep(1000);
        browser.findElement(By.xpath("//*[@id=\"side-menu\"]/li[6]/ul/li[1]/a")).click();
        Thread.sleep(1000);
        WebElement iframe19 = browser.findElement(By.name("iframe19"));//多层iframe嵌套，多次使用
        browser.switchTo().frame(iframe19);
        WebElement element = browser.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div/div[2]/div/div[1]/form/div[1]/input"));
        element.sendKeys("1627726534");
        Thread.sleep(1000);
        browser.switchTo().defaultContent();
    }
    @Test
    public void selectTest() throws InterruptedException {
        browser.get("D:\\new\\shot\\test.html");
        WebElement webElementselect = browser.findElement(By.tagName("select"));
        Select select =new Select(webElementselect);
        select.selectByIndex(1);
        Thread.sleep(2000);
        select.selectByValue("选项3");
        Thread.sleep(2000);
        select.selectByVisibleText("选项 4");//标签中间文本值
        Thread.sleep(2000);

    }
    @AfterTest
    public void closeBrowser() {
        browser.quit();
    }

}
