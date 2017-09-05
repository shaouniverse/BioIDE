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
public class Molecular {
    private String id;
    private String taxonid;
    private String authorname;
    private String authorinstitution;
    private String fastaurl;
    private String title;
    private String sequence;
    private String type;
    private String location;
    private String otherinfo;
    private String sourcesjson;
    private String ncbiid;
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
    @Column(name = "taxonid")
    public String getTaxonid() {
        return taxonid;
    }

    public void setTaxonid(String taxonid) {
        this.taxonid = taxonid;
    }

    @Basic
    @Column(name = "authorname")
    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    @Basic
    @Column(name = "authorinstitution")
    public String getAuthorinstitution() {
        return authorinstitution;
    }

    public void setAuthorinstitution(String authorinstitution) {
        this.authorinstitution = authorinstitution;
    }

    @Basic
    @Column(name = "fastaurl")
    public String getFastaurl() {
        return fastaurl;
    }

    public void setFastaurl(String fastaurl) {
        this.fastaurl = fastaurl;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "sequence")
    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "otherinfo")
    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    @Basic
    @Column(name = "sourcesjson")
    public String getSourcesjson() {
        return sourcesjson;
    }

    public void setSourcesjson(String sourcesjson) {
        this.sourcesjson = sourcesjson;
    }

    @Basic
    @Column(name = "ncbiid")
    public String getNcbiid() {
        return ncbiid;
    }

    public void setNcbiid(String ncbiid) {
        this.ncbiid = ncbiid;
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

        Molecular molecular = (Molecular) o;

        if (id != null ? !id.equals(molecular.id) : molecular.id != null) return false;
        if (taxonid != null ? !taxonid.equals(molecular.taxonid) : molecular.taxonid != null) return false;
        if (authorname != null ? !authorname.equals(molecular.authorname) : molecular.authorname != null) return false;
        if (authorinstitution != null ? !authorinstitution.equals(molecular.authorinstitution) : molecular.authorinstitution != null)
            return false;
        if (fastaurl != null ? !fastaurl.equals(molecular.fastaurl) : molecular.fastaurl != null) return false;
        if (title != null ? !title.equals(molecular.title) : molecular.title != null) return false;
        if (sequence != null ? !sequence.equals(molecular.sequence) : molecular.sequence != null) return false;
        if (type != null ? !type.equals(molecular.type) : molecular.type != null) return false;
        if (location != null ? !location.equals(molecular.location) : molecular.location != null) return false;
        if (otherinfo != null ? !otherinfo.equals(molecular.otherinfo) : molecular.otherinfo != null) return false;
        if (sourcesjson != null ? !sourcesjson.equals(molecular.sourcesjson) : molecular.sourcesjson != null)
            return false;
        if (ncbiid != null ? !ncbiid.equals(molecular.ncbiid) : molecular.ncbiid != null) return false;
        if (refjson != null ? !refjson.equals(molecular.refjson) : molecular.refjson != null) return false;
        if (status != null ? !status.equals(molecular.status) : molecular.status != null) return false;
        if (inputer != null ? !inputer.equals(molecular.inputer) : molecular.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(molecular.inputtime) : molecular.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(molecular.synchstatus) : molecular.synchstatus != null)
            return false;
        if (synchdate != null ? !synchdate.equals(molecular.synchdate) : molecular.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taxonid != null ? taxonid.hashCode() : 0);
        result = 31 * result + (authorname != null ? authorname.hashCode() : 0);
        result = 31 * result + (authorinstitution != null ? authorinstitution.hashCode() : 0);
        result = 31 * result + (fastaurl != null ? fastaurl.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (sequence != null ? sequence.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (otherinfo != null ? otherinfo.hashCode() : 0);
        result = 31 * result + (sourcesjson != null ? sourcesjson.hashCode() : 0);
        result = 31 * result + (ncbiid != null ? ncbiid.hashCode() : 0);
        result = 31 * result + (refjson != null ? refjson.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
