package org.big.common;

import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by WangTianshan on 2017/9/28.
 */
public class IdentityVote {
    private static UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public static Boolean isTeamLeader(Team thisTeam){
        System.out.println("=======vvvvvvvvvv======");
        if (thisTeam.getLeader().equals(thisUser.getId()))
            return true;
        else
            return false;
    }
}
