package org.big.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
public class Traitdescription {
    private int id;
    private Integer traitontologyid;
    private Integer propertyid;
    private String traitdescriptioncol;
    private String definition;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "traitontologyid")
    public Integer getTraitontologyid() {
        return traitontologyid;
    }

    public void setTraitontologyid(Integer traitontologyid) {
        this.traitontologyid = traitontologyid;
    }

    @Basic
    @Column(name = "propertyid")
    public Integer getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(Integer propertyid) {
        this.propertyid = propertyid;
    }

    @Basic
    @Column(name = "traitdescriptioncol")
    public String getTraitdescriptioncol() {
        return traitdescriptioncol;
    }

    public void setTraitdescriptioncol(String traitdescriptioncol) {
        this.traitdescriptioncol = traitdescriptioncol;
    }

    @Basic
    @Column(name = "definition")
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Traitdescription that = (Traitdescription) o;

        if (id != that.id) return false;
        if (traitontologyid != null ? !traitontologyid.equals(that.traitontologyid) : that.traitontologyid != null)
            return false;
        if (propertyid != null ? !propertyid.equals(that.propertyid) : that.propertyid != null) return false;
        if (traitdescriptioncol != null ? !traitdescriptioncol.equals(that.traitdescriptioncol) : that.traitdescriptioncol != null)
            return false;
        if (definition != null ? !definition.equals(that.definition) : that.definition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (traitontologyid != null ? traitontologyid.hashCode() : 0);
        result = 31 * result + (propertyid != null ? propertyid.hashCode() : 0);
        result = 31 * result + (traitdescriptioncol != null ? traitdescriptioncol.hashCode() : 0);
        result = 31 * result + (definition != null ? definition.hashCode() : 0);
        return result;
    }
}
