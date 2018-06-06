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
	 /**
     *<b>根据TaxonId查找一个Taxon实体</b>
     *<p> 据id查找一个实体</p>
     * @author BINZI
     * @param Id 实体的id
     * @return org.big.entity.Taxon
     */
	Taxon findOneById(String id);

}
