package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Taxaset;
import org.big.service.TaxasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController  //返回json
@Controller
@RequestMapping("/console/taxaset/rest")
public class TaxasetRestController {
	@Autowired
	private TaxasetService taxasetService;
	
	/**
	 * <b> Taxaset的Index页面分页列表查询</b>
	 * <p> Taxaset的Index页面分页列表查询</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSON list(HttpServletRequest request) {
		return this.taxasetService.findTaxasetList(request);
	}
	
	/**
	 * <b> 根据Id批量逻辑删除指定Taxaset</b>
	 * <p> 根据Id批量逻辑删除指定Taxaset</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/removeMany/{ids}", method = RequestMethod.GET)
	public int removeMany(@PathVariable String ids) {
		try {
			String[] idArr = ids.split("￥");
			int isRemove = 0;
			for (String id : idArr) {
				if (this.taxasetService.logicRemove(id)) {
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
	 * <b> 根据Id单个逻辑删除指定Taxaset</b>
	 * <p> 根据Id单个逻辑删除指定Taxaset</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public boolean remove(@PathVariable String id) {
		try {
			return this.taxasetService.logicRemove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     *<b>Taxaset的select列表</b>
     *<p> 当前用户的Taxaset的select检索列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON select(HttpServletRequest request){
		return this.taxasetService.findBySelect(request);
	}
	
    /**
     *<b>Taxaset的select列表之新建Taxaset实体</b>
     *<p> Taxaset的select列表之新建Taxaset实体</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public JSON New(@ModelAttribute("thisTaxaset") @Valid Taxaset thisTaxaset, BindingResult result, Model model, HttpServletRequest request) {
		return this.taxasetService.newOne(thisTaxaset, request);
	}
}
