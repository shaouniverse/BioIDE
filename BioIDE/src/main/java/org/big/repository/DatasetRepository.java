package org.big.repository;

import org.big.entity.Dataset;
import org.big.entity.Team;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *<p><b>Dataset的DAO类接口</b></p>
 *<p> Dataset的DAO类接口，与Dataset有关的持久化操作方法</p>
 * @author BINZI
 *<p>Created date: 2018/04/09 16:25</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
public interface DatasetRepository extends BaseRepository<Dataset, String> {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI 
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Dataset>
     */
	// 根据dsname & dsabstract & createdDate 模糊 | 排序 | 条件 | 分页查询
   @Query(value = "select d from Dataset d where (d.dsname like %?1% or d.dsabstract like %?1% or d.createdDate like %?1%)"
    )
   Page<Dataset> searchInfo(String findText, Pageable pageable);

    /**
     *<b>带分页排序的条件查询（当前用户的数据集）</b>
     *<p> 带分页排序的条件查询（当前用户）</p>
     * @author BINZI 
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Dataset>
     */
    // 根据dsname & dsabstract & createdDate & user.id & status 模糊 | 排序 | 条件 | 分页查询
    @Query(value = "select d from Dataset d" +
            " where (d.dsname like %?1% or d.dsabstract like %?1% or d.createdDate like %?1%) and d.creator = ?2 and d.status = 1 and d.team = NUll")
    Page<Dataset> searchMyInfo(String findText, String userId, Pageable pageable);
    
    /**
     *<b>带分页排序的条件查询（当前用户組下数据集）</b>
     *<p> 带分页排序的条件查询（当前用户）</p>
     * @author BINZI 
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Dataset>
     */
    @Query(value = "select d from Dataset d where (d.dsname like %?1% or d.dsabstract like %?1% or d.createdDate like %?1%) and d.status = 1 and d.team.id = ?2")
    Page<Dataset> searchMyTeamDataInfo(String findText, Pageable pageable, String id);

    /**
     *<b>带分页排序的按dsname查询（当前用户）</b>
     *<p> 带分页排序的按dsname查询（当前用户）</p>
     * @author BINZI 
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Dataset>
     */
    // 根据dsname & user.id & status 模糊 | 排序 | 条件 | 分页查询
    @Query(value = "select d from Dataset d where (d.dsname like %?1%) and d.creator = ?2 and d.status = 1"
    )
    Page<Dataset> searchMyDsname(String findText, String userId, Pageable pageable);

    /**
     *<b>根据id查询一个符合条件的Dataset</b>
     *<p> 根据id查询一个符合条件的Dataset</p>
     * @author BINZI 
     * @param id id
     * @return org.springframework.data.domain.Page<org.big.entity.Dataset>
     */
    Dataset findOneById(String id);

    /**
     *<b>根据Team的name查询一个符合条件的Team</b>
     *<p> 根据Team的name查询一个符合条件的Team</p>
     * @author BINZI
     * @param dsabstraction 备注
     * @param thisUser 当前用户
     * @return org.springframework.data.domain.Page<org.big.entity.Dataset>
     */
    Dataset findOneByDsabstractAndTeam(String dsabstract,Team thisTeam);
    
    /**
     *<b>根据Dataset的Mark查询一个符合条件的Dataset</b>
     *<p> 根据Dataset的Mark查询一个符合条件的Dataset</p>
     * @author BINZI 
     * @param mark
     * @return org.springframework.data.domain.Page<org.big.entity.Dataset>
     */
    Dataset findOneByMark(String mark);
    
}
