package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the specimendata database table.
 * 
 */
@Entity
@NamedQuery(name="Specimendata.findAll", query="SELECT s FROM Specimendata s")
public class Specimendata implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String city;

	private String collectdate;

	private String collector;

	private String country;

	private String county;

	private String idenby;

	private String idendate;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private double lat;

	private double lng;

	private String locality;

	private String location;

	private Object mediajson;

	private String province;

	private Object refjson;

	private String sex;

	private String sourcesid;

	private String specimenno;

	private String specimentype;

	private int state;

	private String storedin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String taxonid;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	public Specimendata() {
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

	public String getCollectdate() {
		return this.collectdate;
	}

	public void setCollectdate(String collectdate) {
		this.collectdate = collectdate;
	}

	public String getCollector() {
		return this.collector;
	}

	public void setCollector(String collector) {
		this.collector = collector;
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

	public String getIdenby() {
		return this.idenby;
	}

	public void setIdenby(String idenby) {
		this.idenby = idenby;
	}

	public String getIdendate() {
		return this.idendate;
	}

	public void setIdendate(String idendate) {
		this.idendate = idendate;
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

	public Object getMediajson() {
		return this.mediajson;
	}

	public void setMediajson(Object mediajson) {
		this.mediajson = mediajson;
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

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSourcesid() {
		return this.sourcesid;
	}

	public void setSourcesid(String sourcesid) {
		this.sourcesid = sourcesid;
	}

	public String getSpecimenno() {
		return this.specimenno;
	}

	public void setSpecimenno(String specimenno) {
		this.specimenno = specimenno;
	}

	public String getSpecimentype() {
		return this.specimentype;
	}

	public void setSpecimentype(String specimentype) {
		this.specimentype = specimentype;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStoredin() {
		return this.storedin;
	}

	public void setStoredin(String storedin) {
		this.storedin = storedin;
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

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

}