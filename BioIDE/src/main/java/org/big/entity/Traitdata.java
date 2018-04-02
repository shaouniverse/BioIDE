package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the traitdata database table.
 * 
 */
@Entity
@NamedQuery(name="Traitdata.findAll", query="SELECT t FROM Traitdata t")
public class Traitdata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String desid;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private Object refjson;

	private Object sourcesjson;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String taxonid;

	private String trainsetid;

	private Object traitjson;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	public Traitdata() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesid() {
		return this.desid;
	}

	public void setDesid(String desid) {
		this.desid = desid;
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

	public Object getRefjson() {
		return this.refjson;
	}

	public void setRefjson(Object refjson) {
		this.refjson = refjson;
	}

	public Object getSourcesjson() {
		return this.sourcesjson;
	}

	public void setSourcesjson(Object sourcesjson) {
		this.sourcesjson = sourcesjson;
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

	public String getTaxonid() {
		return this.taxonid;
	}

	public void setTaxonid(String taxonid) {
		this.taxonid = taxonid;
	}

	public String getTrainsetid() {
		return this.trainsetid;
	}

	public void setTrainsetid(String trainsetid) {
		this.trainsetid = trainsetid;
	}

	public Object getTraitjson() {
		return this.traitjson;
	}

	public void setTraitjson(Object traitjson) {
		this.traitjson = traitjson;
	}

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

}