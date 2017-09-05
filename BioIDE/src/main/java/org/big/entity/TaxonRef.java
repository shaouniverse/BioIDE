package org.big.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
@Table(name = "taxon_ref", schema = "biodata", catalog = "")
public class TaxonRef {
    private String taxonid;
    private String refid;
    private String page;
    private Integer status;
    private String inputer;
    private Timestamp inputtime;
    private Integer synchstatus;
    private Timestamp synchdate;

    @Basic
    @Column(name = "taxonid")
    public String getTaxonid() {
        return taxonid;
    }

    public void setTaxonid(String taxonid) {
        this.taxonid = taxonid;
    }

    @Basic
    @Column(name = "refid")
    public String getRefid() {
        return refid;
    }

    public void setRefid(String refid) {
        this.refid = refid;
    }

    @Basic
    @Column(name = "page")
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

        TaxonRef taxonRef = (TaxonRef) o;

        if (taxonid != null ? !taxonid.equals(taxonRef.taxonid) : taxonRef.taxonid != null) return false;
        if (refid != null ? !refid.equals(taxonRef.refid) : taxonRef.refid != null) return false;
        if (page != null ? !page.equals(taxonRef.page) : taxonRef.page != null) return false;
        if (status != null ? !status.equals(taxonRef.status) : taxonRef.status != null) return false;
        if (inputer != null ? !inputer.equals(taxonRef.inputer) : taxonRef.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(taxonRef.inputtime) : taxonRef.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(taxonRef.synchstatus) : taxonRef.synchstatus != null)
            return false;
        if (synchdate != null ? !synchdate.equals(taxonRef.synchdate) : taxonRef.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taxonid != null ? taxonid.hashCode() : 0;
        result = 31 * result + (refid != null ? refid.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
