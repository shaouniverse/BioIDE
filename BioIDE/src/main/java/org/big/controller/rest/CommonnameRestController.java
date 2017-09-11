package org.big.controller.rest;

import com.alibaba.fastjson.JSON;
import org.big.service.CommonnameServiceImpl;
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
@RequestMapping("/commonname/rest/")
public class CommonnameRestController {

    @Autowired
    private CommonnameServiceImpl commonnameService;

    //List
    @RequestMapping("/list")
    public JSON List(HttpServletRequest request) {
        return this.commonnameService.findbyInfo(request);
    }

    //removeMany
    @RequestMapping(value="/removeMany/{ids}",method = {RequestMethod.GET})
    public boolean removeMany(@PathVariable String ids) {
        try{
            //获取id列表字符串
            String [] idList;
            idList = ids.split("￥");
            for(int i=0;i<idList.length;i++){
                this.commonnameService.removeOne(idList[i]);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    //removeOne
    @RequestMapping(value="/remove/{id}",method = {RequestMethod.GET})
    public boolean Remove(@PathVariable String id) {
        try{
            this.commonnameService.removeOne(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
