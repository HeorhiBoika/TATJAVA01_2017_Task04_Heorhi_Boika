package com.epam.catalog.bean;

import java.io.Serializable;

/**
 * Created by PC on 30.01.2017.
 */
public class Type implements Serializable{

    private String type;

    private static final long serialVersionUID = 1L;

    public Type(){

    }

    public Type(String type) {
        this.type = type;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type1 = (Type) o;

        return type != null ? type.equals(type1.type) : type1.type == null;
    }

    @Override
    public int hashCode() {
        return type != null ? type.hashCode() : 0;
    }
}
