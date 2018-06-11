package org.big.repository;

import org.big.entity.Citation;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface CitationRepository extends BaseRepository<Citation, String> {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Citation>
     */
	@Query(value = "Select c from Citation c where ("
			+ "c.sciname like %?1% or "
			+ "c.authorship like %?1% or "
			+ "c.nametype like %?1% or "
			+ "c.inputer like %?1% or "
			+ "c.synchdate like %?1%) and "
			+ "c.status = 1")
	Page<Citation> searchInfo(String searchText, Pageable pageable);

}
