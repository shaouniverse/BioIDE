package org.big.repository;

import org.big.entity.License;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *<p><b>License的DAO类接口</b></p>
 *<p> License的DAO类接口，与User有关的持久化操作方法</p>
 * @author BINZI
 *<p>Created date: 2018/06/13 10:59</p>
 *<p>Copyright: The Research Group of Biodiversity Informatics (BiodInfo Group) - 中国科学院动物研究所生物多样性信息学研究组</p>
 * @version: 0.1
 * @since JDK 1.80_144
 */
@Repository
public interface LicenseRepository extends BaseRepository<License, String> {
	@Query(value = "Select L from License L where (L.title like %?1% or L.text like %?1%) and L.status = 1")
	Page<License> searchByEnname(String findText, Pageable pageable);

}
