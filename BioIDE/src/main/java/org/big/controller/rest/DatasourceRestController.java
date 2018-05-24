package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/console/datasource/rest")
public class DatasourceRestController {
	@Autowired
	private DatasetService datasetService;
	
	/**
     *<b>Dataset的select列表</b>
     *<p> 当前Team下的Dataset的select检索列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON select(HttpServletRequest request) {
		return this.datasetService.findBySelect(request);
	}
}
