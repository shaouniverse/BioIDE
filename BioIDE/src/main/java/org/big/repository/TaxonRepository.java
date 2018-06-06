package org.big.repository;

import org.big.entity.Taxon;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
    @Query(value = "Select t from Taxon t where (t.scientificname like %?1% or t.authorstr like %?1% or t.inputer like %?1% or t.inputtime like %?1%) and t.status = 1")
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
	
}
