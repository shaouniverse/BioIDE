package org.big.service;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Traitdata;

import com.alibaba.fastjson.JSON;

public interface TraitdataService {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findBySelect(HttpServletRequest request);

	/**
	 * <b> 添加Traitdata实体</b>
	 * <p> 添加Traitdata实体</p>
	 * @author BINZI
	 * @param thisDescription
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	JSON addTraitdata(Traitdata thisTraitdata, HttpServletRequest request);
	
	/**
     *<b>将特征数据表单的Reference相关属性拼装成JSON数据</b>
     *<p> 将特征淑君表单的Reference相关属性拼装成JSON数据</p>
     * @author BINZI
     * @param Id 实体的id
     * @return com.alibaba.fastjson.JSON
     */
	JSON handleReferenceToJson(HttpServletRequest request);
	
	/**
	 *<b>根据id删除一个实体</b>
     *<p> 根据id删除一个实体</p>
	 * @param request
	 * @return
	 */
	boolean deleteOne(HttpServletRequest request);

}
