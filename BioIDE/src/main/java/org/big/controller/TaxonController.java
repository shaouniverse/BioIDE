package org.big.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Dataset;
import org.big.entity.Taxaset;
import org.big.entity.Taxon;
import org.big.service.TaxonService;
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
 * <p><b>TaxonController类</b></p>
 * <p> Taxon的Controller</p>
 *
 * @author WangTianshan (王天山)
 *         <p>Created date: 2018/5/11 09:24</p>
 *         <p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/console/taxon")
public class TaxonController {
	@Autowired
	private TaxonService taxonService;
    /**
     * <b>默认管理页面</b>
     * <p> 默认管理页面</p>
     * @return java.lang.String
     * @author WangTianshan (王天山)
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String Index(Model model) {
        return "taxon/index";
    }

    /**
     * <b>新建页面</b>
     * <p> 新建页面</p>
     *
     * @return java.lang.String
     * @author WangTianshan (王天山)
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public String Add(Model model) {
        Taxon thisTaxon = new Taxon();
        Dataset thisDataset = new Dataset();
        Taxaset thisTaxaset = new Taxaset();
        thisTaxon.setId(UUID.randomUUID().toString());
        model.addAttribute("thisTaxon", thisTaxon);
        model.addAttribute("thisDataset", thisDataset);
        model.addAttribute("thisTaxaset", thisTaxaset);
        return "taxon/add";
    }
    
    /**
     *<b>去修改Taxon</b>
     *<p> 修改Taxon的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model, @PathVariable String id) {
		Taxon thisTaxon = this.taxonService.findOneById(id);
		model.addAttribute("thisTaxon", thisTaxon);
		return "taxon/edit";
	}
	
	/**
     *<b>保存修改Taxon</b>
     *<p> 修改Taxon的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEdit(@ModelAttribute("thisTaxon") @Valid Taxon thisTaxon, BindingResult result, Model model, HttpServletRequest request){
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			String errorMsg = "";
			for (ObjectError error : list) {
				errorMsg = errorMsg + error.getDefaultMessage() + ";";
			}
			model.addAttribute("thisTaxon", thisTaxon);
			model.addAttribute("errorMsg", errorMsg);
			return "taxon/edit";
		}else {
			this.taxonService.updateOneById(thisTaxon);
			return "redirect:/console/taxon";
		}
	}
}
