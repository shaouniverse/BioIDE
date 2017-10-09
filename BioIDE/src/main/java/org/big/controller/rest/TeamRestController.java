package org.big.controller.rest;

import com.alibaba.fastjson.JSON;
import org.big.service.TeamServiceImpl;
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
@RequestMapping("/console/team/rest")
public class TeamRestController {

    @Autowired
    private TeamServiceImpl teamService;

    //List
    @RequestMapping("/list")
    public JSON List(HttpServletRequest request) {
        return this.teamService.findbyUser(request);
        //return this.teamService.findbyInfo(request);
    }

    //removeMany
    @RequestMapping(value="/removeMany/{ids}",method = {RequestMethod.GET})
    public boolean RemoveMany(@PathVariable String ids) {
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

    //removeOne
    @RequestMapping(value="/remove/{id}",method = {RequestMethod.GET})
    public boolean Remove(@PathVariable String id,HttpServletRequest request) {
        try{
            request.getSession().setAttribute("operationError","");
            this.teamService.removeOneByUser(id);
            if(request.getSession().getAttribute("operationError").equals("authority")){
                request.getSession().setAttribute("operationError","");
                return false;
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
