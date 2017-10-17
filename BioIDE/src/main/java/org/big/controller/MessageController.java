package org.big.controller;

import org.big.entity.Message;
import org.big.entity.User;
import org.big.entity.UserDetail;
import org.big.service.MessageService;
import org.big.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *<p><b>Message相关的Controller类</b></p>
 *<p> Message相关的Controller</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/12 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/console/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    /**
     *<b>默认页面</b>
     *<p> 展示收信列表和操作选项</p>
     * @author WangTianshan (王天山)
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Index() {
        return "message/myMessage";
    }

    /**
     *<b>发信箱页面</b>
     *<p> 展示发信列表和操作选项</p>
     * @author WangTianshan (王天山)
     * @param
     * @return java.lang.String
     */
    @RequestMapping(value="/sent", method = {RequestMethod.GET})
    public String Sent() {
        return "message/sent";
    }

    /**
     *<b>查看详情</b>
     *<p> 查看消息的详情，如果是收信人进行此操作将改变此消息的状态为“已读”</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @param id 实体的
     * @return java.lang.String
     */
    @RequestMapping(value="/read/{id}", method = {RequestMethod.GET})
    public String ReadMessage(Model model,@PathVariable String id) {
        Message thisMessage=this.messageService.findbyID(id);
        User thisSender=this.userService.findbyID(thisMessage.getSender());
        model.addAttribute("thisMessage", thisMessage);
        model.addAttribute("thisSender", "From:"+thisSender.getNickname());
        return "message/read_admin";
    }

    /**
     *<b>添加</b>
     *<p> 添加新的实体的编辑的页面</p>
     * @author WangTianshan (王天山)
     * @param model 初始化模型
     * @return java.lang.String
     */
    @RequestMapping(value="/compose", method = {RequestMethod.GET})
    public String Add(Model model) {
        Message thisMessage=new Message();
        thisMessage.setText("<p></p><p></p>");
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        thisMessage.setSender(thisUser.getId());
        model.addAttribute("thisMessage", thisMessage);
        return "message/compose";
    }

    /**
     *<b>保存</b>
     *<p> 将传入的实体保存</p>
     * @author WangTianshan (王天山)
     * @param thisMessage 传入的实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/send", method = {RequestMethod.POST})
    public String Save(@ModelAttribute("thisMessage") Message thisMessage) {
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        thisMessage.setSender(thisUser.getId());
        this.messageService.sendOne(thisMessage);
        return "redirect:/console/message";
    }

}
