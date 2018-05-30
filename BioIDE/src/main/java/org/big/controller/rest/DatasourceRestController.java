package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Datasource;
import org.big.service.DatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/console/datasource/rest")
public class DatasourceRestController {
	@Autowired
	private DatasourceService datasourceService;
	
	/**
     *<b>Datasource的index页面</b>
     *<p> Datasource的index页面</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSON list(HttpServletRequest request) {
		return this.datasourceService.findDatasourceList(request);
	}
	
	/**
	 * <b> 根据Id批量逻辑删除指定Datasource</b>
	 * <p> 根据Id批量逻辑删除指定Datasource</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/removeMany/{ids}", method = RequestMethod.GET)
	public int removeMany(@PathVariable String ids) {
		try {
			String[] idArr = ids.split("￥");
			int isRemove = 0;
			for (String id : idArr) {
				System.out.println(id);
				if (this.datasourceService.logicRemove(id)) {
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
	 * <b> 根据Id单个逻辑删除指定Datasource</b>
	 * <p> 根据Id单个逻辑删除指定Datasource</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public boolean remove(@PathVariable String id) {
		try {
			return this.datasourceService.logicRemove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     *<b>Datasource的select列表</b>
     *<p> 当前Dataset下的Datasource的select检索列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON select(HttpServletRequest request) {
		return this.datasourceService.findBySelect(request);
	}
	
	/**
     *<b>Datasource的select列表之新建Datasource实体</b>
     *<p> Datasource的select列表之新建Datasource实体</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public JSON New(HttpServletRequest request) {
		Datasource thisDatasource = new Datasource();
		thisDatasource.setVersions(request.getParameter("versions"));
		thisDatasource.setTitle(request.getParameter("title"));
		thisDatasource.setInfo(request.getParameter("info"));
		return this.datasourceService.newOne(thisDatasource, request);
	}
}
