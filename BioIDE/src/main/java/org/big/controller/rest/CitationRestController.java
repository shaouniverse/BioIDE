package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Citation;
import org.big.service.CitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * @return com.alibaba.fastjson.JSON
	 */
	@RequestMapping(value="/add", method = {RequestMethod.POST})
	public JSON addCitation(HttpServletRequest request) {
		Citation thisCitation = new Citation();
		return this.citationService.addCitation(thisCitation, request);
	}
	
	/**
	 * <b> 根据Id批量逻辑删除指定Citation</b>
	 * <p> 根据Id批量逻辑删除指定Citation</p>
	 * @param request
	 * @return 
	 */
	@RequestMapping(value = "/removeMany/{ids}", method = RequestMethod.GET)
	public int removeMany(@PathVariable String ids) {
		try {
			String[] idArr = ids.split("￥");
			int isRemove = 0;
			for (String id : idArr) {
				if (this.citationService.logicRemove(id)) {
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
	 * <b> 根据Id单个逻辑删除指定Citation</b>
	 * <p> 根据Id单个逻辑删除指定Citation</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public boolean remove(@PathVariable String id) {
		try {
			return this.citationService.logicRemove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     *<b>Citation信息添加后的删除</b>
     *<p> Citation信息添加后的删除</p>
     * @author BINZI
     * @param request 页面请求
     * @return 
     */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean delete(HttpServletRequest request){
		return this.citationService.deleteOne(request);
	}
}
