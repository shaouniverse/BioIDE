package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the taxaset database table.
 * 
 */
@Entity
@NamedQuery(name="Taxaset.findAll", query="SELECT t FROM Taxaset t")
public class Taxaset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Object refsjson;

	private Object sourcejson;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String tsinfo;

	private String tsname;

	//bi-directional many-to-one association to Dataset
	@ManyToOne
	private Dataset dataset;

	//bi-directional many-to-one association to Taxon
	@OneToMany(mappedBy="taxaset")
	private List<Taxon> taxons;

	public Taxaset() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getRefsjson() {
		return this.refsjson;
	}

	public void setRefsjson(Object refsjson) {
		this.refsjson = refsjson;
	}

	public Object getSourcejson() {
		return this.sourcejson;
	}

	public void setSourcejson(Object sourcejson) {
		this.sourcejson = sourcejson;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getSynchdate() {
		return this.synchdate;
	}

	public void setSynchdate(Date synchdate) {
		this.synchdate = synchdate;
	}

	public int getSynchstatus() {
		return this.synchstatus;
	}

	public void setSynchstatus(int synchstatus) {
		this.synchstatus = synchstatus;
	}

	public String getTsinfo() {
		return this.tsinfo;
	}

	public void setTsinfo(String tsinfo) {
		this.tsinfo = tsinfo;
	}

	public String getTsname() {
		return this.tsname;
	}

	public void setTsname(String tsname) {
		this.tsname = tsname;
	}

	public Dataset getDataset() {
		return this.dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public List<Taxon> getTaxons() {
		return this.taxons;
	}

	public void setTaxons(List<Taxon> taxons) {
		this.taxons = taxons;
	}

	public Taxon addTaxon(Taxon taxon) {
		getTaxons().add(taxon);
		taxon.setTaxaset(this);

		return taxon;
	}

	public Taxon removeTaxon(Taxon taxon) {
		getTaxons().remove(taxon);
		taxon.setTaxaset(null);

		return taxon;
	}

}