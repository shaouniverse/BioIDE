package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.service.DescriptiontypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping(value = "/console/descriptiontype/rest")
public class DescriptiontypeRestController {

	@Autowired
	private DescriptiontypeService descriptiontypeService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JSON data(HttpServletRequest request) {
		return this.descriptiontypeService.findBySelect(request);
	}
}
