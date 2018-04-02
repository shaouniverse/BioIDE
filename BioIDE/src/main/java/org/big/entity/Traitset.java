package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the traitset database table.
 * 
 */
@Entity
@NamedQuery(name="Traitset.findAll", query="SELECT t FROM Traitset t")
public class Traitset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String name;

	@Lob
	private String remark;

	private Object sourcesid;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	//bi-directional many-to-one association to TraitsetHasTraitontology
	@OneToMany(mappedBy="traitset")
	private List<TraitsetHasTraitontology> traitsetHasTraitontologies;

	public Traitset() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Object getSourcesid() {
		return this.sourcesid;
	}

	public void setSourcesid(Object sourcesid) {
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

	public List<TraitsetHasTraitontology> getTraitsetHasTraitontologies() {
		return this.traitsetHasTraitontologies;
	}

	public void setTraitsetHasTraitontologies(List<TraitsetHasTraitontology> traitsetHasTraitontologies) {
		this.traitsetHasTraitontologies = traitsetHasTraitontologies;
	}

	public TraitsetHasTraitontology addTraitsetHasTraitontology(TraitsetHasTraitontology traitsetHasTraitontology) {
		getTraitsetHasTraitontologies().add(traitsetHasTraitontology);
		traitsetHasTraitontology.setTraitset(this);

		return traitsetHasTraitontology;
	}

	public TraitsetHasTraitontology removeTraitsetHasTraitontology(TraitsetHasTraitontology traitsetHasTraitontology) {
		getTraitsetHasTraitontologies().remove(traitsetHasTraitontology);
		traitsetHasTraitontology.setTraitset(null);

		return traitsetHasTraitontology;
	}

}