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
public class Traitdata {
    private String id;
    private String taxonid;
    private String trainsetid;
    private String traitjson;
    private String desid;
    private String refjson;
    private String sourcesjson;
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
    @Column(name = "trainsetid")
    public String getTrainsetid() {
        return trainsetid;
    }

    public void setTrainsetid(String trainsetid) {
        this.trainsetid = trainsetid;
    }

    @Basic
    @Column(name = "traitjson")
    public String getTraitjson() {
        return traitjson;
    }

    public void setTraitjson(String traitjson) {
        this.traitjson = traitjson;
    }

    @Basic
    @Column(name = "desid")
    public String getDesid() {
        return desid;
    }

    public void setDesid(String desid) {
        this.desid = desid;
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
    @Column(name = "sourcesjson")
    public String getSourcesjson() {
        return sourcesjson;
    }

    public void setSourcesjson(String sourcesjson) {
        this.sourcesjson = sourcesjson;
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

        Traitdata traitdata = (Traitdata) o;

        if (id != null ? !id.equals(traitdata.id) : traitdata.id != null) return false;
        if (taxonid != null ? !taxonid.equals(traitdata.taxonid) : traitdata.taxonid != null) return false;
        if (trainsetid != null ? !trainsetid.equals(traitdata.trainsetid) : traitdata.trainsetid != null) return false;
        if (traitjson != null ? !traitjson.equals(traitdata.traitjson) : traitdata.traitjson != null) return false;
        if (desid != null ? !desid.equals(traitdata.desid) : traitdata.desid != null) return false;
        if (refjson != null ? !refjson.equals(traitdata.refjson) : traitdata.refjson != null) return false;
        if (sourcesjson != null ? !sourcesjson.equals(traitdata.sourcesjson) : traitdata.sourcesjson != null)
            return false;
        if (status != null ? !status.equals(traitdata.status) : traitdata.status != null) return false;
        if (inputer != null ? !inputer.equals(traitdata.inputer) : traitdata.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(traitdata.inputtime) : traitdata.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(traitdata.synchstatus) : traitdata.synchstatus != null)
            return false;
        if (synchdate != null ? !synchdate.equals(traitdata.synchdate) : traitdata.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taxonid != null ? taxonid.hashCode() : 0);
        result = 31 * result + (trainsetid != null ? trainsetid.hashCode() : 0);
        result = 31 * result + (traitjson != null ? traitjson.hashCode() : 0);
        result = 31 * result + (desid != null ? desid.hashCode() : 0);
        result = 31 * result + (refjson != null ? refjson.hashCode() : 0);
        result = 31 * result + (sourcesjson != null ? sourcesjson.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
