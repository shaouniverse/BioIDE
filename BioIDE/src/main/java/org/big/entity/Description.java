package org.big.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
public class Description {
    private String id;
    private String describer;
    private String desdate;
    private String destitle;
    private String descontent;
    private String destypeid;
    private String rightsholder;
    private String licenseid;
    private String remark;
    private String taxonid;
    private String language;
    private String relation;
    private String sourcesid;
    private String refjson;
    private Integer status;
    private String inputer;
    private Timestamp inputtime;
    private Integer synchstatus;
    private Timestamp synchdate;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "describer")
    public String getDescriber() {
        return describer;
    }

    public void setDescriber(String describer) {
        this.describer = describer;
    }

    @Basic
    @Column(name = "desdate")
    public String getDesdate() {
        return desdate;
    }

    public void setDesdate(String desdate) {
        this.desdate = desdate;
    }

    @Basic
    @Column(name = "destitle")
    public String getDestitle() {
        return destitle;
    }

    public void setDestitle(String destitle) {
        this.destitle = destitle;
    }

    @Basic
    @Column(name = "descontent")
    public String getDescontent() {
        return descontent;
    }

    public void setDescontent(String descontent) {
        this.descontent = descontent;
    }

    @Basic
    @Column(name = "destypeid")
    public String getDestypeid() {
        return destypeid;
    }

    public void setDestypeid(String destypeid) {
        this.destypeid = destypeid;
    }

    @Basic
    @Column(name = "rightsholder")
    public String getRightsholder() {
        return rightsholder;
    }

    public void setRightsholder(String rightsholder) {
        this.rightsholder = rightsholder;
    }

    @Basic
    @Column(name = "licenseid")
    public String getLicenseid() {
        return licenseid;
    }

    public void setLicenseid(String licenseid) {
        this.licenseid = licenseid;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "taxonid")
    public String getTaxonid() {
        return taxonid;
    }

    public void setTaxonid(String taxonid) {
        this.taxonid = taxonid;
    }

    @Basic
    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "relation")
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Basic
    @Column(name = "sourcesid")
    public String getSourcesid() {
        return sourcesid;
    }

    public void setSourcesid(String sourcesid) {
        this.sourcesid = sourcesid;
    }

    @Basic
    @Column(name = "refjson")
    public String getRefjson() {
        return refjson;
    }

    public void setRefjson(String refjson) {
        this.refjson = refjson;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "inputer")
    public String getInputer() {
        return inputer;
    }

    public void setInputer(String inputer) {
        this.inputer = inputer;
    }

    @Basic
    @Column(name = "inputtime")
    public Timestamp getInputtime() {
        return inputtime;
    }

    public void setInputtime(Timestamp inputtime) {
        this.inputtime = inputtime;
    }

    @Basic
    @Column(name = "synchstatus")
    public Integer getSynchstatus() {
        return synchstatus;
    }

    public void setSynchstatus(Integer synchstatus) {
        this.synchstatus = synchstatus;
    }

    @Basic
    @Column(name = "synchdate")
    public Timestamp getSynchdate() {
        return synchdate;
    }

    public void setSynchdate(Timestamp synchdate) {
        this.synchdate = synchdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Description that = (Description) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (describer != null ? !describer.equals(that.describer) : that.describer != null) return false;
        if (desdate != null ? !desdate.equals(that.desdate) : that.desdate != null) return false;
        if (destitle != null ? !destitle.equals(that.destitle) : that.destitle != null) return false;
        if (descontent != null ? !descontent.equals(that.descontent) : that.descontent != null) return false;
        if (destypeid != null ? !destypeid.equals(that.destypeid) : that.destypeid != null) return false;
        if (rightsholder != null ? !rightsholder.equals(that.rightsholder) : that.rightsholder != null) return false;
        if (licenseid != null ? !licenseid.equals(that.licenseid) : that.licenseid != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (taxonid != null ? !taxonid.equals(that.taxonid) : that.taxonid != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (relation != null ? !relation.equals(that.relation) : that.relation != null) return false;
        if (sourcesid != null ? !sourcesid.equals(that.sourcesid) : that.sourcesid != null) return false;
        if (refjson != null ? !refjson.equals(that.refjson) : that.refjson != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (inputer != null ? !inputer.equals(that.inputer) : that.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(that.inputtime) : that.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(that.synchstatus) : that.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(that.synchdate) : that.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (describer != null ? describer.hashCode() : 0);
        result = 31 * result + (desdate != null ? desdate.hashCode() : 0);
        result = 31 * result + (destitle != null ? destitle.hashCode() : 0);
        result = 31 * result + (descontent != null ? descontent.hashCode() : 0);
        result = 31 * result + (destypeid != null ? destypeid.hashCode() : 0);
        result = 31 * result + (rightsholder != null ? rightsholder.hashCode() : 0);
        result = 31 * result + (licenseid != null ? licenseid.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (taxonid != null ? taxonid.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        result = 31 * result + (sourcesid != null ? sourcesid.hashCode() : 0);
        result = 31 * result + (refjson != null ? refjson.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
