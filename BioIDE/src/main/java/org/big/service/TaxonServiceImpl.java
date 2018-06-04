package org.big.service;

import java.sql.Timestamp;
import java.util.UUID;

import javax.validation.Valid;

import org.big.entity.Taxon;
import org.big.entity.UserDetail;
import org.big.repository.TaxonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public class TaxonServiceImpl implements TaxonService {
	@Autowired
	private TaxonRepository taxonRepository;
	
	@Override
	public void addTaxonBaseInfo(@Valid Taxon thisTaxon) {
		thisTaxon.setId(UUID.randomUUID().toString());
		UserDetail thisUser = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		thisTaxon.setInputer(thisUser.getId());
		thisTaxon.setInputtime(new Timestamp(System.currentTimeMillis()));
		thisTaxon.setSynchdate(new Timestamp(System.currentTimeMillis()));
		thisTaxon.setStatus(1);
		thisTaxon.setSynchstatus(0);
		System.out.println(thisTaxon);
		this.taxonRepository.save(thisTaxon);
	}
	
}
