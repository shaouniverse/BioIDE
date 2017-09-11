package org.big.controller;

import org.big.entity.User;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.sql.Timestamp;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserServiceImpl userService;

    //test
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String FindOne() {
        return "test";
    }

    //add
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add() {
        User thisUser=new User();
        thisUser.setId("2");
        thisUser.setEmail("www");
        thisUser.setPassword("www");
        thisUser.setRole("www");
        thisUser.setPhone("www");
        thisUser.setUserName("www");
        thisUser.setAdddate(new Timestamp(System.currentTimeMillis()));
        this.userService.addNew(thisUser);
        return "test";
    }

    //remove
    @RequestMapping(value="/remove/{id}", method = {RequestMethod.GET})
    public String Remove(@PathVariable String id) {
        this.userService.removeOne(id);
        return "index";
    }

    //update
    @RequestMapping(value="/update", method = {RequestMethod.GET})
    public String Update() {
        //this.userService.findbyID("1");
        User thisUser=new User();
        thisUser.setId("2");
        thisUser.setEmail("www");
        thisUser.setPassword("www");
        thisUser.setRole("www");
        thisUser.setPhone("www");
        thisUser.setUserName("www");
        thisUser.setAdddate(new Timestamp(System.currentTimeMillis()));
        this.userService.addNew(thisUser);
        return "test";
    }

    //findOne
    @RequestMapping(value="/findone/{id}", method = {RequestMethod.GET})
    public String FindOne(@PathVariable String id) {
        User thisUser=this.userService.findbyID(id);
        System.out.println("=======");
        //System.out.println(thisUser.getUserName());
        System.out.println("=======");
        return "test";
    }
}
