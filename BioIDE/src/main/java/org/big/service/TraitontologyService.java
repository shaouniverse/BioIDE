package org.big.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

public interface TraitontologyService {
	/**
     *<b>Traitontology的select列表</b>
     *<p> Traitontology的select列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	JSON findBySelect(HttpServletRequest request);

}
