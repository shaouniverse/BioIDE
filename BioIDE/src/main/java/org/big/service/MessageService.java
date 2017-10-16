package org.big.service;

import com.alibaba.fastjson.JSON;
import org.big.entity.Message;

import javax.servlet.http.HttpServletRequest;

/**
 *<p><b>Message的Service类接口</b></p>
 *<p> Message的Service类接口，与Message有关的业务逻辑方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
public interface MessageService {

    /**
     *<b>根据id查找一个实体</b>
     *<p> 据id查找一个实体</p>
     * @author WangTianshan (王天山)
     * @param ID 实体的id
     * @return org.big.entity.Message
     */
    Message findbyID(String ID);

    /**
     *<b>发出一个Message</b>
     *<p> 发出一个Message</p>
     * @author WangTianshan (王天山)
     * @param thisMessage 实体
     * @return void
     */
    void sendOne(Message thisMessage);

    /**
     *<b>根据id删除一个实体</b>
     *<p> 据id删除一个实体</p>
     * @author WangTianshan (王天山)
     * @param ID 实体的id
     * @return void
     */
    void removeOne(String ID);

    /**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    JSON findbyInfo(HttpServletRequest request);

    /**
     *<b>带分页排序的条件查询(收信箱)</b>
     *<p> 带分页排序的条件查询，只选择收信者和传入用户id一致的实体</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    JSON findInfoByAddressee(HttpServletRequest request);

    /**
     *<b>带分页排序的条件查询((发信箱)</b>
     *<p> 带分页排序的条件查询，只选择发信者和传入用户id一致的实体</p>
     * @author WangTianshan (王天山)
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    JSON findInfoBySender(HttpServletRequest request);
}
