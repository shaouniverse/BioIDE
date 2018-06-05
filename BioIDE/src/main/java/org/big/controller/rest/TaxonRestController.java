package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Taxon;
import org.big.service.TaxonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@Controller
@RequestMapping("/console/taxon/rest")
public class TaxonRestController {
	@Autowired
	private TaxonService taxonService;
	
	/**
	 * <b> Taxon的Index页面分页列表查询</b>
	 * <p> Taxon的Index页面分页列表查询</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSON list(HttpServletRequest request) {
		return this.taxonService.findTaxonList(request);
	}
	
	/**
     *<b>新建Taxon添加基础信息</b>
     *<p> 新建Taxon添加基础信息</p>
     * @author WangTianshan (王天山)
     * @return java.lang.String
     */
    @RequestMapping(value="/add", method = {RequestMethod.POST})
	public JSON AddTaxonBaseInfo(@ModelAttribute("thisTaxon") @Valid Taxon thisTaxon, BindingResult result, Model model, HttpServletRequest request) {
		return this.taxonService.addTaxonBaseInfo(thisTaxon);
	}
}
