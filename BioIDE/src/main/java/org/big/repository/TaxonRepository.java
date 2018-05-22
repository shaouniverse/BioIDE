package org.big.repository;

import org.big.entity.Taxon;
import org.big.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxonRepository extends BaseRepository<Taxon, String> {
	
}
