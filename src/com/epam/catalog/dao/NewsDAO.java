package com.epam.catalog.dao;

import com.epam.catalog.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by PC on 30.01.2017.
 */
public interface NewsDAO {

    void addProduct(String type, String name, String genre) throws DAOException;

    HashSet findByType(String type) throws DAOException;

    HashSet findByName(String name) throws DAOException;

    HashSet findByGenre(String genre) throws DAOException;

    HashSet getCatalog() throws DAOException;
}
