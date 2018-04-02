package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the citation database table.
 * 
 */
@Entity
@NamedQuery(name="Citation.findAll", query="SELECT c FROM Citation c")
public class Citation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String authorship;

	private String citationstr;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private int nametype;

	private Object refjson;

	private String sciname;

	private Object shortrefs;

	private String sourcesid;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	public Citation() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorship() {
		return this.authorship;
	}

	public void setAuthorship(String authorship) {
		this.authorship = authorship;
	}

	public String getCitationstr() {
		return this.citationstr;
	}

	public void setCitationstr(String citationstr) {
		this.citationstr = citationstr;
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

	public int getNametype() {
		return this.nametype;
	}

	public void setNametype(int nametype) {
		this.nametype = nametype;
	}

	public Object getRefjson() {
		return this.refjson;
	}

	public void setRefjson(Object refjson) {
		this.refjson = refjson;
	}

	public String getSciname() {
		return this.sciname;
	}

	public void setSciname(String sciname) {
		this.sciname = sciname;
	}

	public Object getShortrefs() {
		return this.shortrefs;
	}

	public void setShortrefs(Object shortrefs) {
		this.shortrefs = shortrefs;
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

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

}