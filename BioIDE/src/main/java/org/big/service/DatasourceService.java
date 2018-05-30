package org.big.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.big.entity.Datasource;

import com.alibaba.fastjson.JSON;

public interface DatasourceService {
	
	/**
     *<b>保存一个实体</b>
     *<p> 保存一个实体</p>
     * @author BINZI
     * @param thisDatasource 实体
     * @return void
     */
	void saveOne(@Valid Datasource thisDatasource);
	
	/**
     *<b>Datasource的select列表</b>
     *<p> 当前Dataset下的Datasource的select检索列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findBySelect(HttpServletRequest request);

    /**
     *<b>修改一个实体</b>
     *<p> 修改一个实体</p>
     * @author BINZI
     * @param thisDatasource 实体
     * @return void
     */
	void updateOneById(@Valid Datasource thisDatasource);
	
	/**
	 *<b>Datasource的index页面的列表查询</b>
     *<p> Datasource的index页面的列表查询</p> 
	 * @param request
	 * @return
	 */
	JSON findDatasourceList(HttpServletRequest request);
	
	/**
	 * <b>根据id查询Datasource实体</b>
	 * <p> 根据id查询Datasource实体</p>
	 * @param id
	 * @return
	 */
	Datasource findOneById(String id);

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
     *<b>存储一个新的Datasource实体</b>
     *<p> 存储一个新的Datasource实体</p>
     * @author BINZI
     * @param Datasource 实体
     * @return com.alibaba.fastjson.JSON
     */	
	JSON newOne(Datasource thisDatasource, HttpServletRequest request);
}
