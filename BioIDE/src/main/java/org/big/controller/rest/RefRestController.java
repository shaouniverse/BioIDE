package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Ref;
import org.big.service.RefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/console/ref/rest")
public class RefRestController {
	@Autowired
	private RefService refService;
	
	/**
     *<b>Reference的index页面</b>
     *<p> Reference的index页面</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSON list(HttpServletRequest request) {
		return this.refService.findRefList(request);
	}
	
	/**
	 * <b> 根据Id批量逻辑删除指定Ref</b>
	 * <p> 根据Id批量逻辑删除指定Ref</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/removeMany/{ids}", method = RequestMethod.GET)
	public int removeMany(@PathVariable String ids) {
		try {
			String[] idArr = ids.split("￥");
			int isRemove = 0;
			for (String id : idArr) {
				if (this.refService.logicRemove(id)) {
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
	 * <b> 根据Id单个逻辑删除指定Ref</b>
	 * <p> 根据Id单个逻辑删除指定Ref</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public boolean remove(@PathVariable String id) {
		try {
			return this.refService.logicRemove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     *<b>Ref的select列表</b>
     *<p> 当前用户的Ref的select检索列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON select(HttpServletRequest request){
		return this.refService.findBySelect(request);
	}
	
	/**
     *<b>Taxaset的select列表之新建Ref实体</b>
     *<p> Taxaset的select列表之新建Ref实体</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public JSON New(@ModelAttribute("thisRef") @Valid Ref thisRef, BindingResult result, Model model, HttpServletRequest request) {
		return this.refService.newOne(thisRef, request);
	}
}
