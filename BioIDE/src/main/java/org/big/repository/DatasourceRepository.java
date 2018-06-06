package org.big.repository;

import org.big.entity.Datasource;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DatasourceRepository extends BaseRepository<Datasource, String> {
    /**
     *<b>Datasources的select列表</b>
     *<p> 当前Dataset下的Datasources的select检索列表</p>
     * @author BINZI
     * @param findText
     * @param datasetID
     * @param pageable
     * @return com.alibaba.fastjson.JSON
     */
    @Query(value = "select ds from Datasource ds where (ds.title like %?1%) and ds.dataset.id = ?2 and ds.status = 1")
	Page<Datasource> searchByDsname(String findText, String datasetID, Pageable pageable);
    
    /**
     *<b>带分页排序的条件查询</b>
     *<p> 带分页排序的条件查询</p>
     * @author BINZI
     * @param findText 条件关键词，这里是模糊匹配
     * @param pageable 分页排序方案实体
     * @return org.springframework.data.domain.Page<org.big.entity.Taxaset>
     */
    @Query(value = "Select ds from Datasource ds where ("
    		+ "ds.title like %?1% or "
    		+ "ds.inputer like %?1% or "
    		+ "ds.inputtime like %?1% or "
    		+ "ds.versions like %?1%) and "
    		+ "ds.status = 1 and "
    		+ "ds.dataset.id = ?2")
	Page<Datasource> searchInfo(String searchText, Pageable pageable, String dsId);
   
    /**
     *<b>根据DatasourceId查找一个Datasource实体</b>
     *<p> 根据id查找一个实体</p>
     * @author BINZI
     * @param Id 实体的id
     * @return org.big.entity.Taxaset
     */
	@Query(value = "Select ds From Datasource ds Where ds.id = ?1")
	Datasource findOneById(String id);
    /**
     *<b>根据id删除一个实体</b>
     *<p> 据id删除一个实体</p>
     * @author BINZI
     * @param ID 实体的id
     * @return void
     */
	@Transactional
	@Modifying
	@Query(value = "Delete From Datasource ds Where ds.id = ?1")
	void deleteOneById(String id);

}
