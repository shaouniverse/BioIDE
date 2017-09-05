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
public class Traitontology {
    private int id;
    private String enterm;
    private String cnterm;
    private String definition;
    private String synonymys;
    private String sourcesjson;
    private Integer status;
    private String inputer;
    private Timestamp inputtime;
    private Integer synchstatus;
    private Timestamp synchdate;
    private String catalog1;
    private String catalog2;
    private String catalog3;
    private String group;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "enterm")
    public String getEnterm() {
        return enterm;
    }

    public void setEnterm(String enterm) {
        this.enterm = enterm;
    }

    @Basic
    @Column(name = "cnterm")
    public String getCnterm() {
        return cnterm;
    }

    public void setCnterm(String cnterm) {
        this.cnterm = cnterm;
    }

    @Basic
    @Column(name = "definition")
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Basic
    @Column(name = "synonymys")
    public String getSynonymys() {
        return synonymys;
    }

    public void setSynonymys(String synonymys) {
        this.synonymys = synonymys;
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

    @Basic
    @Column(name = "catalog1")
    public String getCatalog1() {
        return catalog1;
    }

    public void setCatalog1(String catalog1) {
        this.catalog1 = catalog1;
    }

    @Basic
    @Column(name = "catalog2")
    public String getCatalog2() {
        return catalog2;
    }

    public void setCatalog2(String catalog2) {
        this.catalog2 = catalog2;
    }

    @Basic
    @Column(name = "catalog3")
    public String getCatalog3() {
        return catalog3;
    }

    public void setCatalog3(String catalog3) {
        this.catalog3 = catalog3;
    }

    @Basic
    @Column(name = "group")
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Traitontology that = (Traitontology) o;

        if (id != that.id) return false;
        if (enterm != null ? !enterm.equals(that.enterm) : that.enterm != null) return false;
        if (cnterm != null ? !cnterm.equals(that.cnterm) : that.cnterm != null) return false;
        if (definition != null ? !definition.equals(that.definition) : that.definition != null) return false;
        if (synonymys != null ? !synonymys.equals(that.synonymys) : that.synonymys != null) return false;
        if (sourcesjson != null ? !sourcesjson.equals(that.sourcesjson) : that.sourcesjson != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (inputer != null ? !inputer.equals(that.inputer) : that.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(that.inputtime) : that.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(that.synchstatus) : that.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(that.synchdate) : that.synchdate != null) return false;
        if (catalog1 != null ? !catalog1.equals(that.catalog1) : that.catalog1 != null) return false;
        if (catalog2 != null ? !catalog2.equals(that.catalog2) : that.catalog2 != null) return false;
        if (catalog3 != null ? !catalog3.equals(that.catalog3) : that.catalog3 != null) return false;
        if (group != null ? !group.equals(that.group) : that.group != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (enterm != null ? enterm.hashCode() : 0);
        result = 31 * result + (cnterm != null ? cnterm.hashCode() : 0);
        result = 31 * result + (definition != null ? definition.hashCode() : 0);
        result = 31 * result + (synonymys != null ? synonymys.hashCode() : 0);
        result = 31 * result + (sourcesjson != null ? sourcesjson.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        result = 31 * result + (catalog1 != null ? catalog1.hashCode() : 0);
        result = 31 * result + (catalog2 != null ? catalog2.hashCode() : 0);
        result = 31 * result + (catalog3 != null ? catalog3.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
}
