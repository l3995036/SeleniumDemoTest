package com.example.learning;

import com.example.learning.seleniumtest.LoginPage;
import com.example.learning.seleniumtest.logic.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@SpringBootTest
public class LoginTest {
    WebDriver browser;
    @BeforeMethod
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDriver\\chromedriver.exe");//实例化对象
        browser = new ChromeDriver();
    }
   @DataProvider(name= "userlist")
    public Object[][] dataTest(){
        return new Object[][]{
                {"l3995036","liu3995036"},
                {"l33995036","liu399503"}
        };
    }
    @Test (dataProvider = "userlist")
        public void loginSucessTest(String username,String pwd ) throws InterruptedException {
        Login.loginTest(browser,username,pwd);
        WebDriverWait wait=new WebDriverWait(browser,30);//显示等待，页面未加载，找不到元素的处理
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("退出")));
        String logout = browser.findElement(By.linkText("退出")).getText();
        Assert.assertEquals(logout,"退出");
    }
    @Test
    public void loginFailTest() throws InterruptedException {
        Login.loginTest(browser,"l3995036","liu399503");
        WebDriverWait wait=new WebDriverWait(browser,30);//显示等待，页面未加载，找不到元素的处理
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nerror\"]/div[2]")));
        String errmsg = browser.findElement(By.xpath("//*[@id=\"nerror\"]/div[2]\n")).getText();
        Assert.assertEquals(errmsg,"帐号或密码错误");
    }
/*    public static void loginTest(WebDriver browser,String email,String password) throws InterruptedException {
        browser.get("https://mail.163.com/");
        browser.manage().window().maximize();
        browser.findElement(By.id("switchAccountLogin")).click();
        Thread.sleep(2000);
        WebElement element = browser.findElement(By.tagName("iframe"));
        browser.switchTo().frame(element);
        browser.findElement(By.name("email")).sendKeys(email);
        browser.findElement(By.name("password")).sendKeys(password);
        browser.findElement(By.id("dologin")).click();
    }*/
    @AfterMethod
    public void closeBrowser() {
        browser.quit();
    }
}
