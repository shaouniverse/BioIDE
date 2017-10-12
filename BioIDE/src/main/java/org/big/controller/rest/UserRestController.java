package org.big.controller.rest;

import com.alibaba.fastjson.JSON;
import org.big.entity.User;
import org.big.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.Predicate;
import java.sql.Timestamp;
import java.util.List;


/**
 *<p><b>UserController的Rest风格类</b></p>
 *<p> UserController的Rest风格类</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController  //返回json
@Controller
@RequestMapping("/user/rest")
public class UserRestController {

    @Autowired
    private UserServiceImpl userService;
//
//    //List
//    @RequestMapping("/list")
//    public JSON List(HttpServletRequest request) {
//        return this.userService.findbyInfo(request);
//    }
//
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
