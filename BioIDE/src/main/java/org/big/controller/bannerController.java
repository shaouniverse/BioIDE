package org.big.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
@RequestMapping("/banner")
public class bannerController {
    //首页
    @RequestMapping(value="/introduce", method = {RequestMethod.GET})
    public String Introduce() {

        return "banner/introduce";
    }
}
