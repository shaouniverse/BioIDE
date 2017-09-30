package org.big.controller;

import org.big.service.TeamService;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    //test
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Test() {
        return "test";
    }
}
