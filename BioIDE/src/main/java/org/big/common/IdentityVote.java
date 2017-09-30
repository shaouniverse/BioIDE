package org.big.common;

import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.service.TeamServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by WangTianshan on 2017/9/28.
 */
public class IdentityVote {

    TeamServiceImpl teamServiceImpl = (TeamServiceImpl) SpringTool.getBean("teamServiceImpl");
    public UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public Boolean isTeamLeaderByTeamId(String teamId){
        Team thisTeam = teamServiceImpl.findbyID(teamId);
        for (GrantedAuthority grantedAuthority : thisUser.getAuthorities()) {
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
