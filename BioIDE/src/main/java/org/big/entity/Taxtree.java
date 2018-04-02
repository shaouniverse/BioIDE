package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the taxtree database table.
 * 
 */
@Entity
@NamedQuery(name="Taxtree.findAll", query="SELECT t FROM Taxtree t")
public class Taxtree implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private Object refsjson;

	private Object sourcejson;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String taxtreecol;

	private String treeinfo;

	private String treename;

	//bi-directional many-to-many association to Taxon
	@ManyToMany
	@JoinTable(
		name="taxon_has_taxtree"
		, joinColumns={
			@JoinColumn(name="taxtree_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="taxon_id")
			}
		)
	private List<Taxon> taxons;

	//bi-directional many-to-one association to Dataset
	@ManyToOne
	private Dataset dataset;

	public Taxtree() {
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

	public String getTaxtreecol() {
		return this.taxtreecol;
	}

	public void setTaxtreecol(String taxtreecol) {
		this.taxtreecol = taxtreecol;
	}

	public String getTreeinfo() {
		return this.treeinfo;
	}

	public void setTreeinfo(String treeinfo) {
		this.treeinfo = treeinfo;
	}

	public String getTreename() {
		return this.treename;
	}

	public void setTreename(String treename) {
		this.treename = treename;
	}

	public List<Taxon> getTaxons() {
		return this.taxons;
	}

	public void setTaxons(List<Taxon> taxons) {
		this.taxons = taxons;
	}

	public Dataset getDataset() {
		return this.dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

}