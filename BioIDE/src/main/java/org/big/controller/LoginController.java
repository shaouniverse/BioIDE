package org.big.controller;

import org.big.entity.User;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Created by Tianshan on 17/5/23.
 */
@Controller   /* 返回页面*/
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private org.apache.catalina.servlet4preview.http.HttpServletRequest request;
    //login
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String login(Model model) {
        String loginErrorMsg="";
        try{
            if(request.getSession().getAttribute("loginError").equals("name")){
                model.addAttribute("loginError", 1);
                loginErrorMsg="无此用户名";
            }
            else if(request.getSession().getAttribute("loginError").equals("password")){
                model.addAttribute("loginError", 1);
                loginErrorMsg="密码错误";
            }
            else if(request.getSession().getAttribute("loginError").equals("token")){
                model.addAttribute("loginError", 1);
                loginErrorMsg="验证码错误";
            }
            else{
                model.addAttribute("loginError", 0);
            }
            model.addAttribute("loginErrorMsg", loginErrorMsg);
        }catch(Exception e){
        }
        return "login";
    }
}
