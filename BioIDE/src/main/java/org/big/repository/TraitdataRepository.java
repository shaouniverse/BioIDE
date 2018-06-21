package org.big.repository;

import org.big.entity.Traitdata;
import org.big.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TraitdataRepository extends BaseRepository<Traitdata, String> {
	/**
     *<b>通过Id查找一个实体</b>
     *<p> 通过Id查找一个实体</p>
     * @author BINZI
     * @param traitdataId
     * @return org.big.entity.Traitdata
     */
	@Query(value = "Select td from Traitdata td where td.id = ?1")
	Object findOneById(String traitdataId);
	
	/**
     *<b>通过Id删除一个实体</b>
     *<p> 通过Id删除一个实体</p>
     * @author BINZI
     * @param traitdataId
     */
	@Modifying
	@Transactional
	@Query("Delete Traitdata td where td.id =?1")
	void deleteOneById(String traitdataId);

}
