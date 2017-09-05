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
public class Geoobject {
    private String id;
    private String cngeoname;
    private String engeoname;
    private String geodata;
    private String pid;
    private String geotype;
    private String version;
    private String relation;
    private Double centerx;
    private Double centery;
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
    @Column(name = "cngeoname")
    public String getCngeoname() {
        return cngeoname;
    }

    public void setCngeoname(String cngeoname) {
        this.cngeoname = cngeoname;
    }

    @Basic
    @Column(name = "engeoname")
    public String getEngeoname() {
        return engeoname;
    }

    public void setEngeoname(String engeoname) {
        this.engeoname = engeoname;
    }

    @Basic
    @Column(name = "geodata")
    public String getGeodata() {
        return geodata;
    }

    public void setGeodata(String geodata) {
        this.geodata = geodata;
    }

    @Basic
    @Column(name = "pid")
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "geotype")
    public String getGeotype() {
        return geotype;
    }

    public void setGeotype(String geotype) {
        this.geotype = geotype;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "relation")
    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Basic
    @Column(name = "centerx")
    public Double getCenterx() {
        return centerx;
    }

    public void setCenterx(Double centerx) {
        this.centerx = centerx;
    }

    @Basic
    @Column(name = "centery")
    public Double getCentery() {
        return centery;
    }

    public void setCentery(Double centery) {
        this.centery = centery;
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

        Geoobject geoobject = (Geoobject) o;

        if (id != null ? !id.equals(geoobject.id) : geoobject.id != null) return false;
        if (cngeoname != null ? !cngeoname.equals(geoobject.cngeoname) : geoobject.cngeoname != null) return false;
        if (engeoname != null ? !engeoname.equals(geoobject.engeoname) : geoobject.engeoname != null) return false;
        if (geodata != null ? !geodata.equals(geoobject.geodata) : geoobject.geodata != null) return false;
        if (pid != null ? !pid.equals(geoobject.pid) : geoobject.pid != null) return false;
        if (geotype != null ? !geotype.equals(geoobject.geotype) : geoobject.geotype != null) return false;
        if (version != null ? !version.equals(geoobject.version) : geoobject.version != null) return false;
        if (relation != null ? !relation.equals(geoobject.relation) : geoobject.relation != null) return false;
        if (centerx != null ? !centerx.equals(geoobject.centerx) : geoobject.centerx != null) return false;
        if (centery != null ? !centery.equals(geoobject.centery) : geoobject.centery != null) return false;
        if (status != null ? !status.equals(geoobject.status) : geoobject.status != null) return false;
        if (inputer != null ? !inputer.equals(geoobject.inputer) : geoobject.inputer != null) return false;
        if (inputtime != null ? !inputtime.equals(geoobject.inputtime) : geoobject.inputtime != null) return false;
        if (synchstatus != null ? !synchstatus.equals(geoobject.synchstatus) : geoobject.synchstatus != null)
            return false;
        if (synchdate != null ? !synchdate.equals(geoobject.synchdate) : geoobject.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cngeoname != null ? cngeoname.hashCode() : 0);
        result = 31 * result + (engeoname != null ? engeoname.hashCode() : 0);
        result = 31 * result + (geodata != null ? geodata.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (geotype != null ? geotype.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        result = 31 * result + (centerx != null ? centerx.hashCode() : 0);
        result = 31 * result + (centery != null ? centery.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (inputer != null ? inputer.hashCode() : 0);
        result = 31 * result + (inputtime != null ? inputtime.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
