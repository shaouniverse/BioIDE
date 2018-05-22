package org.big.controller;

import org.big.entity.Message;
import org.big.entity.Team;
import org.big.entity.User;
import org.big.entity.UserDetail;
import org.big.service.TeamService;
import org.big.service.UserService;
import org.big.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *<p><b>Team相关的Controller类</b></p>
 *<p> Team相关的Controller</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/12 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/console/team")
public class TeamController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserTeamService userTeamService;

    /**
     *<b>默认页面</b>
     *<p> 展示和自己有关团队的列表和操作选项</p>
     * @author WangTianshan (王天山)
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Index(Model model) {
    	Message thisMessage = new Message();
    	thisMessage.setStatus(0);
    	model.addAttribute("thisMessage", thisMessage);
        return "team/myTeam";
    }

    /**
     *<b>查看团队详情</b>
     *<p> 查看团队的详情</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @param id 实体的
     * @return java.lang.String
     */
    @RequestMapping(value="/details/{id}", method = {RequestMethod.GET})
    public String TeamDetails(Model model,@PathVariable String id) {
        Team thisTeam=this.teamService.findbyID(id);
        int members=this.teamService.countMembersByTeamId(id);
        User leader=this.userService.findbyID(thisTeam.getLeader());
        model.addAttribute("thisTeam", thisTeam);
        model.addAttribute("members", members);
        model.addAttribute("leader", leader);
        return "team/details";
    }

    /**
     *<b>添加</b>
     *<p> 添加新的实体的编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @return java.lang.String
     */
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add(Model model) {
        Team thisTeam=new Team();
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        thisTeam.setLeader(thisUser.getId());
        model.addAttribute("thisTeam", thisTeam);
        return "team/add";
    }

    /**
     *<b>编辑</b>
     *<p> 对已有的实体进行编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @param id 被编辑实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/edit/{id}", method = {RequestMethod.GET})
    public String Edit(Model model,@PathVariable String id) {
        Team thisTeam=this.teamService.findbyID(id);
        model.addAttribute("thisTeam", thisTeam);
        return "team/edit";
    }

    /**
     *<b>保存</b>
     *<p> 将传入的实体保存</p>
     * @author WangTianshan (王天山)
     * @param thisTeam 传入的实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/save", method = {RequestMethod.POST})
    public String Save(@ModelAttribute("thisTeam") Team thisTeam) {
        if (null != thisTeam.getNote() && !"".equals(thisTeam.getNote()) && "Default".equals(thisTeam.getNote())) {
			thisTeam.setNote(thisTeam.getNote().toLowerCase());
		}
    	this.teamService.saveOneByUser(thisTeam);
        return "redirect:/console/team";
    }

    /**
     *<b>删除</b>
     *<p> 将传入的实体删除</p>
     * @author WangTianshan (王天山)
     * @param id 传入的实体id
     * @return java.lang.String      
     */
    @RequestMapping(value="/remove/{id}", method = {RequestMethod.GET})
    public String Remove(@PathVariable String id) {
        this.teamService.removeOneByUser(id);
        return "index";
    }
    
    /** 处理用户邀请连接
     *<b>团队邀请</b>
     *<p> 团队邀请通过UserId & TeamId</p>
     * @author BINZI
     * @param id 传入的实体id
     * @return java.lang.String 
     */
    @RequestMapping(value="/invite/{userName}/{teamid}", method = {RequestMethod.GET})
    public String Invite(@PathVariable String userName, @PathVariable String teamid) {
    	// 用户接收邀请
    	userTeamService.saveOne(userService.findOneByName(userName).getId(), teamid);
    	return "redirect:/console/team";
    }
}
