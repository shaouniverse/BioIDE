package org.big.controller;

import org.big.entity.Team;
import org.big.entity.User;
import org.big.service.TeamService;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.sql.Timestamp;
import java.util.List;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private TeamService teamService;

    //test
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Test() {
        List<Team> teams = teamService.selectTeamByUserId("0a");
        for(Team thisTeam:teams){
            System.out.println(thisTeam.getName());
        }
        return "test";
    }
}
