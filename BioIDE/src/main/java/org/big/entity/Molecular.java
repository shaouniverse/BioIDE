package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the molecular database table.
 * 
 */
@Entity
@NamedQuery(name="Molecular.findAll", query="SELECT m FROM Molecular m")
public class Molecular implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String authorinstitution;

	private String authorname;

	private String city;

	private String country;

	private String county;

	private String fastaurl;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private double lat;

	private double lng;

	private String locality;

	private String location;

	private String ncbiid;

	private Object otherinfo;

	private String province;

	private Object refjson;

	@Lob
	private String sequence;

	private Object sourcesjson;

	@Column(name="spe_id")
	private String speId;

	@Column(name="spe_location")
	private String speLocation;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String title;

	private String type;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	public Molecular() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorinstitution() {
		return this.authorinstitution;
	}

	public void setAuthorinstitution(String authorinstitution) {
		this.authorinstitution = authorinstitution;
	}

	public String getAuthorname() {
		return this.authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
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

	public String getFastaurl() {
		return this.fastaurl;
	}

	public void setFastaurl(String fastaurl) {
		this.fastaurl = fastaurl;
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

	public String getNcbiid() {
		return this.ncbiid;
	}

	public void setNcbiid(String ncbiid) {
		this.ncbiid = ncbiid;
	}

	public Object getOtherinfo() {
		return this.otherinfo;
	}

	public void setOtherinfo(Object otherinfo) {
		this.otherinfo = otherinfo;
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

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Object getSourcesjson() {
		return this.sourcesjson;
	}

	public void setSourcesjson(Object sourcesjson) {
		this.sourcesjson = sourcesjson;
	}

	public String getSpeId() {
		return this.speId;
	}

	public void setSpeId(String speId) {
		this.speId = speId;
	}

	public String getSpeLocation() {
		return this.speLocation;
	}

	public void setSpeLocation(String speLocation) {
		this.speLocation = speLocation;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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