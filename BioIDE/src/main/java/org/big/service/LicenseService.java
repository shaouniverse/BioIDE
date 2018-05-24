package org.big.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

public interface LicenseService {

	JSON findBySelect(HttpServletRequest request);
}
