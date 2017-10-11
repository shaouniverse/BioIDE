package org.big.common;

import org.big.entity.Message;
import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.service.TeamServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;

/**
 *<p><b>构造实体类</b></p>
 *<p> 将传入的java对象对应至已有的Entity实体</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 7-10-11 下午9:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
public class BuildEntity {
    /**
     *<b>构造TeamEntity</b>
     *<p> 将传入的java对象对应至已有的TeamEntity实体</p>
     * @author WangTianshan (王天山)
     * @param obj 传入的Object对象
     * @return thisTeam（Team）
     */
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
    /**
     *<b>构造MessageEntity</b>
     *<p> 将传入的java对象对应至已有的MessageEntity实体</p>
     * @author WangTianshan (王天山)
     * @param obj 传入的Object对象
     * @return thisMessage（Message）
     */
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
