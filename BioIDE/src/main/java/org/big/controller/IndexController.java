package org.big.controller;

import org.big.entity.User;
import org.big.entity.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
public class IndexController {
    //首页
    @RequestMapping(value="/", method = {RequestMethod.GET})
    public String Index(HttpServletRequest request) {
        try{
            UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return "redirect:/console/"+thisUser.getUsername();
        }
        catch(Exception e){
        }
        return "redirect:/console/1";
    }
}
