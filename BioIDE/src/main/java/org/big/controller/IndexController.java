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
//            SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
//                    .getSession().getAttribute("SPRING_SECURITY_CONTEXT");
//            // 登录名
//            System.out.println("Username:"
//                    + securityContextImpl.getAuthentication().getName());
//            // 登录密码，未加密的
//            System.out.println("Credentials:"
//                    + securityContextImpl.getAuthentication().getCredentials());
//            WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl
//                    .getAuthentication().getDetails();
//            // 获得访问地址
//            System.out.println("RemoteAddress" + details.getRemoteAddress());
//            // 获得sessionid
//            System.out.println("SessionId" + details.getSessionId());
//            // 获得当前用户所拥有的权限
//            List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl
//                    .getAuthentication().getAuthorities();
//            for (GrantedAuthority grantedAuthority : authorities) {
//                System.out.println("Authority=" + grantedAuthority.getAuthority());
//            }
            UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return "redirect:/console/"+thisUser.getUsername();
            //return "redirect:/console/"+thisUser.getId();
        }
        catch(Exception e){
        }
        return "redirect:/console/1";
    }
}
