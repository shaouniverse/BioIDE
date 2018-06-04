package org.big.service;

import javax.validation.Valid;

import org.big.entity.Taxon;

public interface TaxonService {
	/**
	 * <b> 添加Taxon基础数据</b>
	 * <p> 添加Taxon基础数据</p>
	 * @author BINZI
	 * @param thisTaxon
	 */
	void addTaxonBaseInfo(@Valid Taxon thisTaxon);
	// 自定义Taxon增删改查
}
