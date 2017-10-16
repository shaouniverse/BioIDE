package org.big.service;

import com.alibaba.fastjson.JSON;
import org.big.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 *<p><b>User的Service类接口</b></p>
 *<p> User的Service类接口，与User有关的业务逻辑方法</p>
 * @author WangTianshan (王天山)
 *<p>Created date: 2017/9/6 21:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
public interface UserService {

    /**
     *<b>根据id查找一个实体</b>
     *<p> 据id查找一个实体</p>
     * @author WangTianshan (王天山)
     * @param ID 实体的id
     * @return org.big.entity.User
     */
    User findbyID(String ID);

    /**
     *<b>保存一个实体</b>
     *<p> 保存一个实体</p>
     * @author WangTianshan (王天山)
     * @param thisUser 实体
     * @return void
     */
    void saveOne(User thisUser);

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
     *<b>根据user_name查找一个实体</b>
     *<p> 据user_name查找一个实体</p>
     * @author WangTianshan (王天山)
     * @param user_name 实体的user_name
     * @return org.big.entity.User
     */
    User findOneByName(String user_name);
}
