package org.big.controller.rest;

import com.alibaba.fastjson.JSON;
import org.big.service.MessageServiceImpl;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@RestController  //返回json
@Controller
@RequestMapping("/console/message/rest")
public class MessageRestController {

    @Autowired
    private MessageServiceImpl messageService;

    //List
    @RequestMapping("/list")
    public JSON List(HttpServletRequest request) {
        return this.messageService.findInfoByAddressee(request);
    }

    //Sent
    @RequestMapping("/sent")
    public JSON Sent(HttpServletRequest request) {
        return this.messageService.findInfoBySender(request);
    }
//    //removeMany
//    @RequestMapping(value="/removeMany/{ids}",method = {RequestMethod.GET})
//    public boolean RemoveMany(@PathVariable String ids) {
//        try{
//            //获取id列表字符串
//            String [] idList;
//            idList = ids.split("￥");
//            for(int i=0;i<idList.length;i++){
//                this.userService.removeOne(idList[i]);
//            }
//            return true;
//        }catch(Exception e){
//            return false;
//        }
//    }
//
//    //removeOne
//    @RequestMapping(value="/remove/{id}",method = {RequestMethod.GET})
//    public boolean Remove(@PathVariable String id) {
//        try{
//            this.userService.removeOne(id);
//            return true;
//        }catch(Exception e){
//            return false;
//        }
//    }
}
