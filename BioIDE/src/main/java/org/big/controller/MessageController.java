package org.big.controller;

import org.big.entity.Message;
import org.big.entity.Team;
import org.big.entity.UserDetail;
import org.big.service.MessageServiceImpl;
import org.big.service.TeamServiceImpl;
import org.big.service.UserTeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by WangTianshan on 2017/9/6.
 */
@Controller
@RequestMapping("/console/message")
public class MessageController {

    @Autowired
    private MessageServiceImpl messageService;

    //index
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String Index() {
        return "message/myMessage";
    }

    //add
    @RequestMapping(value="/compose", method = {RequestMethod.GET})
    public String Add(Model model) {
        Message thisMessage=new Message();
        thisMessage.setText("<p></p><p></p>");
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        thisMessage.setSender(thisUser.getId());
        model.addAttribute("thisMessage", thisMessage);
        return "message/compose";
    }

    //save
    @RequestMapping(value="/send", method = {RequestMethod.POST})
    public String Save(@ModelAttribute("thisMessage") Message thisMessage) {
        UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        thisMessage.setSender(thisUser.getId());
        this.messageService.sendOne(thisMessage);
        return "redirect:/console/message";
    }

}
