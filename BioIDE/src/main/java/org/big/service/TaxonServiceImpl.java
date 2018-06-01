package org.big.service;

import javax.validation.Valid;

import org.big.entity.Taxon;
import org.springframework.stereotype.Service;
@Service
public class TaxonServiceImpl implements TaxonService {

	@Override
	public void addBaseInfo(@Valid Taxon thisTaxon) {
		
	}
	
}
