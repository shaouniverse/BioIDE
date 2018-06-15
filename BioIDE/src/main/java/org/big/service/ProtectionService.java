package org.big.service;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Protection;

import com.alibaba.fastjson.JSON;

public interface ProtectionService {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findProtectionList(HttpServletRequest request);
	
	/**
	 * <b> 添加Distributiondata实体</b>
	 * <p> 添加Distributiondata实体</p>
	 * @author BINZI
	 * @param thisProtection
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	JSON addProtection(Protection thisProtection, HttpServletRequest request);

	/**
     *<b>将Protection表单的Reference相关属性拼装成JSON数据</b>
     *<p> 将Protection表单的Reference相关属性拼装成JSON数据</p>
     * @author BINZI
     * @param Id 实体的id
     * @return com.alibaba.fastjson.JSON
     */
	JSON handleReferenceToJson(HttpServletRequest request);
	
	/**
	 *<b>根据id逻辑删除一个实体</b>
     *<p> 根据id逻辑删除一个实体</p>
	 * @param id
	 * @return
	 */
	boolean logicRemove(String id);
	
	/**
     *<b>Protection的select列表(保护标准级别)</b>
     *<p> 当前Taxon下的Protection的select检索列表(保护标准级别)</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findBySelect(HttpServletRequest request);
	
	/**
	 *<b>根据id删除一个实体</b>
     *<p> 根据id删除一个实体</p>
	 * @param id
	 * @return
	 */
	JSON deleteOne(HttpServletRequest request);
}
