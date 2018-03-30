package org.big.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.big.entity.User;
import org.big.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {
    
	@Autowired
    private UserService userService;
    @Value("${spring.mail.username}") 	// BINZIDYB01@yeah.net
    private String fromEmail;
    
    /**
     *<b>注册页面</b>
     *<p> 注册页面</p>
     * @author WangTianshan (王天山)
     * @param model 空的model
     * @return java.lang.String
     */
	@RequestMapping(value="", method = {RequestMethod.GET})
	public String register(Model model) {
		User newUser =new User();
        newUser.setRole("ROLE_USER");
        model.addAttribute("newUser", newUser);	// model到浏览器 -- 用于权限
		return "user/register";
	}

	@RequestMapping(value="/new", method = {RequestMethod.POST})
    public String newUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("newUser") User newUser, Model model) {
		String registerMsg=this.userService.registerNewOne(request,response,newUser);
        if(registerMsg.equals("success")){
            request.getSession().setAttribute("registerEmail",newUser.getEmail());
            request.getSession().setAttribute("adminEmail",fromEmail);
            return "redirect:/register/success";
        }
        else{
            model.addAttribute("newUser", newUser);
            model.addAttribute("errorMsg", registerMsg);
            return "user/register";
        }
    }
	
	/**
     *<b>注册成功页面</b>
     *<p> 注册成功页面</p>
     * @author WangTianshan (王天山)
     * @return java.lang.String
     */
    @RequestMapping(value="/success")
    public String success(Model model,HttpServletRequest request) {
        return "user/registerSuccess";
    }
    
    /**
     *<b>激活结果页面</b>
     *<p> 激活结果页面</p>
     * @author WangTianshan (王天山)
     * @return java.lang.String
     */
    // http://localhost:8081/register/active/BINZI/437b1ebe-cd79-4979-a5ca-73071f6368a3/
    @RequestMapping(value="/active/{userName}/{mark}")
    public String active(Model model,HttpServletRequest request, HttpServletResponse response, @PathVariable("userName") String userName, @PathVariable("mark") String mark) {
        System.out.println("激活用户：" + userName);
    	System.out.println("激活码：" + mark);
    	
    	String activeMsg = userService.activeUser(userName,mark,request,response);	//返回用户状态 -- 
        model.addAttribute("activeMsg", activeMsg);
        request.getSession().setAttribute("adminEmail",fromEmail);
        if(activeMsg.equals("此账户已激活")||activeMsg.equals("This account has been activated")){
            model.addAttribute("activeStatus", true);
            model.addAttribute("loginUsername", userName);
        }
        else{
            model.addAttribute("activeStatus", false);
        }
        return "/login";	// 激活成功跳到登录页面 -- 该页面提示注册用户 -- "用户已激活"
    }
}
