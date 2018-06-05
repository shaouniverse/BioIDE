package org.big.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Taxon;

import com.alibaba.fastjson.JSON;

public interface TaxonService {

    /**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findTaxonList(HttpServletRequest request);
	
	/**
	 * <b> 添加Taxon基础数据</b>
	 * <p> 添加Taxon基础数据</p>
	 * @author BINZI
	 * @param thisTaxon
	 */
	JSON addTaxonBaseInfo(@Valid Taxon thisTaxon);
	// 自定义Taxon增删改查

}
