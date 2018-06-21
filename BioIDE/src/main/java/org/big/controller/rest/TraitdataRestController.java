package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Traitdata;
import org.big.service.TraitdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
/**
 *<p><b>Traitdata相关的Controller的Rest风格类</b></p>
 *<p> Traitdata相关的Controller的Rest风格类</p>
 * @author BINZI
 *<p>Created date: 2018/06/21 10:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController
@RequestMapping(value = "/console/traitdata/rest")
public class TraitdataRestController {

	@Autowired
	private TraitdataService traitdataService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON data(HttpServletRequest request) {
		return this.traitdataService.findBySelect(request);
	}
	
	/**
	 * <b> Traitdata实体添加</b>
	 * <p> Traitdata实体添加</p>
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	@RequestMapping(value="/add", method = {RequestMethod.POST})
	public JSON addTraitdata(HttpServletRequest request) {
		Traitdata thisTraitdata = new Traitdata();
		return this.traitdataService.addTraitdata(thisTraitdata, request);
	}
	
	/**
     *<b>Traitdata信息添加后的删除</b>
     *<p> Traitdata信息添加后的删除</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean delete(HttpServletRequest request){
		return this.traitdataService.deleteOne(request);
	}
}
