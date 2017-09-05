package org.big.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
@Table(name = "taxon_taxaset", schema = "biodata", catalog = "")
public class TaxonTaxaset {
    private String id;
    private String taxonid;
    private String taxasetid;
    private Integer status;
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
    @Column(name = "taxasetid")
    public String getTaxasetid() {
        return taxasetid;
    }

    public void setTaxasetid(String taxasetid) {
        this.taxasetid = taxasetid;
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

        TaxonTaxaset that = (TaxonTaxaset) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (taxonid != null ? !taxonid.equals(that.taxonid) : that.taxonid != null) return false;
        if (taxasetid != null ? !taxasetid.equals(that.taxasetid) : that.taxasetid != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (synchstatus != null ? !synchstatus.equals(that.synchstatus) : that.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(that.synchdate) : that.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taxonid != null ? taxonid.hashCode() : 0);
        result = 31 * result + (taxasetid != null ? taxasetid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
