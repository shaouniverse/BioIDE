package org.big.controller;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.User;
import org.big.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 *<p><b>User相关的Controller类</b></p>
 *<p> User相关的Controller</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/12 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     *<b>添加</b>
     *<p> 添加新的实体的编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @return java.lang.String
     */
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add(Model model) {
        User thisUser=new User();
        thisUser.setRole("ROLE_USER");
        model.addAttribute("thisUser", thisUser);
        return "user/add";
    }

    /**
     *<b>编辑</b>
     *<p> 对已有的实体进行编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @param id 被编辑实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/edit/{id}", method = {RequestMethod.GET})
    public String Edit(Model model,@PathVariable String id) {
        User thisUser=this.userService.findbyID(id);
        model.addAttribute("thisUser", thisUser);
        return "user/edit";
    }

    /**
     *<b>保存</b>
     *<p> 将传入的实体保存</p>
     * @author WangTianshan (王天山)
     * @param thisUser 传入的实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/save", method = {RequestMethod.POST})
    public String Save(@ModelAttribute("thisUser") User thisUser) {
        this.userService.saveOne(thisUser);
        return "redirect:/user";
    }
    
    /**
     *<b>选择邮件接收人</b>
     *<p> 选择邮件接收人</p>
     * @author BINZI
     * @param 
     * @return 
     */
    @RequestMapping("/data")
    @ResponseBody
    public JSON UData(HttpServletRequest request) {
        return this.userService.findAllUser(request);
    }

}
