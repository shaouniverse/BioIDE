package org.big.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
public class IndexController {
    //首页
    @RequestMapping(value="/", method = {RequestMethod.GET})
    public String Index() {

        return "redirect:/console/1";
    }
}
