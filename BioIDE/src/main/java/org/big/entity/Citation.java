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
public class Citation {
    private String id;
    private String sciname;
    private String authorship;
    private Integer nametype;
    private String citationstr;
    private String shortrefs;
    private String refjson;
    private String sourcesid;
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
    @Column(name = "sciname")
    public String getSciname() {
        return sciname;
    }

    public void setSciname(String sciname) {
        this.sciname = sciname;
    }

    @Basic
    @Column(name = "authorship")
    public String getAuthorship() {
        return authorship;
    }

    public void setAuthorship(String authorship) {
        this.authorship = authorship;
    }

    @Basic
    @Column(name = "nametype")
    public Integer getNametype() {
        return nametype;
    }

    public void setNametype(Integer nametype) {
        this.nametype = nametype;
    }

    @Basic
    @Column(name = "citationstr")
    public String getCitationstr() {
        return citationstr;
    }

    public void setCitationstr(String citationstr) {
        this.citationstr = citationstr;
    }

    @Basic
    @Column(name = "shortrefs")
    public String getShortrefs() {
        return shortrefs;
    }

    public void setShortrefs(String shortrefs) {
        this.shortrefs = shortrefs;
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
    @Column(name = "sourcesid")
    public String getSourcesid() {
        return sourcesid;
    }

    public void setSourcesid(String sourcesid) {
        this.sourcesid = sourcesid;
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

        Citation citation = (Citation) o;

        if (id != null ? !id.equals(citation.id) : citation.id != null) return false;
        if (sciname != null ? !sciname.equals(citation.sciname) : citation.sciname != null) return false;
        if (authorship != null ? !authorship.equals(citation.authorship) : citation.authorship != null) return false;
        if (nametype != null ? !nametype.equals(citation.nametype) : citation.nametype != null) return false;
        if (citationstr != null ? !citationstr.equals(citation.citationstr) : citation.citationstr != null)
            return false;
        if (shortrefs != null ? !shortrefs.equals(citation.shortrefs) : citation.shortrefs != null) return false;
        if (refjson != null ? !refjson.equals(citation.refjson) : citation.refjson != null) return false;
        if (sourcesid != null ? !sourcesid.equals(citation.sourcesid) : citation.sourcesid != null) return false;
        if (status != null ? !status.equals(citation.status) : citation.status != null) return false;
        if (inputer != null ? !inputer.equals(citation.inputer) : citation.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(citation.inputtime) : citation.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(citation.synchstatus) : citation.synchstatus != null)
            return false;
        if (synchdate != null ? !synchdate.equals(citation.synchdate) : citation.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sciname != null ? sciname.hashCode() : 0);
        result = 31 * result + (authorship != null ? authorship.hashCode() : 0);
        result = 31 * result + (nametype != null ? nametype.hashCode() : 0);
        result = 31 * result + (citationstr != null ? citationstr.hashCode() : 0);
        result = 31 * result + (shortrefs != null ? shortrefs.hashCode() : 0);
        result = 31 * result + (refjson != null ? refjson.hashCode() : 0);
        result = 31 * result + (sourcesid != null ? sourcesid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
