package org.big.repository;

import org.big.entity.License;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends BaseRepository<License, String> {
	@Query(value = "Select L from License L where (L.title like %?1% or L.text like %?1%) and L.status = 1")
	Page<License> searchByEnname(String findText, Pageable pageable);

}
