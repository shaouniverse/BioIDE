package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.service.TraitontologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
/**
 *<p><b>Traitontology相关的Controller的Rest风格类</b></p>
 *<p> Traitontology相关的Controller的Rest风格类</p>
 * @author BINZI
 *<p>Created date: 2018/06/22 10:20</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController
@RequestMapping(value = "/console/traitontology/rest")
public class TraitontologyRestController {

	@Autowired
	private TraitontologyService traitontologyService;
	/**
	 * <b> Traitontology的Select2查询</b>
	 * <p> Traitontology的Select2查询</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON data(HttpServletRequest request) {
		return this.traitontologyService.findBySelect(request);
	}
}
