package org.big.repository;

import org.big.entity.Description;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *<p><b>Description的DAO类接口</b></p>
 *<p> Description的DAO类接口，与User有关的持久化操作方法</p>
 * @author BINZI
 *<p>Created date: 2018/06/13 10:59</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
public interface DescriptionRepository extends BaseRepository<Description, String> {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Citation>
     */
	@Query(value = "Select d from Description d where ("
			+ "d.destitle like %?1% or "
			+ "d.describer like %?1% or "
			+ "d.desdate like %?1% or "
			+ "d.rightsholder like %?1% or "
			+ "d.language like %?1% or "
			+ "d.destypeid like %?1% or "
			+ "d.inputer like %?1% or "
			+ "d.inputtime like %?1% or "
			+ "d.synchdate like %?1%) and "
			+ "d.status = 1")
	Page<Description> searchInfo(String searchText, Pageable pageable);

	/**
     *<b>通过Id查找一个实体</b>
     *<p> 通过Id查找一个实体</p>
     * @author BINZI
     * @param id
     * @return org.big.entity.Description
     */
	@Query(value = "Select d from Description d where d.id = ?1")
	Description findOneById(String id);
}
