package org.big.common;

import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.service.TeamService;
import org.big.service.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

/**
 * Created by WangTianshan on 2017/9/28.
 */
public class IdentityVote {

    //TeamServiceImpl teamService = (TeamServiceImpl)ContextLoader.getCurrentWebApplicationContext().getBean("teamService");
    //TeamService teamService = ApplicationContext.getBean(TeamService.class);
    //private TeamServiceImpl teamService;
    @Autowired
    protected TeamServiceImpl teamService;
//    private static IdentityVote  identityVote ;
//    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
//    public void init() {
//        identityVote = this;
//        identityVote.teamService = this.teamService;
//        // 初使化时将已静态化的testService实例化
//    }

    public UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//    public static Boolean isTeamLeaderByTeam(Team thisTeam){
//        if (thisTeam.getLeader().equals(thisUser.getId()))
//            return true;
//        else
//            return false;
//    }

    public Boolean isTeamLeaderByTeamId(String teamId){
        System.out.println("===========");
        Team thisTeam=this.teamService.findbyID(teamId);
        System.out.println("teamId="+teamId);
        for (GrantedAuthority grantedAuthority : thisUser.getAuthorities()) {
            System.out.println(grantedAuthority.getAuthority());
            System.out.println("Authority" + grantedAuthority.getAuthority());
            if(grantedAuthority.getAuthority().equals("ROLE_SUPER")){
                return true;
            }
            else if(grantedAuthority.getAuthority().equals("ROLE_USER")){
                if (thisTeam.getLeader().equals(thisUser.getId()))
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
        return false;
    }
}
