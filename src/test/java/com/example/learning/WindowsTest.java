package com.example.learning;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WindowsTest {
    WebDriver browser;
    private Object until;

    @BeforeMethod
    public void openBrowser()  {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");//实例化对象
        browser=new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//全局等待
    }
    //两个窗口切换，通过句柄值定位到目标窗口
    @Test
    public void windowTest() throws InterruptedException {
        browser.get("D:\\new\\shot\\test.html");
        browser.findElement(By.linkText("百度")).click();
        String windowHandle = browser.getWindowHandle();//获取当前页面的句柄值
        for (String handles:browser.getWindowHandles()){
        if (handles.equals(windowHandle))
            continue;
            browser.switchTo().window(handles);
        }
        //Thread.sleep(2000);//线程等待
        WebDriverWait wait=new WebDriverWait(browser,30);//显示等待，页面未加载，找不到元素的处理
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("百度")));
        browser.findElement(By.linkText("新闻")).click();
    }
   //鼠标右击
    @Test
    public void rightCliTest() throws InterruptedException {
        browser.get("https://www.baidu.com/");
        Actions action=new Actions(browser);
        WebElement bdbutton = browser.findElement(By.id("su"));
        action.contextClick(bdbutton).perform();//百度按钮右键
        action.doubleClick(bdbutton).perform();//双击百度按钮
        WebElement news = browser.findElement(By.linkText("新闻"));
        action.moveToElement(news).perform();//鼠标移到某个元素，显示相应文本信息text()="XX"
        action.dragAndDropBy(bdbutton,300,300).perform();//元素拖动
        action.clickAndHold(bdbutton).moveToElement(news).release(bdbutton).perform();//选中某个元素拖动到另一个元素位置，后释放
        Thread.sleep(2000);
    }
    @Test
    public void mSelectTest() throws InterruptedException {
        browser.get("D:\\new\\shot\\test.html");
        browser.findElement(By.xpath("/html/body/select"));
        List<WebElement> elements = browser.findElements(By.xpath("/html/body/select/option"));
        Actions actions=new Actions(browser);
        actions.keyDown(Keys.SHIFT).click(elements.get(0)).click(elements.get(1)).perform();
        Thread.sleep(10000);
    }
    @Test
    public void keyTest() throws AWTException, InterruptedException {
        browser.get("https://www.baidu.com/");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
    }
    @Test
    public void uploadTest() throws InterruptedException {
        browser.get("http://yanshi.sucaihuo.com/modals/40/4078/demo/");
        browser.findElement(By.xpath("//*[@id=\"side-menu\"]/li[12]/a")).click();
        browser.findElement(By.linkText("表单构建器")).click();
        WebElement iframe87 = browser.findElement(By.name("iframe87"));
        browser.switchTo().frame(iframe87);
        browser.findElement(By.xpath("/html/body/div/div/div[1]/div/div[2]/form/div[4]/div/input")).sendKeys("D:\\new\\shot\\test.png");
        Thread.sleep(2000);
    }
    @Test
    public void downloadTest(){
        FirefoxProfile fp=new FirefoxProfile();
        ChromeOptions fp1=new ChromeOptions();
    }
    //修改前端正则表达式，绕过前端校验
    @Test
    public void jsTest() throws InterruptedException {
        browser.get("https://www.baidu.com/");
        JavascriptExecutor js=(JavascriptExecutor)browser;
        js.executeScript("document.getElementById(\"kw\").setAttribute(\"value\",\"123\")");
        Thread.sleep(5000);
    }
    @AfterMethod
    public void closeBrowser()
    {
        browser.quit();
    }
}
