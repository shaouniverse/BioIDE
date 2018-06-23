package org.big.service;

import com.alibaba.fastjson.JSON;
import org.big.entity.Datasource;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface GeoobjectService {



	/**
	 *<b>Geoobject的select列表</b>
	 *<p> Geoobject的检索列表</p>
	 * @author WangTianshan(王天山)
	 * @param request 页面请求
	 * @return com.alibaba.fastjson.JSON
	 */
	JSON findBySelect(HttpServletRequest request);
	/**
	 *<b>GeoobjectAdministrative的select列表</b>
	 *<p> GeoobjectAdministrative的检索列表</p>
	 * @author WangTianshan(王天山)
	 * @param request 页面请求
	 * @return com.alibaba.fastjson.JSON
	 */
	JSON findAdministrativeBySelect(HttpServletRequest request);
	/**
	 *<b>构造拼音</b>
	 *<p> 构造拼音</p>
	 * @author WangTianshan(王天山)
	 * @return com.alibaba.fastjson.JSON
	 */
	Boolean buildPy();
}
