package com.epam.catalog.dao.impl;

import com.epam.catalog.dao.DB_ConnectionDAO;
import com.epam.catalog.dao.connectionPool.ConnectionPool;
import com.epam.catalog.dao.exception.DAOException;


/**
 * Created by PC on 08.02.2017.
 */
public class ConnectionDAO implements DB_ConnectionDAO {
    @Override
    public void checkConnectionDB() throws DAOException {
        ConnectionPool connectionPool;
        try {
            connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
        } catch (DAOException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void closeAllConnections() throws DAOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.dispose();
    }
}
