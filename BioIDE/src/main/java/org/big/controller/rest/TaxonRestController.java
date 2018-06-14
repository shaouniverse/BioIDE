package org.big.controller.rest;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.common.UUIDUtils;
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
	 * <b> Taxon添加</b>
	 * <p> Taxon添加</p>
	 * @param request
	 * @param thisTaxon
	 * @param result
	 * @param model
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	@RequestMapping(value="/add", method = {RequestMethod.POST})
	public JSON AddTaxonBaseInfo(@ModelAttribute("thisTaxon") @Valid Taxon thisTaxon, BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			String errorMsg = "";
			for (ObjectError error : list) {
				errorMsg = errorMsg + error.getDefaultMessage() + ";";
			}
			model.addAttribute("thisTaxon", thisTaxon);
			model.addAttribute("errorMsg", errorMsg);
		}
		thisTaxon.setInputtime(new Timestamp(System.currentTimeMillis()));
		return this.taxonService.addTaxonBaseInfo(thisTaxon, request);
		
	}

	/**
	 * <b> 根据Id批量逻辑删除指定Taxon</b>
	 * <p> 根据Id批量逻辑删除指定Taxon</p>
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/removeMany/{ids}", method = RequestMethod.GET)
	public int removeMany(@PathVariable String ids) {
		try {
			String[] idArr = ids.split("￥");
			int isRemove = 0;
			for (String id : idArr) {
				if (this.taxonService.logicRemove(id)) {
					isRemove = isRemove + 1;
				}
			}
			return isRemove;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * <b> 根据Id单个逻辑删除指定Taxon</b>
	 * <p> 根据Id单个逻辑删除指定Taxon</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public boolean remove(@PathVariable String id) {
		try {
			return this.taxonService.logicRemove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * <b> 为Taxon下的各个实体设置唯一标识</b>
	 * <p> 为Taxon下的各个实体设置唯一标识</p>
	 * @return
	 */
	@RequestMapping(value = "/uuid", method = RequestMethod.GET)
	private String uuid() {
		return UUIDUtils.getUUID32();
	}
}
