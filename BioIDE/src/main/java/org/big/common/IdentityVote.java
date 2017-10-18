package org.big.common;

import org.big.entity.Message;
import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.service.TeamServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *<p><b>权限判断</b></p>
 *<p> 根据传入的参数判断当前用户是否具有该操作的权限</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/28 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
public class IdentityVote {

    TeamServiceImpl teamServiceImpl = (TeamServiceImpl) SpringTool.getBean("teamServiceImpl");
    public UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    /**
     *<b>权限验证</b>
     *<p> 根据传入的关键参数返回当前用户是否具有该操作的权限</p>
     * @author WangTianshan (王天山)
     * @param targetName 切面类的名称关键标记
     * @param objectId 被操作对象的id
     * @return java.lang.Boolean
     */
    public Boolean hasAuthority(String targetName,String objectId){
        Boolean hasAuthority=false;
        switch (targetName){
            case "TeamServiceImpl":
                hasAuthority=isTeamLeaderByTeamId(objectId);
                break;
            default:
                break;
        }
        return hasAuthority;
    }

    /**
     *<b>根据TeamId判断是否具有权限</b>
     *<p> 根据传入的TeamId判断是否具有权限，超级管理员和该TeamLeader具有权限</p>
     * @author WangTianshan (王天山)
     * @param teamId Team的id
     * @return java.lang.Boolean
     */
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

    /**
     *<b>根据Message、Addressee判断是否具有修改已读状态权限</b>
     *<p> 根据Message、Addressee判断是否具有修改已读状态权限，超级管理员和该Addressee具有权限</p>
     * @author WangTianshan (王天山)
     * @param thisMessage 被判断的Message实体
     * @return java.lang.Boolean
     */
    public Boolean isAddresseeByMessage(Message thisMessage){
        //判断当前状态
        if(thisMessage.getStatus()==0){
            if(thisMessage.getAddressee().equals(thisUser.getId())){
                return true;
            }
        }
        return false;
    }
}
