package org.big.common;

import org.big.entity.Message;
import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.service.TeamServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;

/**
 * Created by WangTianshan on 2017/9/28.
 */
public class BuildEntity {
    public static Team buildTeam(Object obj){
        Team thisTeam=new Team();
        Object[] objs = (Object[]) obj;
        thisTeam.setId((String)objs[0]);
        thisTeam.setName((String)objs[1]);
        thisTeam.setLeader((String)objs[2]);
        thisTeam.setNote((String)objs[3]);
        thisTeam.setAdddate((Timestamp) objs[4]);
        return thisTeam;
    }
    public static Message buildMessage(Object obj){
        Message thisMessage=new Message();
        Object[] objs = (Object[]) obj;
        thisMessage.setId((String)objs[0]);
        thisMessage.setSender((String)objs[1]);
        thisMessage.setAddressee((String)objs[2]);
        thisMessage.setSendtime((Timestamp)objs[3]);
        thisMessage.setTitle((String) objs[4]);
        thisMessage.setText((String) objs[5]);
        thisMessage.setStatus((int) objs[6]);
        thisMessage.setType((String) objs[7]);
        thisMessage.setMark((String) objs[8]);
        return thisMessage;
    }
}
