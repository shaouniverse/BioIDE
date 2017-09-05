package org.big.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
public class Keyitem {
    private String id;
    private String item;
    private Integer innerorder;
    private Integer orderid;
    private Integer branchid;
    private String taxonid;
    private String taxkeyid;
    private String pid;
    private String featureimgjson;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "item")
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Basic
    @Column(name = "innerorder")
    public Integer getInnerorder() {
        return innerorder;
    }

    public void setInnerorder(Integer innerorder) {
        this.innerorder = innerorder;
    }

    @Basic
    @Column(name = "orderid")
    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "branchid")
    public Integer getBranchid() {
        return branchid;
    }

    public void setBranchid(Integer branchid) {
        this.branchid = branchid;
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
    @Column(name = "taxkeyid")
    public String getTaxkeyid() {
        return taxkeyid;
    }

    public void setTaxkeyid(String taxkeyid) {
        this.taxkeyid = taxkeyid;
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
    @Column(name = "featureimgjson")
    public String getFeatureimgjson() {
        return featureimgjson;
    }

    public void setFeatureimgjson(String featureimgjson) {
        this.featureimgjson = featureimgjson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyitem keyitem = (Keyitem) o;

        if (id != null ? !id.equals(keyitem.id) : keyitem.id != null) return false;
        if (item != null ? !item.equals(keyitem.item) : keyitem.item != null) return false;
        if (innerorder != null ? !innerorder.equals(keyitem.innerorder) : keyitem.innerorder != null) return false;
        if (orderid != null ? !orderid.equals(keyitem.orderid) : keyitem.orderid != null) return false;
        if (branchid != null ? !branchid.equals(keyitem.branchid) : keyitem.branchid != null) return false;
        if (taxonid != null ? !taxonid.equals(keyitem.taxonid) : keyitem.taxonid != null) return false;
        if (taxkeyid != null ? !taxkeyid.equals(keyitem.taxkeyid) : keyitem.taxkeyid != null) return false;
        if (pid != null ? !pid.equals(keyitem.pid) : keyitem.pid != null) return false;
        if (featureimgjson != null ? !featureimgjson.equals(keyitem.featureimgjson) : keyitem.featureimgjson != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (innerorder != null ? innerorder.hashCode() : 0);
        result = 31 * result + (orderid != null ? orderid.hashCode() : 0);
        result = 31 * result + (branchid != null ? branchid.hashCode() : 0);
        result = 31 * result + (taxonid != null ? taxonid.hashCode() : 0);
        result = 31 * result + (taxkeyid != null ? taxkeyid.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (featureimgjson != null ? featureimgjson.hashCode() : 0);
        return result;
    }
}
