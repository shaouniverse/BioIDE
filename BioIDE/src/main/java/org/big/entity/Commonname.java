package org.big.entity;

import com.alibaba.fastjson.JSON;
import org.big.common.Characteristics;
import org.hibernate.annotations.Type;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by WangTianshan on 2017/9/7.
 */
@Entity
public class Commonname {
    private String id;
    private String taxonid;
    private String commonname;
    private String language;
//    @Column
//    @Type(type = "org.big.common.MyJsonType")
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
    @Column(name = "taxonid")
    public String getTaxonid() {
        return taxonid;
    }

    public void setTaxonid(String taxonid) {
        this.taxonid = taxonid;
    }

    @Basic
    @Column(name = "commonname")
    public String getCommonname() {
        return commonname;
    }

    public void setCommonname(String commonname) {
        this.commonname = commonname;
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Commoname commoname = (Commoname) o;
//
//        if (id != null ? !id.equals(commoname.id) : commoname.id != null) return false;
//        if (taxonid != null ? !taxonid.equals(commoname.taxonid) : commoname.taxonid != null) return false;
//        if (commonname != null ? !commonname.equals(commoname.commonname) : commoname.commonname != null) return false;
//        if (language != null ? !language.equals(commoname.language) : commoname.language != null) return false;
//        if (refjson != null ? !refjson.equals(commoname.refjson) : commoname.refjson != null) return false;
//        if (sourcesid != null ? !sourcesid.equals(commoname.sourcesid) : commoname.sourcesid != null) return false;
//        if (status != null ? !status.equals(commoname.status) : commoname.status != null) return false;
//        if (inputer != null ? !inputer.equals(commoname.inputer) : commoname.inputer != null) return false;
//        if (inputtime != null ? !inputtime.equals(commoname.inputtime) : commoname.inputtime != null) return false;
//        if (synchstatus != null ? !synchstatus.equals(commoname.synchstatus) : commoname.synchstatus != null)
//            return false;
//        if (synchdate != null ? !synchdate.equals(commoname.synchdate) : commoname.synchdate != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (taxonid != null ? taxonid.hashCode() : 0);
//        result = 31 * result + (commonname != null ? commonname.hashCode() : 0);
//        result = 31 * result + (language != null ? language.hashCode() : 0);
//        result = 31 * result + (refjson != null ? refjson.hashCode() : 0);
//        result = 31 * result + (sourcesid != null ? sourcesid.hashCode() : 0);
//        result = 31 * result + (status != null ? status.hashCode() : 0);
//        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
//        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
//        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
//        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
//        return result;
//    }
}
