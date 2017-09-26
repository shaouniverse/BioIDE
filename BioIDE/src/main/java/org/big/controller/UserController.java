package org.big.controller;

import org.big.entity.User;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.UUID;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    //add
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add(Model model) {
        User thisUser=new User();
        thisUser.setRole("user");
        model.addAttribute("thisUser", thisUser);
        return "user/add";
    }

    //edit
    @RequestMapping(value="/edit/{id}", method = {RequestMethod.GET})
    public String Edit(Model model,@PathVariable String id) {
        User thisUser=this.userService.findbyID(id);
        model.addAttribute("thisUser", thisUser);
        return "user/edit";
    }

    //save
    @RequestMapping(value="/save", method = {RequestMethod.POST})
    public String Save(@ModelAttribute("thisUser") User thisUser) {
        this.userService.saveOne(thisUser);
        return "redirect:/user";
    }


}
