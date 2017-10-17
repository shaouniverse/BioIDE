package org.big.controller.rest;

import com.alibaba.fastjson.JSON;
import org.big.service.CommonnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 *<p><b>CommonnameController的Rest风格类</b></p>
 *<p> CommonnameController的Rest风格类</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController  //返回json
@Controller
@RequestMapping("/commonname/rest")
public class CommonnameRestController {

    @Autowired
    private CommonnameService commonnameService;

    /**
     *<b>列表</b>
     *<p> 当前用户所能查看权限的列表</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    @RequestMapping("/list")
    public JSON List(HttpServletRequest request) {
        return this.commonnameService.findbyInfo(request);
    }

    /**
     *<b>删除多个</b>
     *<p> 根据id序列一次性删除多个</p>
     * @author WangTianshan (王天山)
     * @param ids id序列，用"￥"分隔
     * @return boolean
     */
    @RequestMapping(value="/removeMany/{ids}",method = {RequestMethod.GET})
    public boolean removeMany(@PathVariable String ids) {
        try{
            //获取id列表字符串
            String [] idList;
            idList = ids.split("￥");
            for(int i=0;i<idList.length;i++){
                this.commonnameService.removeOne(idList[i]);
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     *<b>删除单个</b>
     *<p> 根据id删除单个</p>
     * @author WangTianshan (王天山)
     * @param id 实体id
     * @return boolean
     */
    @RequestMapping(value="/remove/{id}",method = {RequestMethod.GET})
    public boolean Remove(@PathVariable String id) {
        try{
            this.commonnameService.removeOne(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

}
