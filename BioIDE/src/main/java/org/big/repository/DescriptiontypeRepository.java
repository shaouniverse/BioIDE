package org.big.repository;

import org.big.entity.Descriptiontype;
import org.big.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptiontypeRepository extends BaseRepository<Descriptiontype, String> {
	@Query(value = "Select dt from Descriptiontype dt where (dt.name like %?1%)")
	Page<Descriptiontype> searchByDsname(String findText, Pageable pageable);

}
