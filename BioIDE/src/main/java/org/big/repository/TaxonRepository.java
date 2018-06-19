package org.big.repository;

import org.big.entity.Taxon;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *<p><b>Taxon的DAO类接口</b></p>
 *<p> Taxon的DAO类接口，与User有关的持久化操作方法</p>
 * @author BINZI
 *<p>Created date: 2018/06/13 10:59</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
public interface TaxonRepository extends BaseRepository<Taxon, String> {
    /**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param searchText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Taxon>
     */
    @Query(value = "Select t from Taxon t where ("
    		+ "t.scientificname like %?1% or "
    		+ "t.authorstr like %?1% or "
    		+ "t.inputer like %?1% or "
    		+ "t.synchdate like %?1%) and t.status = 1")
	Page<Taxon> searchInfo(String searchText, Pageable pageable);

    /**
     *<b>根据TaxonId查找一个Taxon实体</b>
     *<p> 据id查找一个实体</p>
     * @author BINZI
     * @param Id 实体的id
     * @return org.big.entity.Taxon
     */
	@Query(value = "Select t From Taxon t Where t.id = ?1")
	Taxon findOneById(String id);
	/**
     *<b>Taxon的select列表</b>
     *<p> 当前Taxkey下的Taxon的select检索列表</p>
     * @author BINZI
     * @param findText
     * @param pageable
     * @return com.alibaba.fastjson.JSON
     */
    @Query(value = "select t from Taxon t where (t.scientificname like %?1%) and t.status = 1")
	Page<Taxon> searchByTaxonInfo(String findText, Pageable pageable);
	
}
