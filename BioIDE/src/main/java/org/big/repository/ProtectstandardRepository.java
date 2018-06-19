package org.big.repository;

import org.big.entity.Protectstandard;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * <p><b>Protectstandard的DAO类接口</b></p>
 * <p> Protectstandard的DAO类接口，与User有关的持久化操作方法</p>
 * @author BINZI
 * <p>Created date: 2018/06/14 15:19</p>
 * <p>Copyright: The Research Group of Biodiversity Informatics (BiodInfoGroup) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
public interface ProtectstandardRepository extends BaseRepository<Protectstandard, String> {
	/**
	 * <b>带分页排序的条件查询</b>
	 * <p> 带分页排序的条件查询 </p>
	 * @author BINZI
	 * @param findText 条件关键词，这里是模糊匹配
	 * @param pageable 分页排序方案实体
	 * @return org.springframework.data.domain.Page <org.big.entity.Distributiondata>
	 */
	/*
	 * @Query(value = "Select ps from Protectstandard ps where (" +
	 * "ps.protlevel like %?1% or " + "ps.proassessment like %?1% or " +
	 * "ps.inputer like %?1% or " + "ps.inputtime like %?1% or " +
	 * "ps.synchdate like %?1%) and " + "ps.status = 1") Page<Protectstandard>
	 * searchInfo(String searchText, Pageable pageable);
	 */

	/**
	 * <b>通过Id查找一个实体</b>
	 * <p> 通过Id查找一个实体 </p>
	 * @author BINZI
	 * @param id
	 * @return org.big.entity.Protection
	 */
	@Query(value = "Select ps from Protectstandard ps where ps.id = ?1")
	Protectstandard findOneById(String id);

	/**
     *<b>Proteciton的select列表(保护标准)</b>
     *<p> 当前Taxon下的Proteciton的select检索列表</p>
     * @author BINZI
     * @param findText
     * @param pageable
     * @return com.alibaba.fastjson.JSON
     */
    @Query(value = "Select distinct ps.standardname from Protectstandard ps where (ps.standardname like %?1%) and ps.status = 1")
	Page<String> searchByStandardname(String findText, Pageable pageable);
    
    /**
     *<b>Proteciton的select列表(保护版本)</b>
     *<p> 当前Taxon下的Proteciton的select检索列表</p>
     * @author BINZI
     * @param findText
     * @param pageable
     * @return com.alibaba.fastjson.JSON
     */				
    @Query(value = "Select distinct ps.version, ps.releasedate from Protectstandard ps where (ps.version like %?1% or ps.releasedate like %?1%) and ps.standardname = ?2 and ps.status = 1")
    Page<String> searchByVersion(String findText, Pageable pageable, String standardname);
	
    /**
     *<b>Proteciton的select列表(保护级别)</b>
     *<p> 当前Taxon下的Proteciton的select检索列表</p>
     * @author BINZI
     * @param findText
     * @param pageable
     * @return com.alibaba.fastjson.JSON
     */
    @Query(value = "Select distinct ps.protlevel from Protectstandard ps where (ps.protlevel like %?1%) and ps.version = ?2 and ps.status = 1")
	Page<String> searchByProtlevel(String findText, Pageable pageable, String version);

}
