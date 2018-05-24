package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/console/license/rest")
public class LicenseRestController {

	@Autowired
	private LicenseService licenseService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON data(HttpServletRequest request) {
		return this.licenseService.findBySelect(request);
	}
}
