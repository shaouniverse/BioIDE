package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rank database table.
 * 
 */
@Entity
@NamedQuery(name="Rank.findAll", query="SELECT r FROM Rank r")
public class Rank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String chname;

	private String enname;

	//bi-directional many-to-one association to Taxon
	@OneToMany(mappedBy="rank")
	private List<Taxon> taxons;

	public Rank() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChname() {
		return this.chname;
	}

	public void setChname(String chname) {
		this.chname = chname;
	}

	public String getEnname() {
		return this.enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public List<Taxon> getTaxons() {
		return this.taxons;
	}

	public void setTaxons(List<Taxon> taxons) {
		this.taxons = taxons;
	}

	public Taxon addTaxon(Taxon taxon) {
		getTaxons().add(taxon);
		taxon.setRank(this);

		return taxon;
	}

	public Taxon removeTaxon(Taxon taxon) {
		getTaxons().remove(taxon);
		taxon.setRank(null);

		return taxon;
	}

}