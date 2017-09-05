package org.big.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by WangTianshan on 2017/9/5.
 */
@Entity
public class Rank {
    private int id;
    private String chname;
    private String enname;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "chname")
    public String getChname() {
        return chname;
    }

    public void setChname(String chname) {
        this.chname = chname;
    }

    @Basic
    @Column(name = "enname")
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rank rank = (Rank) o;

        if (id != rank.id) return false;
        if (chname != null ? !chname.equals(rank.chname) : rank.chname != null) return false;
        if (enname != null ? !enname.equals(rank.enname) : rank.enname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (chname != null ? chname.hashCode() : 0);
        result = 31 * result + (enname != null ? enname.hashCode() : 0);
        return result;
    }
}
