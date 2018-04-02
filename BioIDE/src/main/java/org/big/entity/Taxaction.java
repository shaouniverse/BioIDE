package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the taxaction database table.
 * 
 */
@Entity
@NamedQuery(name="Taxaction.findAll", query="SELECT t FROM Taxaction t")
public class Taxaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	private Date actdate;

	private Object actions;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String taxonid;

	public Taxaction() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getActdate() {
		return this.actdate;
	}

	public void setActdate(Date actdate) {
		this.actdate = actdate;
	}

	public Object getActions() {
		return this.actions;
	}

	public void setActions(Object actions) {
		this.actions = actions;
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

}