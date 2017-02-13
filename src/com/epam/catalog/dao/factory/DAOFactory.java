package com.epam.catalog.dao.factory;

import com.epam.catalog.dao.DB_ConnectionDAO;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.impl.ConnectionDAO;
import com.epam.catalog.dao.impl.SQLCatalogDAO;


/**
 * Created by PC on 30.01.2017.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final NewsDAO filNewsDAO = new SQLCatalogDAO();
    private final DB_ConnectionDAO db_connectionDAO = new ConnectionDAO();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public NewsDAO getProductDAO() {

        return filNewsDAO;
    }

    public DB_ConnectionDAO getDb_connectionDAO() {

        return db_connectionDAO;

    }
}
