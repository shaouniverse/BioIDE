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
public class Taxon {
    private String id;
    private String scientificname;
    private String authorstr;
    private String epithet;
    private Integer rankid;
    private String nomencode;
    private String remark;
    private String sourcesid;
    private String tci;
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
    @Column(name = "scientificname")
    public String getScientificname() {
        return scientificname;
    }

    public void setScientificname(String scientificname) {
        this.scientificname = scientificname;
    }

    @Basic
    @Column(name = "authorstr")
    public String getAuthorstr() {
        return authorstr;
    }

    public void setAuthorstr(String authorstr) {
        this.authorstr = authorstr;
    }

    @Basic
    @Column(name = "epithet")
    public String getEpithet() {
        return epithet;
    }

    public void setEpithet(String epithet) {
        this.epithet = epithet;
    }

    @Basic
    @Column(name = "rankid")
    public Integer getRankid() {
        return rankid;
    }

    public void setRankid(Integer rankid) {
        this.rankid = rankid;
    }

    @Basic
    @Column(name = "nomencode")
    public String getNomencode() {
        return nomencode;
    }

    public void setNomencode(String nomencode) {
        this.nomencode = nomencode;
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
    @Column(name = "sourcesid")
    public String getSourcesid() {
        return sourcesid;
    }

    public void setSourcesid(String sourcesid) {
        this.sourcesid = sourcesid;
    }

    @Basic
    @Column(name = "tci")
    public String getTci() {
        return tci;
    }

    public void setTci(String tci) {
        this.tci = tci;
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

        Taxon taxon = (Taxon) o;

        if (id != null ? !id.equals(taxon.id) : taxon.id != null) return false;
        if (scientificname != null ? !scientificname.equals(taxon.scientificname) : taxon.scientificname != null)
            return false;
        if (authorstr != null ? !authorstr.equals(taxon.authorstr) : taxon.authorstr != null) return false;
        if (epithet != null ? !epithet.equals(taxon.epithet) : taxon.epithet != null) return false;
        if (rankid != null ? !rankid.equals(taxon.rankid) : taxon.rankid != null) return false;
        if (nomencode != null ? !nomencode.equals(taxon.nomencode) : taxon.nomencode != null) return false;
        if (remark != null ? !remark.equals(taxon.remark) : taxon.remark != null) return false;
        if (sourcesid != null ? !sourcesid.equals(taxon.sourcesid) : taxon.sourcesid != null) return false;
        if (tci != null ? !tci.equals(taxon.tci) : taxon.tci != null) return false;
        if (status != null ? !status.equals(taxon.status) : taxon.status != null) return false;
        if (inputer != null ? !inputer.equals(taxon.inputer) : taxon.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(taxon.inputtime) : taxon.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(taxon.synchstatus) : taxon.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(taxon.synchdate) : taxon.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (scientificname != null ? scientificname.hashCode() : 0);
        result = 31 * result + (authorstr != null ? authorstr.hashCode() : 0);
        result = 31 * result + (epithet != null ? epithet.hashCode() : 0);
        result = 31 * result + (rankid != null ? rankid.hashCode() : 0);
        result = 31 * result + (nomencode != null ? nomencode.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (sourcesid != null ? sourcesid.hashCode() : 0);
        result = 31 * result + (tci != null ? tci.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
