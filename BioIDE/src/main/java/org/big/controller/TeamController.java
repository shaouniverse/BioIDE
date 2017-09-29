package org.big.controller;

import org.aspectj.lang.annotation.Before;
import org.big.common.IdentityVote;
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

import java.sql.Timestamp;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
@RequestMapping("/console/team")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private UserTeamServiceImpl userTeamService;

    //index
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Index() {
        return "team/myTeam";
    }

    //add
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add(Model model) {
        Team thisTeam=new Team();
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        thisTeam.setLeader(thisUser.getId());
        model.addAttribute("thisTeam", thisTeam);
        return "team/add";
    }

    //edit
    @RequestMapping(value="/edit/{id}", method = {RequestMethod.GET})
    public String Edit(Model model,@PathVariable String id) {
        Team thisTeam=this.teamService.findbyID(id);
        model.addAttribute("thisTeam", thisTeam);
        return "team/edit";
    }

    //save
    @RequestMapping(value="/save", method = {RequestMethod.POST})
    public String Save(@ModelAttribute("thisTeam") Team thisTeam) {
        this.teamService.saveOneByUser(thisTeam);
        return "redirect:/console/team";
    }


    //remove
    @RequestMapping(value="/remove/{id}", method = {RequestMethod.GET})
    public String Remove(@PathVariable String id) {
        this.teamService.removeOneByUser(id);
        return "index";
    }

}
