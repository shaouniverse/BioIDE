package org.big.controller.rest;

import com.alibaba.fastjson.JSON;
import org.big.service.TeamServiceImpl;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@RestController  //返回json
@Controller
@RequestMapping("/super")
public class SuperRestController {

    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private UserServiceImpl userService;

    //team-List
    @RequestMapping("/team/rest/list")
    public JSON TeamList(HttpServletRequest request) {
        return this.teamService.findbyInfo(request);
    }

    //team-removeMany
    @RequestMapping(value="/team/rest/removeMany/{ids}",method = {RequestMethod.GET})
    public boolean RemoveManyTeam(@PathVariable String ids) {
        try{
            //获取id列表字符串
            String [] idList;
            idList = ids.split("￥");
            for(int i=0;i<idList.length;i++){
                this.teamService.removeOne(idList[i]);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //team-removeOne
    @RequestMapping(value="/team/rest/remove/{id}",method = {RequestMethod.GET})
    public boolean RemoveTeam(@PathVariable String id) {
        try{
            this.teamService.removeOne(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //user-List
    @RequestMapping("/user/rest/list")
    public JSON UserList(HttpServletRequest request) {
        return this.userService.findbyInfo(request);
    }

    //user-removeMany
    @RequestMapping(value="/user/rest/removeMany/{ids}",method = {RequestMethod.GET})
    public boolean RemoveManyUser(@PathVariable String ids) {
        try{
            //获取id列表字符串
            String [] idList;
            idList = ids.split("￥");
            for(int i=0;i<idList.length;i++){
                this.userService.removeOne(idList[i]);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //user-removeOne
    @RequestMapping(value="/user/rest/remove/{id}",method = {RequestMethod.GET})
    public boolean RemoveUser(@PathVariable String id) {
        try{
            this.userService.removeOne(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
