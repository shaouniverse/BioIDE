package org.big.controller;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.big.entity.Dataset;
import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.service.DatasetService;
import org.big.service.MessageService;
import org.big.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *<p><b>控制台相关的Controller类</b></p>
 *<p> 控制台相关的Controller</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private DatasetService datasetService;
    @Autowired
    private TeamService teamService;

    /**
     *<b>默认页面</b>
     *<p> 登录后自动跳转的控制台默认页面</p>
     * @author WangTianshan (王天山)
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value="/{consoleId}", method = {RequestMethod.GET})
    public String Index(Model model, @PathVariable String consoleId, HttpServletRequest request) {
    	UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // 判断当前用户是否有consoleId对应的Team
    	List<Team> teamList = this.teamService.selectTeamsByUserId(thisUser.getId());
    	for (Team team : teamList) {
			if (team.getId().equals(consoleId)) {
				// 查出当前TeamId下的所有数据集 -- 响应到console/index页面
				List<Dataset> dsList = this.datasetService.findAllDatasetsByTeamId(consoleId);
				model.addAttribute("team", this.teamService.findbyID(consoleId));
				model.addAttribute("dsList", dsList);
				int unReadMessageNum = messageService.countStatus(0);
				request.getSession().setAttribute("unReadMessageNum", unReadMessageNum);
				return "console/index";
			}
		}
    	return "redirect:/select/team";
	}
}
