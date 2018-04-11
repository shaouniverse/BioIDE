package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController  //返回json
@Controller
@RequestMapping("/console/dataset/rest")
public class DatasetRestController {
	@Autowired
    private DatasetService datasetService;

    /**
     *<b>Dataset列表</b>
     *<p> 当前用户的Dataset的检索列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    @RequestMapping("/list")
    public JSON DataSetList(HttpServletRequest request) {
        return this.datasetService.findMybyInfo(request);
    }
}
