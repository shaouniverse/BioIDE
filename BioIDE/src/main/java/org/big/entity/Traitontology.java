package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the traitontology database table.
 * 
 */
@Entity
@NamedQuery(name="Traitontology.findAll", query="SELECT t FROM Traitontology t")
public class Traitontology implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String catalog1;

	private String catalog2;

	private String catalog3;

	private String cnterm;

	@Lob
	private String definition;

	private String enterm;

	private String group;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private Object sourcesjson;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private Object synonymys;

	//bi-directional many-to-one association to TraitsetHasTraitontology
	@OneToMany(mappedBy="traitontology")
	private List<TraitsetHasTraitontology> traitsetHasTraitontologies;

	public Traitontology() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatalog1() {
		return this.catalog1;
	}

	public void setCatalog1(String catalog1) {
		this.catalog1 = catalog1;
	}

	public String getCatalog2() {
		return this.catalog2;
	}

	public void setCatalog2(String catalog2) {
		this.catalog2 = catalog2;
	}

	public String getCatalog3() {
		return this.catalog3;
	}

	public void setCatalog3(String catalog3) {
		this.catalog3 = catalog3;
	}

	public String getCnterm() {
		return this.cnterm;
	}

	public void setCnterm(String cnterm) {
		this.cnterm = cnterm;
	}

	public String getDefinition() {
		return this.definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getEnterm() {
		return this.enterm;
	}

	public void setEnterm(String enterm) {
		this.enterm = enterm;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	public Object getSynonymys() {
		return this.synonymys;
	}

	public void setSynonymys(Object synonymys) {
		this.synonymys = synonymys;
	}

	public List<TraitsetHasTraitontology> getTraitsetHasTraitontologies() {
		return this.traitsetHasTraitontologies;
	}

	public void setTraitsetHasTraitontologies(List<TraitsetHasTraitontology> traitsetHasTraitontologies) {
		this.traitsetHasTraitontologies = traitsetHasTraitontologies;
	}

	public TraitsetHasTraitontology addTraitsetHasTraitontology(TraitsetHasTraitontology traitsetHasTraitontology) {
		getTraitsetHasTraitontologies().add(traitsetHasTraitontology);
		traitsetHasTraitontology.setTraitontology(this);

		return traitsetHasTraitontology;
	}

	public TraitsetHasTraitontology removeTraitsetHasTraitontology(TraitsetHasTraitontology traitsetHasTraitontology) {
		getTraitsetHasTraitontologies().remove(traitsetHasTraitontology);
		traitsetHasTraitontology.setTraitontology(null);

		return traitsetHasTraitontology;
	}

}