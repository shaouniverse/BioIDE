package org.big.controller;

import org.big.common.MD5Utils;
import org.big.entity.Message;
import org.big.entity.Team;
import org.big.entity.User;
import org.big.service.MessageService;
import org.big.service.TeamService;
import org.big.service.UserService;
import org.big.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *<p><b>超级管理员相关的Controller类</b></p>
 *<p> 超级管理员相关的Controller</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/12 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/super")
public class SuperController {

    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserTeamService userTeamService;
    /********************** Team管理页 ***************************/
    /**
     *<b>Team管理页</b>
     *<p> 包含所有Team的信息列表、操作选项</p>
     * @author WangTianshan (王天山)
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value="/team", method = {RequestMethod.GET})
    public String ViewTeam() {
        return "team/index";
    }

    /**
     *<b>添加Team</b>
     *<p> 添加新的Team的编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @return java.lang.String
     */
    @RequestMapping(value="/team/add", method = {RequestMethod.GET})
    public String AddTeam(Model model) {
        Team thisTeam=new Team();
        model.addAttribute("thisTeam", thisTeam);
        return "team/add";
    }

    /**
     *<b>编辑Team</b>
     *<p> 对已有的Team进行编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @param id 被编辑Team实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/team/edit/{id}", method = {RequestMethod.GET})
    public String EditTeam(Model model,@PathVariable String id) {
        Team thisTeam=this.teamService.findbyID(id);
        model.addAttribute("thisTeam", thisTeam);
        return "team/edit";
    }

    /**
     *<b>保存Team</b>
     *<p> 将传入的Team实体保存</p>
     * @author WangTianshan (王天山)
     * @param thisTeam 传入的Team实体
     * @return java.lang.String
     */
    @RequestMapping(value="/team/save", method = {RequestMethod.POST})
    public String SaveTeam(@ModelAttribute("thisTeam") Team thisTeam) {
        this.teamService.saveOne(thisTeam);
        return "redirect:/super/team";
    }

    /**
     *<b>删除Team</b>
     *<p> 将传入的Team实体删除</p>
     * @author WangTianshan (王天山)
     * @param id 传入的Team实体的id
     * @return java.lang.String
     */
    @RequestMapping(value="/team/remove/{id}", method = {RequestMethod.GET})
    public String RemoveTeam(@PathVariable String id) {
        this.teamService.removeOne(id);
        return "index";
    }
    
    /**
     *<b>处理Team邀请 -- 托管MessageController</b>
     *<p> Team邀请</p>
     * @author WangTianshan (王天山)
     * @param id 传入的Team实体的id
     * @return java.lang.String
     */
/*    @RequestMapping(value="/team/invite/{id}", method = {RequestMethod.GET})
    public String InviteTeam(@PathVariable String id) {
        this.teamService.inviteOne(id);
    	System.out.println("邀请TeamID：" + id);
        return "message/compose";
    }*/

    
    /********************** User管理页 ***************************/
    /**
     *<b>User管理页</b>
     *<p> 包含所有User的信息列表、操作选项</p>
     * @author WangTianshan (王天山)
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value="/user", method = {RequestMethod.GET})
    public String ViewUser() {
        return "user/index";
    }

    /**
     *<b>添加User</b>
     *<p> 添加新的User的编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @return java.lang.String
     */
    @RequestMapping(value="/user/add", method = {RequestMethod.GET})
    public String AddUser(Model model) {
        User thisUser=new User();
        thisUser.setRole("user");
        model.addAttribute("thisUser", thisUser);
        return "user/add";
    }

    /**
     *<b>编辑User</b>
     *<p> 对已有的User进行编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @param id 被编辑User实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/user/edit/{id}", method = {RequestMethod.GET})
    public String EditUser(Model model,@PathVariable String id) {
        User thisUser=this.userService.findbyID(id);
        model.addAttribute("thisUser", thisUser);
        return "user/edit";
    }

    /**
     *<b>保存User</b>
     *<p> 将传入的User实体保存</p>
     * @author WangTianshan (王天山)
     * @param thisUser 传入的User实体
     * @return java.lang.String
     */
    @RequestMapping(value="/user/save", method = {RequestMethod.POST})
    public String SaveUser(@ModelAttribute("thisUser") User thisUser) {
    	thisUser.setPassword(MD5Utils.MD532(thisUser.getPassword()));
        this.userService.saveOne(thisUser);
        return "redirect:/super/user";
    }

    /**
     *<b>删除User</b>
     *<p> 将传入的User实体删除</p>
     * @author WangTianshan (王天山)
     * @param id 传入的User实体的id
     * @return java.lang.String
     */
    @RequestMapping(value="/user/remove/{id}", method = {RequestMethod.GET})
    public String RemoveUser(@PathVariable String id) {
        this.userService.removeOne(id);
        return "index";
    }

    /**
     *<b>Message管理页</b>
     *<p> 包含所有Message的信息列表、操作选项</p>
     * @author WangTianshan (王天山)
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value="/message", method = {RequestMethod.GET})
    public String ViewMessage() {
        return "message/index";
    }

    /**
     *<b>查看Message详情</b>
     *<p> 查看所有消息的详情，不能变更消息状态</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @param id 实体的id
     * @return java.lang.String
     */
    @RequestMapping(value="/message/read/{id}", method = {RequestMethod.GET})
    public String ReadMessage(Model model,@PathVariable String id) {
        Message thisMessage=this.messageService.findbyID(id);
        User thisSender=this.userService.findbyID(thisMessage.getSender());
        model.addAttribute("thisMessage", thisMessage);
        model.addAttribute("thisSender", "From:"+thisSender.getNickname());
        return "message/read_admin";
    }
}
