package org.big.service;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Distributiondata;

import com.alibaba.fastjson.JSON;

public interface DistributiondataService {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findDistributiondataList(HttpServletRequest request);
	
	/**
	 * <b> 添加Distributiondata实体</b>
	 * <p> 添加Distributiondata实体</p>
	 * @author BINZI
	 * @param thisDistributiondata
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	JSON addDistributiondata(Distributiondata thisDistributiondata, HttpServletRequest request);

	/**
     *<b>将描述表单的Reference相关属性拼装成JSON数据</b>
     *<p> 将描述表单的Reference相关属性拼装成JSON数据</p>
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
}
