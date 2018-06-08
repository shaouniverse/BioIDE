package org.big.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Datasource;
import org.big.service.DatasetService;
import org.big.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/console/datasource")
public class DatasourceController {
	@Autowired
	private DatasourceService datasourceService;
	
	@Autowired
	private DatasetService datasetService;
	
	/**
     *<b> Datasource的列表页面</b>
     *<p> Datasource的列表页面</p>
     * @author BINZI
     * @return java.lang.String
     */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toIndex() {
		return "datasource/index";
	}
	
	/**
     *<b>Datasource管理页面之新建Datasource实体</b>
     *<p> Datasource管理页面之新建Datasource实体</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		Datasource thisDatasource = new Datasource();
        model.addAttribute("thisDatasource", thisDatasource);
		return "datasource/add";
	}
	
	/**
     *<b>Taxon的添加页面Select下拉选在指定Team下添加Datasource</b>
     *<p> 添加新的Datasource的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/addNew", method = RequestMethod.GET)
	public String addNew(Model model, HttpServletRequest request) {
		Datasource thisDatasource = new Datasource();
        model.addAttribute("thisDatasource", thisDatasource);
		return "datasource/addModal";
	}
	
	/**
     *<b>修改Datasource</b>
     *<p> 修改Datasource的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable String id) {
		Datasource thisDatasource = this.datasourceService.findOneById(id);
		model.addAttribute("thisDatasource", thisDatasource);
		return "datasource/edit";
	}
	
	/**
     *<b>保存Datasource实体</b>
     *<p> 保存Datasource实体</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("thisDatasource") @Valid Datasource thisDatasource, BindingResult result, Model model, HttpServletRequest request) {
		if (null == thisDatasource.getId() || "".equals(thisDatasource.getId())) {	// --> 新增
			if (result.hasErrors()) {
				isError(thisDatasource, result, model);
				return "datasource/add";
			}else {
				String datasetID = (String) request.getSession().getAttribute("datasetID");
				thisDatasource.setDataset(this.datasetService.findbyID(datasetID));
				thisDatasource.setInputtime(new Timestamp(System.currentTimeMillis()));
				this.datasourceService.saveOne(thisDatasource);
				return "redirect:/console/datasource";
			}
		}else{																	// --> 修改
			if (result.hasErrors()) {
				isError(thisDatasource, result, model);
				return "datasource/edit";
			}else {
				this.datasourceService.updateOneById(thisDatasource);
				return "redirect:/console/datasource";
			}
		}
	}

	/** 
	 * <b> 新增|修改 重复代码提取</b>
	 * <p> 新增|修改 重复代码提取</p>
	 * @param thisDatasource
	 * @param result
	 * @param model
	 */
	public void isError(Datasource thisDatasource, BindingResult result, Model model) {
		List<ObjectError> list = result.getAllErrors();
		String errorMsg = "";
		for (ObjectError error : list) {
			errorMsg = errorMsg + error.getDefaultMessage() + ";";
		}
		model.addAttribute("thisDatasource", thisDatasource);
		model.addAttribute("errorMsg", errorMsg);
	}
	
}
