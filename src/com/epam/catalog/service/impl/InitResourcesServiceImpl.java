package com.epam.catalog.service.impl;

import com.epam.catalog.dao.DB_ConnectionDAO;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.InitResourcesService;
import com.epam.catalog.service.exception.ServiceException;

/**
 * Created by PC on 16.02.2017.
 */
public class InitResourcesServiceImpl implements InitResourcesService {

    public InitResourcesServiceImpl() {

    }

    @Override
    public void init_Resources() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DB_ConnectionDAO db_connectionDAO = daoFactory.getDb_connectionDAO();
            db_connectionDAO.checkConnectionDB();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void close_Resources() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DB_ConnectionDAO db_connectionDAO = daoFactory.getDb_connectionDAO();
            db_connectionDAO.closeAllConnections();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
