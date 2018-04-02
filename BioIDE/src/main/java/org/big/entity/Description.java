package org.big.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the description database table.
 * 
 */
@Entity
@NamedQuery(name="Description.findAll", query="SELECT d FROM Description d")
public class Description implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Lob
	private String descontent;

	private String describer;

	private String desdate;

	private String destitle;

	private String destypeid;

	private String inputer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inputtime;

	private String language;

	private String licenseid;

	private Object refjson;

	private Object relation;

	private String remark;

	private String rightsholder;

	private String sourcesid;

	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date synchdate;

	private int synchstatus;

	//bi-directional many-to-one association to Descriptiontype
	@ManyToOne
	private Descriptiontype descriptiontype;

	//bi-directional many-to-one association to Taxon
	@ManyToOne
	private Taxon taxon;

	public Description() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescontent() {
		return this.descontent;
	}

	public void setDescontent(String descontent) {
		this.descontent = descontent;
	}

	public String getDescriber() {
		return this.describer;
	}

	public void setDescriber(String describer) {
		this.describer = describer;
	}

	public String getDesdate() {
		return this.desdate;
	}

	public void setDesdate(String desdate) {
		this.desdate = desdate;
	}

	public String getDestitle() {
		return this.destitle;
	}

	public void setDestitle(String destitle) {
		this.destitle = destitle;
	}

	public String getDestypeid() {
		return this.destypeid;
	}

	public void setDestypeid(String destypeid) {
		this.destypeid = destypeid;
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

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLicenseid() {
		return this.licenseid;
	}

	public void setLicenseid(String licenseid) {
		this.licenseid = licenseid;
	}

	public Object getRefjson() {
		return this.refjson;
	}

	public void setRefjson(Object refjson) {
		this.refjson = refjson;
	}

	public Object getRelation() {
		return this.relation;
	}

	public void setRelation(Object relation) {
		this.relation = relation;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRightsholder() {
		return this.rightsholder;
	}

	public void setRightsholder(String rightsholder) {
		this.rightsholder = rightsholder;
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

	public Descriptiontype getDescriptiontype() {
		return this.descriptiontype;
	}

	public void setDescriptiontype(Descriptiontype descriptiontype) {
		this.descriptiontype = descriptiontype;
	}

	public Taxon getTaxon() {
		return this.taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

}