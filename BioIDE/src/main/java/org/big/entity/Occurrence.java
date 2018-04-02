package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the occurrence database table.
 * 
 */
@Entity
@NamedQuery(name="Occurrence.findAll", query="SELECT o FROM Occurrence o")
public class Occurrence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String city;

	private String country;

	private String county;

	private String eventdate;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private double lat;

	private double lng;

	private String locality;

	private String location;

	private String province;

	private Object refjson;

	private String sourcesid;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	@Column(name="taxon_rank_id")
	private int taxonRankId;

	@Column(name="taxon_taxaset_id")
	private String taxonTaxasetId;

	private String taxonid;

	private String type;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	public Occurrence() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getEventdate() {
		return this.eventdate;
	}

	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
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

	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return this.lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getLocality() {
		return this.locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
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

	public int getTaxonRankId() {
		return this.taxonRankId;
	}

	public void setTaxonRankId(int taxonRankId) {
		this.taxonRankId = taxonRankId;
	}

	public String getTaxonTaxasetId() {
		return this.taxonTaxasetId;
	}

	public void setTaxonTaxasetId(String taxonTaxasetId) {
		this.taxonTaxasetId = taxonTaxasetId;
	}

	public String getTaxonid() {
		return this.taxonid;
	}

	public void setTaxonid(String taxonid) {
		this.taxonid = taxonid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

}