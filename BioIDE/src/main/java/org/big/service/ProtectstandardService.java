package org.big.service;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Protectstandard;

import com.alibaba.fastjson.JSON;

public interface ProtectstandardService {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findProtectstandardList(HttpServletRequest request);
	/**
	 * <b> 添加Protectstandard实体</b>
	 * <p> 添加Protectstandard实体</p>
	 * @author BINZI
	 * @param thisProtectstandard
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	JSON addProtectstandard(Protectstandard thisProtectstandard, HttpServletRequest request);
	
	/**
	 *<b>根据id逻辑删除一个实体</b>
     *<p> 根据id逻辑删除一个实体</p>
	 * @param id
	 * @return
	 */
	boolean logicRemove(String id);
	
    /**
     *<b>Protectstandard的select列表(保护标准名称)</b>
     *<p> 当前Taxon下的Protectstandard的select检索列表(保护标准名称)</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findBySelectStandard(HttpServletRequest request);
	
    /**
     *<b>Protectstandard的select列表(保护标准版本)</b>
     *<p> 当前Taxon下的Protectstandard的select检索列表(保护标准版本)</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findBySelectVersion(HttpServletRequest request, String standardname);
	
	/**
     *<b>Protectstandard的select列表(保护标准级别)</b>
     *<p> 当前Taxon下的Protectstandard的select检索列表(保护标准级别)</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findBySelectProtlevel(HttpServletRequest request, String version);
}
