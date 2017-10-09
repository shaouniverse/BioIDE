package org.big.controller;

import org.big.entity.Team;
import org.big.entity.User;
import org.big.service.TeamServiceImpl;
import org.big.service.UserServiceImpl;
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
@RequestMapping("/super")
public class SuperController {

    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private UserServiceImpl userService;

    //team-index
    @RequestMapping(value="/team", method = {RequestMethod.GET})
    public String ViewTeam() {
        return "team/index";
    }

    //team-add
    @RequestMapping(value="/team/add", method = {RequestMethod.GET})
    public String AddTeam(Model model) {
        Team thisTeam=new Team();
        model.addAttribute("thisTeam", thisTeam);
        return "team/add";
    }

    //team-edit
    @RequestMapping(value="/team/edit/{id}", method = {RequestMethod.GET})
    public String EditTeam(Model model,@PathVariable String id) {
        Team thisTeam=this.teamService.findbyID(id);
        model.addAttribute("thisTeam", thisTeam);
        return "team/edit";
    }

    //team-save
    @RequestMapping(value="/team/save", method = {RequestMethod.POST})
    public String SaveTeam(@ModelAttribute("thisTeam") Team thisTeam) {
        this.teamService.saveOne(thisTeam);
        return "redirect:/super/team";
    }


    //team-remove
    @RequestMapping(value="/team/remove/{id}", method = {RequestMethod.GET})
    public String RemoveTeam(@PathVariable String id) {
        this.teamService.removeOne(id);
        return "index";
    }

    //user-index
    @RequestMapping(value="/user", method = {RequestMethod.GET})
    public String ViewUser() {
        return "user/index";
    }

    //user-add
    @RequestMapping(value="/user/add", method = {RequestMethod.GET})
    public String AddUser(Model model) {
        User thisUser=new User();
        thisUser.setRole("user");
        model.addAttribute("thisUser", thisUser);
        return "user/add";
    }

    //user-edit
    @RequestMapping(value="/user/edit/{id}", method = {RequestMethod.GET})
    public String EditUser(Model model,@PathVariable String id) {
        User thisUser=this.userService.findbyID(id);
        model.addAttribute("thisUser", thisUser);
        return "user/edit";
    }

    //user-save
    @RequestMapping(value="/user/save", method = {RequestMethod.POST})
    public String SaveUser(@ModelAttribute("thisUser") User thisUser) {
        this.userService.saveOne(thisUser);
        return "redirect:/super/user";
    }


    //user-remove
    @RequestMapping(value="/user/remove/{id}", method = {RequestMethod.GET})
    public String RemoveUser(@PathVariable String id) {
        this.userService.removeOne(id);
        return "index";
    }

    //message
    @RequestMapping(value="/message", method = {RequestMethod.GET})
    public String ViewMessage() {
        return "message/index";
    }
}
