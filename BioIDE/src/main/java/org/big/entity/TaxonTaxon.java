package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the taxon_taxon database table.
 * 
 */
@Entity
@Table(name="taxon_taxon")
@NamedQuery(name="TaxonTaxon.findAll", query="SELECT t FROM TaxonTaxon t")
public class TaxonTaxon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String refjson;

	private Object relationship;

	private String sourcesid;

	private int status;

	private int synchstatus;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchtime;

	public TaxonTaxon() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInputer() {
		return this.inputer;
	}

	public void setInputer(String inputer) {
		this.inputer = inputer;
	}

	public Date getInputtime() {
		return this.inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	public String getRefjson() {
		return this.refjson;
	}

	public void setRefjson(String refjson) {
		this.refjson = refjson;
	}

	public Object getRelationship() {
		return this.relationship;
	}

	public void setRelationship(Object relationship) {
		this.relationship = relationship;
	}

	public String getSourcesid() {
		return this.sourcesid;
	}

	public void setSourcesid(String sourcesid) {
		this.sourcesid = sourcesid;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSynchstatus() {
		return this.synchstatus;
	}

	public void setSynchstatus(int synchstatus) {
		this.synchstatus = synchstatus;
	}

	public Date getSynchtime() {
		return this.synchtime;
	}

	public void setSynchtime(Date synchtime) {
		this.synchtime = synchtime;
	}

}