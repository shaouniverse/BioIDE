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
public class Taxaset {
    private String id;
    private String tsname;
    private String tsinfo;
    private String refsjson;
    private String sourcejson;
    private String datasetid;
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
    @Column(name = "tsname")
    public String getTsname() {
        return tsname;
    }

    public void setTsname(String tsname) {
        this.tsname = tsname;
    }

    @Basic
    @Column(name = "tsinfo")
    public String getTsinfo() {
        return tsinfo;
    }

    public void setTsinfo(String tsinfo) {
        this.tsinfo = tsinfo;
    }

    @Basic
    @Column(name = "refsjson")
    public String getRefsjson() {
        return refsjson;
    }

    public void setRefsjson(String refsjson) {
        this.refsjson = refsjson;
    }

    @Basic
    @Column(name = "sourcejson")
    public String getSourcejson() {
        return sourcejson;
    }

    public void setSourcejson(String sourcejson) {
        this.sourcejson = sourcejson;
    }

    @Basic
    @Column(name = "datasetid")
    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
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

        Taxaset taxaset = (Taxaset) o;

        if (id != null ? !id.equals(taxaset.id) : taxaset.id != null) return false;
        if (tsname != null ? !tsname.equals(taxaset.tsname) : taxaset.tsname != null) return false;
        if (tsinfo != null ? !tsinfo.equals(taxaset.tsinfo) : taxaset.tsinfo != null) return false;
        if (refsjson != null ? !refsjson.equals(taxaset.refsjson) : taxaset.refsjson != null) return false;
        if (sourcejson != null ? !sourcejson.equals(taxaset.sourcejson) : taxaset.sourcejson != null) return false;
        if (datasetid != null ? !datasetid.equals(taxaset.datasetid) : taxaset.datasetid != null) return false;
        if (status != null ? !status.equals(taxaset.status) : taxaset.status != null) return false;
        if (synchstatus != null ? !synchstatus.equals(taxaset.synchstatus) : taxaset.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(taxaset.synchdate) : taxaset.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tsname != null ? tsname.hashCode() : 0);
        result = 31 * result + (tsinfo != null ? tsinfo.hashCode() : 0);
        result = 31 * result + (refsjson != null ? refsjson.hashCode() : 0);
        result = 31 * result + (sourcejson != null ? sourcejson.hashCode() : 0);
        result = 31 * result + (datasetid != null ? datasetid.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
