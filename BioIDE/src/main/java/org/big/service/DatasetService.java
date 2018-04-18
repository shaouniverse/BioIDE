package org.big.service;
import com.alibaba.fastjson.JSON;
import org.big.entity.Dataset;
import org.big.entity.Team;
import org.big.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 *<p><b>Dataset的Service类接口</b></p>
 *<p> Dataset的Service类接口，与Dataset有关的业务逻辑方法</p>
 * @author BINZI
 *<p>Created date: 2018/04/09 17:22</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
public interface DatasetService {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    JSON findbyInfo(HttpServletRequest request);

    /**
     *<b>带分页排序的条件查询（当前用户）</b>
     *<p> 带分页排序的条件查询（当前用户）</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    JSON findMybyInfo(HttpServletRequest request);

    /**
     *<b>带分页的条件查询（当前用户）</b>
     *<p> 带分页的条件查询（当前用户）</p>
     * @author BINZI
     * @param request 页面请求
     * @return com.alibaba.fastjson.JSON
     */
    JSON findMybySelect(HttpServletRequest request);

    /**
     *<b>存储媒体实体</b>
     *<p> 存储媒体实体</p>
     * @author BINZI
     * @param thisDataset 实体
     * @return java.lang.String
     */
    void saveOne(Dataset thisDataset);

    /**
     *<b>存储一个新的媒体实体</b>
     *<p> 存储一个新的媒体实体</p>
     * @author BINZI
     * @param thisDataset 实体
     * @return java.lang.String
     */
    void addOne(Dataset thisDataset);

    /**
     *<b>根据id逻辑删除一个实体</b>
     *<p> 据id逻辑删除一个实体</p>
     * @author BINZI
     * @param ID 实体的id
     * @return void
     */
    Boolean removeOne(String ID);

    /**
     *<b>根据id物理删除一个实体</b>
     *<p> 据id物理删除一个实体</p>
     * @author BINZI
     * @param ID 实体的id
     * @return void
     */
    Boolean deleteOne(HttpServletRequest request,String ID);

    /**
     *<b>根据id查找一个实体</b>
     *<p> 据id查找一个实体</p>
     * @author BINZI
     * @param ID 实体的id
     * @return org.big.entity.Dataset
     */
    Dataset findbyID(String ID);

    /**
     *<b>根据dsabstraction查找一个实体</b>
     *<p> 据dsabstraction查找一个实体</p>
     * @author BINZI
     * @param dsabstraction
     * @param thisUser
     * @return org.big.entity.Dataset
     */
    Dataset findOneByDsabstractAndTeam(String dsabstraction,Team thisTeam);

    /**
     *<b>查找默认的那个实体</b>
     *<p> 查找默认的那个实体</p>
     * @author BINZI
     * @return org.big.entity.Dataset
     */
    Dataset findDefaultByUser();

    /**
     *<b>存储一个新的媒体实体</b>
     *<p> 存储一个新的媒体实体</p>
     * @author BINZI
     * @param thisDataset 实体
     * @return java.lang.String
     */
    JSON newOne(Dataset thisDataset);
}
