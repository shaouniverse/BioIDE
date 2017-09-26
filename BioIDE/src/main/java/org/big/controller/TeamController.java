package org.big.controller;

import org.big.entity.Team;
import org.big.service.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;

    //test
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String FindOne() {
        return "team/index";
    }

    //add
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add(Model model) {
        Team thisTeam=new Team();
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
        this.teamService.saveOne(thisTeam);
        return "redirect:/team";
    }


    //remove
    @RequestMapping(value="/remove/{id}", method = {RequestMethod.GET})
    public String Remove(@PathVariable String id) {
        this.teamService.removeOne(id);
        return "index";
    }

    //findOne
    @RequestMapping(value="/get/{id}", method = {RequestMethod.GET})
    public String FindOne(@PathVariable String id) {
        Team thisTeam=this.teamService.findbyID(id);
        return "test";
    }
}
