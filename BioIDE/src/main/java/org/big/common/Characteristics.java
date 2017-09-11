package org.big.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by WangTianshan on 2017/9/8.
 */
public class Characteristics implements Serializable {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field= field;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Characteristics other = (Characteristics) obj;
        return Objects.equals(this.field, other.field);

    }

    @Override
    public int hashCode() {

        return Objects.hash(this.field);

    }
}