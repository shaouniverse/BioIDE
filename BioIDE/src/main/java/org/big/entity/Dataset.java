package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the dataset database table.
 * 
 */
@Entity
@NamedQuery(name="Dataset.findAll", query="SELECT d FROM Dataset d")
public class Dataset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.DATE)
	private Date createdDate;

	private String creator;

	private String dsabstract;

	private String dsname;

	private String lisenceid;

	private String mark;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String teamid;

	private String userid;

	//bi-directional many-to-one association to Phytree
	@OneToMany(mappedBy="dataset")
	private List<Phytree> phytrees;

	//bi-directional many-to-one association to Taxaset
	@OneToMany(mappedBy="dataset")
	private List<Taxaset> taxasets;

	//bi-directional many-to-one association to Taxtree
	@OneToMany(mappedBy="dataset")
	private List<Taxtree> taxtrees;

	public Dataset() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDsabstract() {
		return this.dsabstract;
	}

	public void setDsabstract(String dsabstract) {
		this.dsabstract = dsabstract;
	}

	public String getDsname() {
		return this.dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	public String getLisenceid() {
		return this.lisenceid;
	}

	public void setLisenceid(String lisenceid) {
		this.lisenceid = lisenceid;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
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

	public String getTeamid() {
		return this.teamid;
	}

	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Phytree> getPhytrees() {
		return this.phytrees;
	}

	public void setPhytrees(List<Phytree> phytrees) {
		this.phytrees = phytrees;
	}

	public Phytree addPhytree(Phytree phytree) {
		getPhytrees().add(phytree);
		phytree.setDataset(this);

		return phytree;
	}

	public Phytree removePhytree(Phytree phytree) {
		getPhytrees().remove(phytree);
		phytree.setDataset(null);

		return phytree;
	}

	public List<Taxaset> getTaxasets() {
		return this.taxasets;
	}

	public void setTaxasets(List<Taxaset> taxasets) {
		this.taxasets = taxasets;
	}

	public Taxaset addTaxaset(Taxaset taxaset) {
		getTaxasets().add(taxaset);
		taxaset.setDataset(this);

		return taxaset;
	}

	public Taxaset removeTaxaset(Taxaset taxaset) {
		getTaxasets().remove(taxaset);
		taxaset.setDataset(null);

		return taxaset;
	}

	public List<Taxtree> getTaxtrees() {
		return this.taxtrees;
	}

	public void setTaxtrees(List<Taxtree> taxtrees) {
		this.taxtrees = taxtrees;
	}

	public Taxtree addTaxtree(Taxtree taxtree) {
		getTaxtrees().add(taxtree);
		taxtree.setDataset(this);

		return taxtree;
	}

	public Taxtree removeTaxtree(Taxtree taxtree) {
		getTaxtrees().remove(taxtree);
		taxtree.setDataset(null);

		return taxtree;
	}

}