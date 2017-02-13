package com.epam.catalog.dao;

import com.epam.catalog.dao.exception.DAOException;


/**
 * Created by PC on 08.02.2017.
 */
public interface DB_ConnectionDAO {
    void checkConnectionDB() throws DAOException;

    void closeAllConnections() throws DAOException;
}
