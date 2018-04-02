package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the protectstandard database table.
 * 
 */
@Entity
@NamedQuery(name="Protectstandard.findAll", query="SELECT p FROM Protectstandard p")
public class Protectstandard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String releasedate;

	private String source;

	private String standardinfo;

	private String standardname;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	private String version;

	//bi-directional many-to-one association to Protection
	@OneToMany(mappedBy="protectstandard")
	private List<Protection> protections;

	public Protectstandard() {
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

	public String getReleasedate() {
		return this.releasedate;
	}

	public void setReleasedate(String releasedate) {
		this.releasedate = releasedate;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStandardinfo() {
		return this.standardinfo;
	}

	public void setStandardinfo(String standardinfo) {
		this.standardinfo = standardinfo;
	}

	public String getStandardname() {
		return this.standardname;
	}

	public void setStandardname(String standardname) {
		this.standardname = standardname;
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

	public List<Protection> getProtections() {
		return this.protections;
	}

	public void setProtections(List<Protection> protections) {
		this.protections = protections;
	}

	public Protection addProtection(Protection protection) {
		getProtections().add(protection);
		protection.setProtectstandard(this);

		return protection;
	}

	public Protection removeProtection(Protection protection) {
		getProtections().remove(protection);
		protection.setProtectstandard(null);

		return protection;
	}

}