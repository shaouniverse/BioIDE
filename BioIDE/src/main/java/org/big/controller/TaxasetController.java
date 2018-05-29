package org.big.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Taxaset;
import org.big.service.DatasetService;
import org.big.service.TaxasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *<p><b>Taxaset实体的Controller类</b></p>
 *<p> Taxaset实体的Controller</p>
 * @author BINZI
 *<p>Created date: 2018/05/21 14:07</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/console/taxaset")
public class TaxasetController {
	@Autowired
	private TaxasetService taxasetService;
	@Autowired
	private DatasetService datasetService;
	
	/**
     *<b>Dataset管理页</b>
     *<p> 包含所有Dataset的信息列表、操作选项</p>
     * @author BINZI
     * @return java.lang.String
     */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "taxaset/index";
	}
	
	/**
     *<b>添加Taxaset</b>
     *<p> 添加新的Taxaset的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		Taxaset thisTaxaset = new Taxaset();
		model.addAttribute("thisTaxaset", thisTaxaset);
		return "taxaset/add";
	}
	
	/**
     *<b>修改Taxaset</b>
     *<p> 修改Taxaset的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable String id) {
		Taxaset thisTaxaset = this.taxasetService.findOneById(id);
		model.addAttribute("thisTaxaset", thisTaxaset);
		return "taxaset/edit";
	}
	
	/**
     *<b>保存Taxaset实体</b>
     *<p> 保存Taxaset实体</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("thisTaxaset") @Valid Taxaset thisTaxaset, BindingResult result, Model model, HttpServletRequest request) {
		if (null == thisTaxaset.getId() || "".equals(thisTaxaset.getId())) {	// --> 新增
			if (result.hasErrors()) {
				isError(thisTaxaset, result, model);
				return "taxaset/add";
			}else {
				String datasetID = (String) request.getSession().getAttribute("datasetID");
				thisTaxaset.setDataset(this.datasetService.findbyID(datasetID));
				this.taxasetService.saveOne(thisTaxaset);
				return "redirect:/console/taxaset";
			}
		}else{																	// --> 修改
			if (result.hasErrors()) {
				isError(thisTaxaset, result, model);
				return "taxaset/edit";
			}else {
				this.taxasetService.updateOneById(thisTaxaset);
				return "redirect:/console/taxaset";
			}
		}
	}
	
	public String show(@PathVariable String id, HttpServletRequest request, Model model) { // 指定数据集是否有分类单元集
		String dsId = (String) request.getSession().getAttribute("datasetID");
		List<Taxaset> tsList = this.taxasetService.findTaxasetsByDatasetId(dsId);
		for (Taxaset taxaset : tsList) {
			if (id.equals(taxaset.getId())) {
				Taxaset thisTaxaset = this.taxasetService.findOneById(id);
				model.addAttribute("thisTaxaset", thisTaxaset);
				return "taxaset/show";
			}
		}
		return "403";
	}
	
	/** 
	 * <b> 新增|修改 重复代码提取</b>
	 * <p> 新增|修改 重复代码提取</p>
	 * @param thisTaxaset
	 * @param result
	 * @param model
	 */
	private void isError(Taxaset thisTaxaset, BindingResult result, Model model) {
		List<ObjectError> list = result.getAllErrors();
		String errorMsg = "";
		for (ObjectError error : list) {
			errorMsg = errorMsg + error.getDefaultMessage() + ";";
		}
		model.addAttribute("thisTaxaset", thisTaxaset);
		model.addAttribute("errorMsg", errorMsg);
	}
	
	/**
     *<b>添加Taxaset</b>
     *<p> 添加新的Taxaset的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/addNew", method = RequestMethod.GET)
	public String addNew(Model model) {
		Taxaset thisTaxaset = new Taxaset();
		model.addAttribute("thisTaxaset", thisTaxaset);
		return "taxaset/addModal";
	}
}
