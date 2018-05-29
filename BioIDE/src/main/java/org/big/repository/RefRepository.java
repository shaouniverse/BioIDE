package org.big.repository;

import org.big.entity.Ref;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface RefRepository extends BaseRepository<Ref, String> {
	/**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Taxaset>
     */
    @Query(value = "Select r from Ref r "
    		+ "where ("
    		+ "r.author like %?1% or "
    		+ "r.title like %?1% or "
    		+ "r.ptype like %?1% or "
    		+ "r.journal like %?1% or "
    		+ "r.press like %?1% or "
    		+ "r.pyear like %?1% or "
    		+ "r.version like %?1%) and "
    		+ "r.status = 1")
	Page<Ref> searchInfo(String searchText, Pageable pageable);

    /**
     *<b>根据RefId查找一个Reference实体</b>
     *<p> 据id查找一个实体</p>
     * @author BINZI
     * @param Id 实体的id
     * @return org.big.entity.Ref
     */
	@Query(value = "Select r From Ref r Where r.id = ?1")
	Ref findOneById(String id);
	
    /**
     *<b>根据id删除一个实体</b>
     *<p> 据id删除一个实体</p>
     * @author BINZI
     * @param ID 实体的id
     * @return void
     */
	@Transactional
	@Modifying
	@Query(value = "Delete From Ref r Where r.id = ?1")
	void deleteOneById(String id);

}
