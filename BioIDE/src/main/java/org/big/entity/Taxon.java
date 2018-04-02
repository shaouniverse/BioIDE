package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the taxon database table.
 * 
 */
@Entity
@NamedQuery(name="Taxon.findAll", query="SELECT t FROM Taxon t")
public class Taxon implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String authorstr;

	private String epithet;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String nomencode;

	private int rankid;

	private Object refjson;

	private String remark;

	private String scientificname;

	private String sourcesid;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String tci;

	//bi-directional many-to-one association to Citation
	@OneToMany(mappedBy="taxon")
	private List<Citation> citations;

	//bi-directional many-to-one association to Description
	@OneToMany(mappedBy="taxon")
	private List<Description> descriptions;

	//bi-directional many-to-one association to Distributiondata
	@OneToMany(mappedBy="taxon")
	private List<Distributiondata> distributiondata;

	//bi-directional many-to-one association to Molecular
	@OneToMany(mappedBy="taxon")
	private List<Molecular> moleculars;

	//bi-directional many-to-one association to Multimedia
	@OneToMany(mappedBy="taxon")
	private List<Multimedia> multimedias;

	//bi-directional many-to-one association to Occurrence
	@OneToMany(mappedBy="taxon")
	private List<Occurrence> occurrences;

	//bi-directional many-to-one association to Protection
	@OneToMany(mappedBy="taxon")
	private List<Protection> protections;

	//bi-directional many-to-one association to Specimendata
	@OneToMany(mappedBy="taxon")
	private List<Specimendata> specimendata;

	//bi-directional many-to-one association to Taxkey
	@OneToMany(mappedBy="taxon")
	private List<Taxkey> taxkeys;

	//bi-directional many-to-one association to Rank
	@ManyToOne
	private Rank rank;

	//bi-directional many-to-one association to Taxaset
	@ManyToOne
	private Taxaset taxaset;

	//bi-directional many-to-many association to Taxtree
	@ManyToMany(mappedBy="taxons")
	private List<Taxtree> taxtrees;

	//bi-directional many-to-one association to Traitdata
	@OneToMany(mappedBy="taxon")
	private List<Traitdata> traitdata;

	public Taxon() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorstr() {
		return this.authorstr;
	}

	public void setAuthorstr(String authorstr) {
		this.authorstr = authorstr;
	}

	public String getEpithet() {
		return this.epithet;
	}

	public void setEpithet(String epithet) {
		this.epithet = epithet;
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

	public String getNomencode() {
		return this.nomencode;
	}

	public void setNomencode(String nomencode) {
		this.nomencode = nomencode;
	}

	public int getRankid() {
		return this.rankid;
	}

	public void setRankid(int rankid) {
		this.rankid = rankid;
	}

	public Object getRefjson() {
		return this.refjson;
	}

	public void setRefjson(Object refjson) {
		this.refjson = refjson;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getScientificname() {
		return this.scientificname;
	}

	public void setScientificname(String scientificname) {
		this.scientificname = scientificname;
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

	public String getTci() {
		return this.tci;
	}

	public void setTci(String tci) {
		this.tci = tci;
	}

	public List<Citation> getCitations() {
		return this.citations;
	}

	public void setCitations(List<Citation> citations) {
		this.citations = citations;
	}

	public Citation addCitation(Citation citation) {
		getCitations().add(citation);
		citation.setTaxon(this);

		return citation;
	}

	public Citation removeCitation(Citation citation) {
		getCitations().remove(citation);
		citation.setTaxon(null);

		return citation;
	}

	public List<Description> getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(List<Description> descriptions) {
		this.descriptions = descriptions;
	}

	public Description addDescription(Description description) {
		getDescriptions().add(description);
		description.setTaxon(this);

		return description;
	}

	public Description removeDescription(Description description) {
		getDescriptions().remove(description);
		description.setTaxon(null);

		return description;
	}

	public List<Distributiondata> getDistributiondata() {
		return this.distributiondata;
	}

	public void setDistributiondata(List<Distributiondata> distributiondata) {
		this.distributiondata = distributiondata;
	}

	public Distributiondata addDistributiondata(Distributiondata distributiondata) {
		getDistributiondata().add(distributiondata);
		distributiondata.setTaxon(this);

		return distributiondata;
	}

	public Distributiondata removeDistributiondata(Distributiondata distributiondata) {
		getDistributiondata().remove(distributiondata);
		distributiondata.setTaxon(null);

		return distributiondata;
	}

	public List<Molecular> getMoleculars() {
		return this.moleculars;
	}

	public void setMoleculars(List<Molecular> moleculars) {
		this.moleculars = moleculars;
	}

	public Molecular addMolecular(Molecular molecular) {
		getMoleculars().add(molecular);
		molecular.setTaxon(this);

		return molecular;
	}

	public Molecular removeMolecular(Molecular molecular) {
		getMoleculars().remove(molecular);
		molecular.setTaxon(null);

		return molecular;
	}

	public List<Multimedia> getMultimedias() {
		return this.multimedias;
	}

	public void setMultimedias(List<Multimedia> multimedias) {
		this.multimedias = multimedias;
	}

	public Multimedia addMultimedia(Multimedia multimedia) {
		getMultimedias().add(multimedia);
		multimedia.setTaxon(this);

		return multimedia;
	}

	public Multimedia removeMultimedia(Multimedia multimedia) {
		getMultimedias().remove(multimedia);
		multimedia.setTaxon(null);

		return multimedia;
	}

	public List<Occurrence> getOccurrences() {
		return this.occurrences;
	}

	public void setOccurrences(List<Occurrence> occurrences) {
		this.occurrences = occurrences;
	}

	public Occurrence addOccurrence(Occurrence occurrence) {
		getOccurrences().add(occurrence);
		occurrence.setTaxon(this);

		return occurrence;
	}

	public Occurrence removeOccurrence(Occurrence occurrence) {
		getOccurrences().remove(occurrence);
		occurrence.setTaxon(null);

		return occurrence;
	}

	public List<Protection> getProtections() {
		return this.protections;
	}

	public void setProtections(List<Protection> protections) {
		this.protections = protections;
	}

	public Protection addProtection(Protection protection) {
		getProtections().add(protection);
		protection.setTaxon(this);

		return protection;
	}

	public Protection removeProtection(Protection protection) {
		getProtections().remove(protection);
		protection.setTaxon(null);

		return protection;
	}

	public List<Specimendata> getSpecimendata() {
		return this.specimendata;
	}

	public void setSpecimendata(List<Specimendata> specimendata) {
		this.specimendata = specimendata;
	}

	public Specimendata addSpecimendata(Specimendata specimendata) {
		getSpecimendata().add(specimendata);
		specimendata.setTaxon(this);

		return specimendata;
	}

	public Specimendata removeSpecimendata(Specimendata specimendata) {
		getSpecimendata().remove(specimendata);
		specimendata.setTaxon(null);

		return specimendata;
	}

	public List<Taxkey> getTaxkeys() {
		return this.taxkeys;
	}

	public void setTaxkeys(List<Taxkey> taxkeys) {
		this.taxkeys = taxkeys;
	}

	public Taxkey addTaxkey(Taxkey taxkey) {
		getTaxkeys().add(taxkey);
		taxkey.setTaxon(this);

		return taxkey;
	}

	public Taxkey removeTaxkey(Taxkey taxkey) {
		getTaxkeys().remove(taxkey);
		taxkey.setTaxon(null);

		return taxkey;
	}

	public Rank getRank() {
		return this.rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Taxaset getTaxaset() {
		return this.taxaset;
	}

	public void setTaxaset(Taxaset taxaset) {
		this.taxaset = taxaset;
	}

	public List<Taxtree> getTaxtrees() {
		return this.taxtrees;
	}

	public void setTaxtrees(List<Taxtree> taxtrees) {
		this.taxtrees = taxtrees;
	}

	public List<Traitdata> getTraitdata() {
		return this.traitdata;
	}

	public void setTraitdata(List<Traitdata> traitdata) {
		this.traitdata = traitdata;
	}

	public Traitdata addTraitdata(Traitdata traitdata) {
		getTraitdata().add(traitdata);
		traitdata.setTaxon(this);

		return traitdata;
	}

	public Traitdata removeTraitdata(Traitdata traitdata) {
		getTraitdata().remove(traitdata);
		traitdata.setTaxon(null);

		return traitdata;
	}

}