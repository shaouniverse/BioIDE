package org.big.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Dataset;
import org.big.service.DatasetService;
import org.big.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *<p><b>超级管理员相关的Controller类</b></p>
 *<p> 超级管理员相关的Controller</p>
 * @author BINZI
 *<p>Created date: 2018/04/10 11:07</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/console/dataset")
public class DatasetController {
	@Autowired
    private DatasetService datasetService;
	@Autowired
	private TeamService teamService;
	/**
     *<b>Dataset管理页</b>
     *<p> 包含所有Dataset的信息列表、操作选项</p>
     * @author BINZI
     * @return java.lang.String
     */
    @RequestMapping(value="", method = {RequestMethod.GET})
    public String index() {
        return "dataset/index";
    }
    
    /**
     *<b>添加Dataset</b>
     *<p> 添加新的Dataset的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
    @RequestMapping(value="/add", method = {RequestMethod.GET})
    public String Add(Model model) {
        Dataset thisDataset=new Dataset();
        model.addAttribute("thisDataset", thisDataset);
        return "dataset/add";
    }

    /**
     *<b>编辑Dataset</b>
     *<p> 对已有的Dataset进行编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @param id 被编辑Dataset实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/edit/{id}", method = {RequestMethod.GET})
    public String Edit(Model model,@PathVariable int id) {
        Dataset thisDataset=this.datasetService.findbyID(id);
        model.addAttribute("thisDataset", thisDataset);
        return "dataset/edit";
    }
    
    /**
     *<b>Dataset详情</b>
     *<p> 对已有的Dataset进行详情查看管理</p>
     * @author BINZI
     * @param model 初始化模型
     * @param id 被编辑Dataset实体id
     * @return java.lang.String
     */
    @RequestMapping(value="/show/{id}", method = {RequestMethod.GET})
    public String Show(Model model,@PathVariable int id,HttpServletRequest request) {
        Dataset thisDataset=this.datasetService.findbyID(id);
        model.addAttribute("thisDataset", thisDataset);
        // Page<Record> recordList=recordService.searchInfoByDataset(id,request);
        // model.addAttribute("recordList", recordList);
        // model.addAttribute("totalRecord", recordList.getTotalElements());
        int offset_serch=0;
        try{
            offset_serch=Integer.parseInt(request.getParameter("offset"));
        }
        catch (Exception e){

        }
        model.addAttribute("thisPage", offset_serch);
        return "dataset/show";
    }
    
    /**
     *<b>添加新的Dataset</b>
     *<p> 添加新的Dataset实体保存</p>
     * @author BINZI
     * @param thisDataset 传入的Dataset实体
     * @return java.lang.String
     */
    @RequestMapping(value="/new", method = {RequestMethod.POST})
    public String Add(@ModelAttribute("thisDataset") @Valid Dataset thisDataset,BindingResult result,Model model) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            String errorMsg="";
            for (ObjectError error : list) {
                errorMsg=errorMsg+error.getDefaultMessage()+";";
            }
            model.addAttribute("thisDataset", thisDataset);
            model.addAttribute("errorMsg", errorMsg);
            return "dataset/add";
        }
        thisDataset.setTeam(teamService.findbyID("2786c4df-6b16-4212-a611-369de7b82614"));
        this.datasetService.addOne(thisDataset);
        System.out.println(thisDataset.getCreator() + "\t" + thisDataset.getDsname() + "\t" + thisDataset.getDsabstract() + "\t" + thisDataset.getId());
        return "redirect:/console/dataset";
    }
    
    /**
     *<b>保存Dataset</b>
     *<p> 将传入的Dataset实体保存</p>
     * @author BINZI
     * @param thisDataset 传入的Dataset实体
     * @return java.lang.String
     */
    @RequestMapping(value="/save", method = {RequestMethod.POST})
    public String Save(@ModelAttribute("thisDataset") @Valid Dataset thisDataset,BindingResult result,Model model) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            String errorMsg="";
            for (ObjectError error : list) {
                errorMsg=errorMsg+error.getDefaultMessage()+";";
            }
            model.addAttribute("thisDataset", thisDataset);
            model.addAttribute("errorMsg", errorMsg);
            return "dataset/edit";
        }
        this.datasetService.saveOne(thisDataset);
        return "redirect:/user/dataset";
    }

    /**
     *<b>删除Dataset</b>
     *<p> 将传入的Dataset实体删除</p>
     * @author BINZI
     * @param id 传入的Dataset实体的id
     * @return java.lang.String
     */
/*    @RequestMapping(value="/remove/{id}", method = {RequestMethod.GET})
    public String Remove(@PathVariable String id) {
        this.datasetService.removeOne(id);
        return "index";
    }*/
}
