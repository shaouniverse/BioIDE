package org.big.controller;

import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.entity.UserTeam;
import org.big.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *<p><b>IndexController类</b></p>
 *<p> Index的Controller</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
public class IndexController {
    @Autowired
    private TeamService teamService;

    /**
     *<b>默认页面</b>
     *<p> 登录后自动跳转的主页面</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return java.lang.String
     */
    @RequestMapping(value="/", method = {RequestMethod.GET})
    public String Index(HttpServletRequest request) {
        try{
            UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return "redirect:/select/team";
        }
        catch(Exception e){
        }
        return "redirect:/login";
    }

//    @RequestMapping(value="/", method = {RequestMethod.GET})
//    public String Index(HttpServletRequest request) {
//        try{
//            UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            return "redirect:/console/"+thisUser.getUsername();
//        }
//        catch(Exception e){
//        }
//        return "redirect:/console/1";
//    }

    /**
     *<b>选择团队</b>
     *<p> 登录后选择团队页面</p>
     * @author WangTianshan (王天山)
     * @return java.lang.String
     */
    @RequestMapping(value="/select/team", method = {RequestMethod.GET})
    public String SelectTeam(Model model) {
        try{
            UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            // cannot be cast to org.big.entity.Team -- 多表查询返回的Object对象无法转成Team对象
            List<Team> teamList = this.teamService.selectTeamByUserId(thisUser.getId());//根据user id查找所有team
            model.addAttribute("teamList", teamList);
            return "team/select";
        }
        catch(Exception e){
        }
        return "redirect:/login";
    }

    /**
     *<b>变换团队</b>
     *<p> 选择团队后变换团队</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return java.lang.String
     */
    @RequestMapping(value="/change/team/{teamId}", method = {RequestMethod.GET})
    public String ChangeTeam(HttpServletRequest request, @PathVariable String teamId) {
        return "redirect:/console/"+teamId;
    }
}
