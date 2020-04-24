package com.example.learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest
public class FindElementsTest extends AbstractTestNGSpringContextTests {
    WebDriver browser;
    @BeforeTest
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");//实例化对象
        browser=new ChromeDriver();
    }
    @Test
    //查找元素，通过id
    public void byIdTest(){
        browser.get("http://www.baidu.com");
        WebElement kw = browser.findElement(By.id("kw"));//通过属性id定位
    }
    @Test
    //查找元素，通过name
    public void byNameTest(){
        browser.get("http://www.baidu.com");
        WebElement wd = browser.findElement(By.name("wd"));//定位百度搜索输入框
    }
    //查找元素，通过class
    @Test
    public void byClassTest(){
        browser.get("http://www.baidu.com");
        WebElement s_ipt = browser.findElement(By.className("s_ipt"));
    }
    @Test
    //查找元素，通过linkText
    public void byLinkTest() {
        browser.get("http://www.baidu.com");
        WebElement kw = browser.findElement(By.linkText("地图"));//a标签中文本
        WebElement kw1 = browser.findElement(By.partialLinkText("地"));//a标签中部分文本
    }
    @Test
    //查找元素，通过tagname
    public void byTagnameTest() {
        browser.get("http://www.baidu.com");
        List<WebElement> input = browser.findElements(By.tagName("input"));
        System.out.println(input.get(3));
    }
    @Test
    //查找元素，通过xpath
    public void byXpathTest() {
        browser.get("http://www.baidu.com");
        WebElement element = browser.findElement(By.xpath("//*[@id=\"su\"]"));
    }
    @Test
    //查找元素，通过css
    public void byCssTest() {
        browser.get("http://www.baidu.com");
        WebElement element1 = browser.findElement(By.cssSelector("#lg"));
    }
    @AfterTest
    public void closeBrowser(){
       browser.quit();
    }
}
