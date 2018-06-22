package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Description;
import org.big.service.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 *<p><b>Description相关的Controller的Rest风格类</b></p>
 *<p> Description相关的Controller的Rest风格类</p>
 * @author BINZI
 *<p>Created date: 2018/06/13 10:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController  //返回json
@Controller
@RequestMapping("/console/description/rest")
public class DescriptionRestController {
	@Autowired
	private DescriptionService descriptionService;
	
	/**
	 * <b> Description的Index页面分页列表查询</b>
	 * <p> Description的Index页面分页列表查询</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSON list(HttpServletRequest request) {
		return this.descriptionService.findDescriptionList(request);
	}
	
	/**
	 * <b> Description实体添加</b>
	 * <p> Description实体添加</p>
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	@RequestMapping(value="/add", method = {RequestMethod.POST})
	public JSON addDescription(HttpServletRequest request) {
		Description thisDescription = new Description();
		return this.descriptionService.addDescription(thisDescription, request);
	}
	
	/**
	 * <b> 根据Id批量逻辑删除指定Description</b>
	 * <p> 根据Id批量逻辑删除指定Description</p>
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/removeMany/{ids}", method = RequestMethod.GET)
	public int removeMany(@PathVariable String ids) {
		try {
			String[] idArr = ids.split("￥");
			int isRemove = 0;
			for (String id : idArr) {
				if (this.descriptionService.logicRemove(id)) {
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
	 * <b> 根据Id单个逻辑删除指定Description</b>
	 * <p> 根据Id单个逻辑删除指定Description</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public boolean remove(@PathVariable String id) {
		try {
			return this.descriptionService.logicRemove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     *<b>Description信息添加后的删除</b>
     *<p> Description信息添加后的删除</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public boolean delete(HttpServletRequest request){
		return this.descriptionService.deleteOne(request);
	}
	
	/**
	 * <b>Description的Index页面分页列表查询</b>
	 * <p> Description的Index页面分页列表查询</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON data(HttpServletRequest request) {
		return this.descriptionService.findBySelect(request);
	}
	
}
