package org.big.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.big.entity.Protectstandard;
import org.big.service.ProtectstandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

/**
 *<p><b>Protectstandard相关的Controller的Rest风格类</b></p>
 *<p> Protectstandard相关的Controller的Rest风格类</p>
 * @author BINZI
 *<p>Created date: 2018/06/14 13:58</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@RestController  //返回json
@Controller
@RequestMapping("/console/protectstandard/rest")
public class ProtectstandardRestController {
	@Autowired
	private ProtectstandardService protectstandardService;
	
	/**
	 * <b> Protectstandard的Index页面分页列表查询</b>
	 * <p> Protectstandard的Index页面分页列表查询</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public JSON list(HttpServletRequest request) {
		return this.protectstandardService.findProtectstandardList(request);
	}
	
	/**
	 * <b> Protection实体添加</b>
	 * <p> Protection实体添加</p>
	 * @param request
	 * @return com.alibaba.fastjson.JSON
	 */
	@RequestMapping(value="/add", method = {RequestMethod.POST})
	public JSON addProtectstandard(HttpServletRequest request) {
		Protectstandard thisProtectstandard = new Protectstandard();
		return this.protectstandardService.addProtectstandard(thisProtectstandard, request);
	}
	
	/**
	 * <b> 根据Id批量逻辑删除指定Protectstandard</b>
	 * <p> 根据Id批量逻辑删除指定Protectstandard</p>
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/removeMany/{ids}", method = RequestMethod.GET)
	public int removeMany(@PathVariable String ids) {
		try {
			String[] idArr = ids.split("￥");
			int isRemove = 0;
			for (String id : idArr) {
				if (this.protectstandardService.logicRemove(id)) {
					isRemove = isRemove + 1;
				}
			}
			return isRemove;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * <b> 根据Id单个逻辑删除指定Protectstandard</b>
	 * <p> 根据Id单个逻辑删除指定Protectstandard</p>
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public boolean remove(@PathVariable String id) {
		try {
			return this.protectstandardService.logicRemove(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
     *<b>Protectstandard的select列表(保护标准名称)</b>
     *<p> 当前用户的Protectstandard的select检索列表(保护标准名称)</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/selectStandard", method = RequestMethod.GET)
	public JSON selectStandard(HttpServletRequest request){
		return this.protectstandardService.findBySelectStandard(request);
	}
	
	/**
     *<b>Protectstandard的select列表(保护标准版本)</b>
     *<p> 当前用户的Protectstandard的select检索列表(保护标准版本)</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/selectVersion", method = RequestMethod.GET)
	public JSON selectVersion(HttpServletRequest request){
		return this.protectstandardService.findBySelectVersion(request);
	}
	
	/**
     *<b>Protectstandard的select列表(保护标准级别)</b>
     *<p> 当前用户的Protectstandard的select检索列表(保护标准级别)</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
	@RequestMapping(value = "/selectProtlevel", method = RequestMethod.GET)
	public JSON select(HttpServletRequest request){
		return this.protectstandardService.findBySelectProtlevel(request);
	}
	
}
