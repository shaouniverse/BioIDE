package org.big.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
public class Dataset {
    private String id;
    private String dsname;
    private String dsabstract;
    private String lisenceid;
    private Date createdDate;
    private String teamid;
    private String creator;
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
    @Column(name = "dsname")
    public String getDsname() {
        return dsname;
    }

    public void setDsname(String dsname) {
        this.dsname = dsname;
    }

    @Basic
    @Column(name = "dsabstract")
    public String getDsabstract() {
        return dsabstract;
    }

    public void setDsabstract(String dsabstract) {
        this.dsabstract = dsabstract;
    }

    @Basic
    @Column(name = "lisenceid")
    public String getLisenceid() {
        return lisenceid;
    }

    public void setLisenceid(String lisenceid) {
        this.lisenceid = lisenceid;
    }

    @Basic
    @Column(name = "createdDate")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "teamid")
    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    @Basic
    @Column(name = "creator")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

        Dataset dataset = (Dataset) o;

        if (id != null ? !id.equals(dataset.id) : dataset.id != null) return false;
        if (dsname != null ? !dsname.equals(dataset.dsname) : dataset.dsname != null) return false;
        if (dsabstract != null ? !dsabstract.equals(dataset.dsabstract) : dataset.dsabstract != null) return false;
        if (lisenceid != null ? !lisenceid.equals(dataset.lisenceid) : dataset.lisenceid != null) return false;
        if (createdDate != null ? !createdDate.equals(dataset.createdDate) : dataset.createdDate != null) return false;
        if (teamid != null ? !teamid.equals(dataset.teamid) : dataset.teamid != null) return false;
        if (creator != null ? !creator.equals(dataset.creator) : dataset.creator != null) return false;
        if (status != null ? !status.equals(dataset.status) : dataset.status != null) return false;
        if (synchstatus != null ? !synchstatus.equals(dataset.synchstatus) : dataset.synchstatus != null) return false;
        if (synchdate != null ? !synchdate.equals(dataset.synchdate) : dataset.synchdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dsname != null ? dsname.hashCode() : 0);
        result = 31 * result + (dsabstract != null ? dsabstract.hashCode() : 0);
        result = 31 * result + (lisenceid != null ? lisenceid.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (teamid != null ? teamid.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (synchstatus != null ? synchstatus.hashCode() : 0);
        result = 31 * result + (synchdate != null ? synchdate.hashCode() : 0);
        return result;
    }
}
