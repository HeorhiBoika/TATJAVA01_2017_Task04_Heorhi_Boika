package com.epam.catalog.bean;


import java.io.Serializable;

/**
 * Created by PC on 30.01.2017.
 */
public class Catalog implements Serializable {

    private String type;
    private String name;
    private String genre;

    private static final long serialVersionUID = 1L;

    public Catalog() {

    }

    public Catalog(String type, String name, String genre) {
        this.type = type;
        this.name = name;
        this.genre = genre;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;
    }

    public String getGenre() {

        return genre;
    }

    public void setGenre(String genre) {

        this.genre = genre;
    }

    @Override
    public String toString() {
        return "type = " + type +
                ", name = " + name +
                ", genre = " + genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;

        if (type != null ? !type.equals(catalog.type) : catalog.type != null) return false;
        if (name != null ? !name.equals(catalog.name) : catalog.name != null) return false;
        return genre != null ? genre.equals(catalog.genre) : catalog.genre == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
