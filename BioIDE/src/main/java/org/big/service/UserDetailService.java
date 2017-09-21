package org.big.service;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.big.entity.Team;
import org.big.entity.User;
import org.big.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tianshan on 17/5/25.
 */
@Service("UserDetailImpl")
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.findOneByName(userName);
        } catch (Exception e) {
            throw new UsernameNotFoundException("读取数据库失败");
        }
        if(user == null){

            request.getSession().setAttribute("login_error","没有该用户");
            throw new UsernameNotFoundException("没有这个用户");
        } else {
            try {
                List<Team> teams = teamService.selectTeamByUserId(user.getId());
                return new UserDetail(user, teams);
            } catch (Exception e) {
                request.getSession().setAttribute("login_error","密码错误");
                throw new UsernameNotFoundException("密码不正确");
            }
        }
    }
}