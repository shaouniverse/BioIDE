package org.big.controller.rest;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Citation;
import org.big.service.CitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 *<p><b>Citation相关的Controller的Rest风格类</b></p>
 *<p> Citation相关的Controller的Rest风格类</p>
 * @author BINZI
 *<p>Created date: 2018/06/11 10:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController  //返回json
@Controller
@RequestMapping("/console/citation/rest")
public class CitationRestController {
	@Autowired
	private CitationService citationService;
	
	/**
	 * <b> Citation的Index页面分页列表查询</b>
	 * <p> Citation的Index页面分页列表查询</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSON list(HttpServletRequest request) {
		return this.citationService.findCitationList(request);
	}
	
	/**
	 * <b> Citation实体添加</b>
	 * <p> Citation实体添加</p>
	 * @param request
	 * @param thisTaxon
	 * @param result
	 * @param model
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	@RequestMapping(value="/add", method = {RequestMethod.POST})
	public JSON AddTaxonBaseInfo(@ModelAttribute("thisTaxon") @Valid Citation thisCitation, BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			String errorMsg = "";
			for (ObjectError error : list) {
				errorMsg = errorMsg + error.getDefaultMessage() + ";";
			}
			model.addAttribute("thisCitation", thisCitation);
			model.addAttribute("errorMsg", errorMsg);
		}
		thisCitation.setInputtime(new Timestamp(System.currentTimeMillis()));
		return this.citationService.addCitation(thisCitation, request);
		
	}
	
}
