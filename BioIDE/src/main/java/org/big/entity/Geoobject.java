package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the geoobject database table.
 * 
 */
@Entity
@NamedQuery(name="Geoobject.findAll", query="SELECT g FROM Geoobject g")
public class Geoobject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private double centerx;

	private double centery;

	private String cngeoname;

	private String engeoname;

	private Object geodata;

	private String geotype;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String pid;

	private Object relation;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String version;

	//bi-directional many-to-one association to Distributiondata
	@OneToMany(mappedBy="geoobject")
	private List<Distributiondata> distributiondata;

	//bi-directional many-to-one association to Geogroup
	@ManyToOne
	private Geogroup geogroup;

	public Geoobject() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getCenterx() {
		return this.centerx;
	}

	public void setCenterx(double centerx) {
		this.centerx = centerx;
	}

	public double getCentery() {
		return this.centery;
	}

	public void setCentery(double centery) {
		this.centery = centery;
	}

	public String getCngeoname() {
		return this.cngeoname;
	}

	public void setCngeoname(String cngeoname) {
		this.cngeoname = cngeoname;
	}

	public String getEngeoname() {
		return this.engeoname;
	}

	public void setEngeoname(String engeoname) {
		this.engeoname = engeoname;
	}

	public Object getGeodata() {
		return this.geodata;
	}

	public void setGeodata(Object geodata) {
		this.geodata = geodata;
	}

	public String getGeotype() {
		return this.geotype;
	}

	public void setGeotype(String geotype) {
		this.geotype = geotype;
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

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Object getRelation() {
		return this.relation;
	}

	public void setRelation(Object relation) {
		this.relation = relation;
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

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public List<Distributiondata> getDistributiondata() {
		return this.distributiondata;
	}

	public void setDistributiondata(List<Distributiondata> distributiondata) {
		this.distributiondata = distributiondata;
	}

	public Distributiondata addDistributiondata(Distributiondata distributiondata) {
		getDistributiondata().add(distributiondata);
		distributiondata.setGeoobject(this);

		return distributiondata;
	}

	public Distributiondata removeDistributiondata(Distributiondata distributiondata) {
		getDistributiondata().remove(distributiondata);
		distributiondata.setGeoobject(null);

		return distributiondata;
	}

	public Geogroup getGeogroup() {
		return this.geogroup;
	}

	public void setGeogroup(Geogroup geogroup) {
		this.geogroup = geogroup;
	}

}