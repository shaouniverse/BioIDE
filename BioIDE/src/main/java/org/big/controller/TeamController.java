package org.big.controller;

import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.service.TeamServiceImpl;
import org.big.service.UserTeamServiceImpl;
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
    private TeamServiceImpl teamService;
    @Autowired
    private UserTeamServiceImpl userTeamService;

    /**
     *<b>默认页面</b>
     *<p> 展示和自己有关团队的列表和操作选项</p>
     * @author WangTianshan (王天山)
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Index() {
        return "team/myTeam";
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

}
