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
public class Specimendata {
    private String id;
    private String collector;
    private String collectdate;
    private String location;
    private Double lng;
    private Double lat;
    private String idenby;
    private String idendate;
    private String specimenno;
    private String sex;
    private String storedin;
    private String specimentype;
    private String mediajson;
    private String taxonid;
    private String desid;
    private String refjson;
    private String sourcesid;
    private Integer state;
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
    @Column(name = "collector")
    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    @Basic
    @Column(name = "collectdate")
    public String getCollectdate() {
        return collectdate;
    }

    public void setCollectdate(String collectdate) {
        this.collectdate = collectdate;
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
    @Column(name = "lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    @Basic
    @Column(name = "lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "idenby")
    public String getIdenby() {
        return idenby;
    }

    public void setIdenby(String idenby) {
        this.idenby = idenby;
    }

    @Basic
    @Column(name = "idendate")
    public String getIdendate() {
        return idendate;
    }

    public void setIdendate(String idendate) {
        this.idendate = idendate;
    }

    @Basic
    @Column(name = "specimenno")
    public String getSpecimenno() {
        return specimenno;
    }

    public void setSpecimenno(String specimenno) {
        this.specimenno = specimenno;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "storedin")
    public String getStoredin() {
        return storedin;
    }

    public void setStoredin(String storedin) {
        this.storedin = storedin;
    }

    @Basic
    @Column(name = "specimentype")
    public String getSpecimentype() {
        return specimentype;
    }

    public void setSpecimentype(String specimentype) {
        this.specimentype = specimentype;
    }

    @Basic
    @Column(name = "mediajson")
    public String getMediajson() {
        return mediajson;
    }

    public void setMediajson(String mediajson) {
        this.mediajson = mediajson;
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
    @Column(name = "sourcesid")
    public String getSourcesid() {
        return sourcesid;
    }

    public void setSourcesid(String sourcesid) {
        this.sourcesid = sourcesid;
    }

    @Basic
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

        Specimendata that = (Specimendata) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (collector != null ? !collector.equals(that.collector) : that.collector != null) return false;
        if (collectdate != null ? !collectdate.equals(that.collectdate) : that.collectdate != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (lng != null ? !lng.equals(that.lng) : that.lng != null) return false;
        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (idenby != null ? !idenby.equals(that.idenby) : that.idenby != null) return false;
        if (idendate != null ? !idendate.equals(that.idendate) : that.idendate != null) return false;
        if (specimenno != null ? !specimenno.equals(that.specimenno) : that.specimenno != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (storedin != null ? !storedin.equals(that.storedin) : that.storedin != null) return false;
        if (specimentype != null ? !specimentype.equals(that.specimentype) : that.specimentype != null) return false;
        if (mediajson != null ? !mediajson.equals(that.mediajson) : that.mediajson != null) return false;
        if (taxonid != null ? !taxonid.equals(that.taxonid) : that.taxonid != null) return false;
        if (desid != null ? !desid.equals(that.desid) : that.desid != null) return false;
        if (refjson != null ? !refjson.equals(that.refjson) : that.refjson != null) return false;
        if (sourcesid != null ? !sourcesid.equals(that.sourcesid) : that.sourcesid != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (inputer != null ? !inputer.equals(that.inputer) : that.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(that.inputtime) : that.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(that.synchstatus) : that.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(that.synchdate) : that.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (collector != null ? collector.hashCode() : 0);
        result = 31 * result + (collectdate != null ? collectdate.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (idenby != null ? idenby.hashCode() : 0);
        result = 31 * result + (idendate != null ? idendate.hashCode() : 0);
        result = 31 * result + (specimenno != null ? specimenno.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (storedin != null ? storedin.hashCode() : 0);
        result = 31 * result + (specimentype != null ? specimentype.hashCode() : 0);
        result = 31 * result + (mediajson != null ? mediajson.hashCode() : 0);
        result = 31 * result + (taxonid != null ? taxonid.hashCode() : 0);
        result = 31 * result + (desid != null ? desid.hashCode() : 0);
        result = 31 * result + (refjson != null ? refjson.hashCode() : 0);
        result = 31 * result + (sourcesid != null ? sourcesid.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
