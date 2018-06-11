package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.service.DescriptiontypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
/**
 *<p><b>Descriptiontype相关的Controller的Rest风格类</b></p>
 *<p> Descriptiontype相关的Controller的Rest风格类</p>
 * @author BINZI
 *<p>Created date: 2018/06/11 10:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController
@RequestMapping(value = "/console/descriptiontype/rest")
public class DescriptiontypeRestController {

	@Autowired
	private DescriptiontypeService descriptiontypeService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON data(HttpServletRequest request) {
		return this.descriptiontypeService.findBySelect(request);
	}
}
