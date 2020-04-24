package com.example.learning;

import com.example.learning.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

@SpringBootTest

public class UserLoginTest extends AbstractTestNGSpringContextTests {
    @Autowired
    UserController userController;
    @BeforeTest
    public void beforeTestDmeo(){
        System.out.println("这是BeforeTest注解");//test方法运行之前运行，只运行一次
    }
    @BeforeMethod
    public void beforeMethodDemo(){
        System.out.println("这是BeforeMethod注解");//test方法运行之前都会运行
    }
    @Test
    public void userLoginTestS(){
        boolean userLogin = userController.userLogin(2);
        Assert.assertEquals(userLogin,true,"登录成功");
        Assert.assertTrue(userLogin);
        Assert.assertNotEquals(userLogin,true);
    }
    @Test()
    /*
    @Test(timeOut=3000ms) //超时测试
    @Test(expectedException=)//异常测试
    @Test(thred=3)//并发测试
    @Test(dependmethod={"tester"})//依赖测试
     */
    public void userLoginTestF(){
        boolean userLogin = userController.userLogin(11);
        Assert.assertEquals(userLogin,false,"登录失败");
        Assert.assertFalse(false);
    }
    @AfterTest
    public void afterTestDemo(){
        System.out.println("这是AfterTest注解");
    }
    @AfterMethod
    public void afterMethodDemo(){
        System.out.println("这是AfterMethod注解");
    }
}
