package org.big.controller;

import javax.servlet.http.HttpServletRequest;

import org.big.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
/**
 *<p><b>Rank实体的Controller类</b></p>
 *<p> Rank实体的Controller</p>
 * @author BINZI
 *<p>Created date: 2018/05/22 10:24</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Controller
@RequestMapping(value = "/consloe/rank")
public class RankController {
	@Autowired
	private RankService rankService;
	
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public JSON data(HttpServletRequest request) {
		return this.rankService.findRankSortData(request);
	}
	
}

