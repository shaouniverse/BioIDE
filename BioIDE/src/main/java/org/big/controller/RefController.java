package org.big.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Ref;
import org.big.service.RefService;
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
@RequestMapping("/console/ref")
public class RefController {
	@Autowired
	private RefService refService;
	
	/**
     *<b> Reference的列表页面</b>
     *<p> Reference的列表页面</p>
     * @author BINZI
     * @return java.lang.String
     */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toIndex() {
		return "reference/index";
	}
	
	/**
     *<b>Reference管理页面之新建Reference实体</b>
     *<p> Reference管理页面之新建Reference实体</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		Ref thisRef = new Ref();
        model.addAttribute("thisRef", thisRef);
		return "reference/add";
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
		Ref thisRef = this.refService.findOneById(id);
		model.addAttribute("thisRef", thisRef);
		return "reference/edit";
	}
	
	/**
     *<b>保存Datasource实体</b>
     *<p> 保存Datasource实体</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("thisRef") @Valid Ref thisRef, BindingResult result, Model model, HttpServletRequest request) {
		if (null == thisRef.getId() || "".equals(thisRef.getId())) {			// --> 新增
			if (result.hasErrors()) {
				isError(thisRef, result, model);
				return "reference/add";
			}else {
				this.refService.saveOne(thisRef);
				return "redirect:/console/ref";
			}
		}else{																	// --> 修改
			if (result.hasErrors()) {
				isError(thisRef, result, model);
				return "reference/edit";
			}else {
				this.refService.updateOneById(thisRef);
				return "redirect:/console/ref";
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
	public void isError(Ref thisRef, BindingResult result, Model model) {
		List<ObjectError> list = result.getAllErrors();
		String errorMsg = "";
		for (ObjectError error : list) {
			errorMsg = errorMsg + error.getDefaultMessage() + ";";
		}
		model.addAttribute("thisRef", thisRef);
		model.addAttribute("errorMsg", errorMsg);
	}
	
}
