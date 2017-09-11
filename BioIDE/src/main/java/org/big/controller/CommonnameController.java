package org.big.controller;

import org.big.entity.Commonname;
import org.big.service.CommonnameService;
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
@RequestMapping("/commonname")
public class CommonnameController {

    @Autowired
    private CommonnameService commonnameService;

    //test
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String FindOne() {
        return "commonname/index";
    }

    //add
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add(Model model) {
        Commonname thisCommonname=new Commonname();
        thisCommonname.setInputtime(new Timestamp(System.currentTimeMillis()));
        thisCommonname.setSynchdate(new Timestamp(System.currentTimeMillis()));
        model.addAttribute("thisCommonname", thisCommonname);
        return "commonname/add";
    }

    //edit
    @RequestMapping(value="/edit/{id}", method = {RequestMethod.GET})
    public String Edit(Model model,@PathVariable String id) {
        Commonname thisCommonname=this.commonnameService.findbyID(id);
        model.addAttribute("thisCommonname", thisCommonname);
        return "commonname/edit";
    }

    //save
    @RequestMapping(value="/save", method = {RequestMethod.POST})
    public String Save(@ModelAttribute("thisCommonname") Commonname thisCommonname) {
        this.commonnameService.saveOne(thisCommonname);
        return "redirect:/commonname";
    }


    //remove
    @RequestMapping(value="/remove/{id}", method = {RequestMethod.GET})
    public String Remove(@PathVariable String id) {
        this.commonnameService.removeOne(id);
        return "index";
    }

}
