package org.big.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
public class Taxkey {
    private String id;
    private String keytitle;
    private String taxonid;
    private String desid;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "keytitle")
    public String getKeytitle() {
        return keytitle;
    }

    public void setKeytitle(String keytitle) {
        this.keytitle = keytitle;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taxkey taxkey = (Taxkey) o;

        if (id != null ? !id.equals(taxkey.id) : taxkey.id != null) return false;
        if (keytitle != null ? !keytitle.equals(taxkey.keytitle) : taxkey.keytitle != null) return false;
        if (taxonid != null ? !taxonid.equals(taxkey.taxonid) : taxkey.taxonid != null) return false;
        if (desid != null ? !desid.equals(taxkey.desid) : taxkey.desid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (keytitle != null ? keytitle.hashCode() : 0);
        result = 31 * result + (taxonid != null ? taxonid.hashCode() : 0);
        result = 31 * result + (desid != null ? desid.hashCode() : 0);
        return result;
    }
}
