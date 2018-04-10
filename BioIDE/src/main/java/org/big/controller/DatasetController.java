package org.big.controller;

import org.big.entity.Dataset;
import org.big.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
