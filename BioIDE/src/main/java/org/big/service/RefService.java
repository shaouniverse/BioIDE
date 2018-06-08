package org.big.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Ref;

import com.alibaba.fastjson.JSON;

public interface RefService {
	/**
	 *<b>Ref的index页面的列表查询</b>
     *<p> Ref的index页面的列表查询</p> 
	 * @param request
	 * @return
	 */
	JSON findRefList(HttpServletRequest request);

	/**
	 * <b>根据id查询Datasource实体</b>
	 * <p> 根据id查询Datasource实体</p>
	 * @param id
	 * @return
	 */
	Ref findOneById(String id);
	
	/**
     *<b>保存一个实体</b>
     *<p> 保存一个实体</p>
     * @author BINZI
     * @param thisRef 实体
     * @return void
     */
	void saveOne(@Valid Ref thisRef);
	
	 /**
     *<b>根据id删除一个实体</b>
     *<p> 据id删除一个实体</p>
     * @author BINZI
     * @param ID 实体的id
     * @return void
     */
    void removeOne(String Id);
    
    /**
     *<b> 根据id逻辑删除一个实体</b>
     *<p> 据id逻辑删除一个实体</p>
     * @param id
     * @return
     */
    boolean logicRemove(String id);
    
    /**
     *<b>修改一个实体</b>
     *<p> 修改一个实体</p>
     * @author BINZI
     * @param thisRef 实体
     * @return void
     */
	void updateOneById(@Valid Ref thisRef);

    /**
     *<b>Ref的select列表</b>
     *<p> 当前Taxon下的Ref的select检索列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findBySelect(HttpServletRequest request);
	/**
     *<b>存储一个新的Datasource实体</b>
     *<p> 存储一个新的Datasource实体</p>
     * @author BINZI
     * @param Datasource 实体
     * @return com.alibaba.fastjson.JSON
     */	
	JSON newOne(@Valid Ref thisRef, HttpServletRequest request);

}
