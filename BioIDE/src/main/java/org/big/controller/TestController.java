package org.big.controller;

import org.big.common.Children;
import org.big.common.IdentityVote;
import org.big.service.TeamService;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Timestamp;
import java.util.List;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TeamService teamService;

    @Autowired
    private Children a;

    //test
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Test() {
        a.run("wts");
        a.run2("wts1");
        return "test";
    }
}
