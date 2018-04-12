package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.big.entity.Team;
import org.big.repository.UserRepository;
import org.big.service.TeamService;
import org.big.service.UserService;
import org.big.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;


/**
 *<p><b>TeamController的Rest风格类</b></p>
 *<p> TeamController的Rest风格类</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController  //返回json
@Controller
@RequestMapping("/console/team/rest")
public class TeamRestController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserTeamService userTeamService;
    @Autowired
    private UserRepository userRepository;
    /**
     *<b>列表</b>
     *<p> 当前用户所能查看权限的列表</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    @RequestMapping("/list")
    public JSON List(HttpServletRequest request) {
        return this.teamService.findbyUser(request);
    }

    /**
     *<b>删除多个</b>
     *<p> 根据id序列一次性删除多个</p>
     * @author WangTianshan (王天山)
     * @param ids id序列，用"￥"分隔
     * @return boolean
     */
    @RequestMapping(value="/removeMany/{ids}",method = {RequestMethod.GET})
    public boolean RemoveMany(@PathVariable String ids) {
        try{
            //获取id列表字符串
            String [] idList = ids.split("￥");
            for(int i=0;i<idList.length;i++){
                this.teamService.removeOne(idList[i]);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     *<b>删除单个</b>
     *<p> 根据id删除单个</p>
     * @author WangTianshan (王天山)
     * @param id 实体id
     * @return boolean
     */
    @RequestMapping(value="/remove/{id}",method = {RequestMethod.GET})
    public boolean Remove(@PathVariable String id,HttpServletRequest request) {
        try{
            request.getSession().setAttribute("operationError","");
            this.teamService.removeOneByUser(id);
            if(request.getSession().getAttribute("operationError").equals("authority")){
                request.getSession().setAttribute("operationError","");
                return false;
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     *<b>Team成员列表</b>
     *<p> 当前用户所能查看权限的Team成员列表</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    @RequestMapping("/memberList")
    public JSON MemberList(HttpServletRequest request) {
        return this.userService.findbyTeamId(request);
    }

    /**
     *<b>删除单个</b>
     *<p> 根据id删除单个</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return boolean
     */
    @RequestMapping(value="/removeMember",method = {RequestMethod.POST})
    public boolean RemoveMember(HttpServletRequest request) {
        System.out.println("====================");
        String teamId=request.getParameter("teamId");
        String userId=request.getParameter("userId");
        System.out.println("teamId="+teamId);
        System.out.println("userId="+userId);
        try{
            this.teamService.removeMembersByTeamIdAndUserId(teamId,userId);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     *<b>团队负责人授权</b>
     *<p> 团队负责人授权</p>
     * @author BINZI
     * @param request 页面请求
     * @return boolean
     */
    @RequestMapping(value="/transMember",method = {RequestMethod.POST})
    public boolean TransMember(HttpServletRequest request, HttpServletResponse response) {
    	String teamId=request.getParameter("teamId");
        String userId=request.getParameter("userId");
        Team team = this.teamService.findbyID(teamId);
        try{
        	this.teamService.updateTeamInfoByLeader(teamId, userId);
        	String email = this.userRepository.findOneById(userId).getEmail();
        	if (null != email && !"".contains(email)) {
        		this.userTeamService.SendEmailTransPermissionAdvice(request, response, email, team);
        	}
        	return true;
        }catch(Exception e){
        	return false;
        }
    }
}
