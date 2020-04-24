package com.example.learning.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //请求控制器，接收请求
@RequestMapping("/index") //控制器请求地址

public class UserController {
    @RequestMapping("/userLogin")
    public boolean userLogin(int a){
        if(a>0&&a<10){
            return true;
        }
        return false;
    }
}
