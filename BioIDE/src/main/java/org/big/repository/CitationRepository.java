package org.big.repository;

import org.big.entity.Citation;

import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *<p><b>Citation的DAO类接口</b></p>
 *<p> Citation的DAO类接口，与User有关的持久化操作方法</p>
 * @author BINZI
 *<p>Created date: 2018/06/11 10:35</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
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
			+ "c.inputtime like %?1% or "
			+ "c.synchdate like %?1%) and "
			+ "c.status = 1")
	Page<Citation> searchInfo(String searchText, Pageable pageable);

	/**
	 *<b>根据id查询一个实体</b>
     *<p> 带分页排序的条件查询</p>
	 * @param id
	 * @return
	 */
	@Query(value = "Select c from Citation c Where c.id = ?1")
	Citation findOneById(String id);

}
