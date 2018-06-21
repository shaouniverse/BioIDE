package org.big.controller;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Datasource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *<p><b>Traitdata相关的Controller类</b></p>
 *<p> Traitdata相关的Controller</p>
 * @author BINZI
 *<p>Created date: 2018/06/21 16:52</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping("/console/traitdata")
public class TraitdataController {
	/**
     *<b> Traitdata的列表页面</b>
     *<p> Traitdata的列表页面</p>
     * @author BINZI
     * @return java.lang.String
     */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toIndex() {
		return "traitdata/index";
	}
	
	/**
     *<b>Traitdata的添加页面Select下拉选添加Datasource</b>
     *<p> 添加新的Datasource的编辑的页面</p>
     * @author BINZI
     * @param model 初始化模型
     * @return java.lang.String
     */
	@RequestMapping(value = "/addDatasource", method = RequestMethod.GET)
	public String addDatasource(Model model, HttpServletRequest request) {
		Datasource thisDatasource = new Datasource();
        model.addAttribute("thisDatasource", thisDatasource);
        model.addAttribute("traitdataFormNum", request.getParameter("traitdataFormNum"));
        model.addAttribute("sourcesId", request.getParameter("sourcesId"));
		return "traitdata/addDatasourcesModal";
	}
}
