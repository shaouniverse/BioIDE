package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the protection database table.
 * 
 */
@Entity
@NamedQuery(name="Protection.findAll", query="SELECT p FROM Protection p")
public class Protection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String proassessment;

	private String protlevel;

	private Object refjson;

	private String sourcesid;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	//bi-directional many-to-one association to Protectstandard
	@ManyToOne
	private Protectstandard protectstandard;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	public Protection() {
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

	public String getProassessment() {
		return this.proassessment;
	}

	public void setProassessment(String proassessment) {
		this.proassessment = proassessment;
	}

	public String getProtlevel() {
		return this.protlevel;
	}

	public void setProtlevel(String protlevel) {
		this.protlevel = protlevel;
	}

	public Object getRefjson() {
		return this.refjson;
	}

	public void setRefjson(Object refjson) {
		this.refjson = refjson;
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

	public Protectstandard getProtectstandard() {
		return this.protectstandard;
	}

	public void setProtectstandard(Protectstandard protectstandard) {
		this.protectstandard = protectstandard;
	}

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

}