package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Dataset;
import org.big.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController  //返回json
@Controller
@RequestMapping("/console/dataset/rest")
public class DatasetRestController {
	@Autowired
    private DatasetService datasetService;

    /**
     *<b>根据teamId查询对应Team下的Dataset列表</b>
     *<p> 根据teamId查询对应Team下的Dataset列表</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    @RequestMapping("/list/{id}")
    public JSON DataSetTeamList(HttpServletRequest request, @PathVariable String id) {
    	return this.datasetService.findMyTeamDatasetsByTid(request, id);
    }
    
    /**
     *<b>删除多个Dataset</b>
     *<p> 根据Dataset id序列一次性删除多个</p>
     * @author BINZI
     * @param ids Media id序列，用"￥"分隔
     * @return boolean
     */
	@RequestMapping(value = "/removeMany/{ids}", method = RequestMethod.GET)
	public int RemoveManyDataset(@PathVariable String ids) {
		try {
			// 获取id列表字符串
			String[] idList = ids.split("￥");
			int isRemove = 0;
			for (int i = 0; i < idList.length; i++) {
				if (this.datasetService.logicRemove(idList[i])){
					isRemove = isRemove + 1;
				}
			}
			return isRemove;
		} catch (Exception e) {
			return -1;
		}
	}
    
    /**
     *<b>删除单个Dataset</b>
     *<p> 根据Dataset id删除单个</p>
     * @author BINZI
     * @param id Media id
     * @return boolean
     */
	// /console/dataset/rest/remove/id
    @RequestMapping(value="/remove/{id}", method = RequestMethod.GET)
    public boolean RemoveDataset(@PathVariable String id) {
    	try{
            return this.datasetService.logicRemove(id);
        }catch(Exception e){
            return false;
        }
    }
}
