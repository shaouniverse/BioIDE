package org.big.service;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Taxkey;

import com.alibaba.fastjson.JSON;

public interface TaxkeyService {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findTaxkeyList(HttpServletRequest request);
	
	/**
	 * <b> 添加Description实体</b>
	 * <p> 添加Description实体</p>
	 * @author BINZI
	 * @param thisDescription
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	JSON addTaxkey(Taxkey thisTaxkey, HttpServletRequest request);

	/**
	 *<b>根据id删除一个实体</b>
     *<p> 根据id删除一个实体</p>
	 * @param id
	 * @return
	 */
	boolean deleteOneById(String id);
	
	/**
	 *<b>根据id删除一个已添加的实体</b>
     *<p> 根据id删除一个已添加的实体</p>
	 * @param request
	 * @return
	 */
	boolean deleteOne(HttpServletRequest request);
}
