package org.big.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

public interface DescriptiontypeService {

	JSON findBySelect(HttpServletRequest request);
	
}
