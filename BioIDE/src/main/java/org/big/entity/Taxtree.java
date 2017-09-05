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
public class Taxtree {
    private String id;
    private String treename;
    private String treeinfo;
    private String refsjson;
    private String sourcejson;
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
    @Column(name = "treename")
    public String getTreename() {
        return treename;
    }

    public void setTreename(String treename) {
        this.treename = treename;
    }

    @Basic
    @Column(name = "treeinfo")
    public String getTreeinfo() {
        return treeinfo;
    }

    public void setTreeinfo(String treeinfo) {
        this.treeinfo = treeinfo;
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

        Taxtree taxtree = (Taxtree) o;

        if (id != null ? !id.equals(taxtree.id) : taxtree.id != null) return false;
        if (treename != null ? !treename.equals(taxtree.treename) : taxtree.treename != null) return false;
        if (treeinfo != null ? !treeinfo.equals(taxtree.treeinfo) : taxtree.treeinfo != null) return false;
        if (refsjson != null ? !refsjson.equals(taxtree.refsjson) : taxtree.refsjson != null) return false;
        if (sourcejson != null ? !sourcejson.equals(taxtree.sourcejson) : taxtree.sourcejson != null) return false;
        if (status != null ? !status.equals(taxtree.status) : taxtree.status != null) return false;
        if (inputer != null ? !inputer.equals(taxtree.inputer) : taxtree.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(taxtree.inputtime) : taxtree.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(taxtree.synchstatus) : taxtree.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(taxtree.synchdate) : taxtree.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (treename != null ? treename.hashCode() : 0);
        result = 31 * result + (treeinfo != null ? treeinfo.hashCode() : 0);
        result = 31 * result + (refsjson != null ? refsjson.hashCode() : 0);
        result = 31 * result + (sourcejson != null ? sourcejson.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
