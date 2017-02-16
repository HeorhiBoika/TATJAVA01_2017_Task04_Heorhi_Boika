package com.epam.catalog.service.impl;

import com.epam.catalog.bean.Catalog;
import com.epam.catalog.dao.DB_ConnectionDAO;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.exception.DAOException;
import com.epam.catalog.dao.factory.DAOFactory;
import com.epam.catalog.service.CatalogService;
import com.epam.catalog.service.exception.ServiceException;

import java.util.HashSet;

/**
 * Created by PC on 31.01.2017.
 */
public class CatalogServiceImpl implements CatalogService {

    public CatalogServiceImpl() {

    }

    @Override
    public void addNewProduct(String type, String name, String genre) throws ServiceException {
        if ((type.toLowerCase().equals("фильм")) | (type.toLowerCase().equals("книга")) | (type.toLowerCase().equals("диск"))) {
            try {
                DAOFactory daoFactory = DAOFactory.getInstance();
                NewsDAO newsDAO = daoFactory.getProductDAO();
                newsDAO.addProduct(type.toLowerCase(), name.toLowerCase(), genre.toLowerCase());
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        } else {
            throw new ServiceException("Type may be only : фильм, книга or диск");
        }
    }

    @Override
    public HashSet getCatalog() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getProductDAO();
        HashSet<Catalog> catalog;
        try {
            catalog = newsDAO.getCatalog();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return catalog;

    }

    @Override
    public HashSet findByGenre(String genre) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getProductDAO();
        HashSet<Catalog> genreList;
        try {
            genreList = newsDAO.findByGenre(genre.toLowerCase());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return genreList;
    }

    @Override
    public HashSet findByName(String name) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getProductDAO();
        HashSet<Catalog> nameList;
        try {
            nameList = newsDAO.findByName(name.toLowerCase());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return nameList;
    }

    @Override
    public HashSet findByType(String type) throws ServiceException {
        HashSet<Catalog> typeList;
        if ((type.toLowerCase().equals("фильм")) | (type.toLowerCase().equals("книга")) | (type.toLowerCase().equals("диск"))) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            NewsDAO newsDAO = daoFactory.getProductDAO();
            try {
                typeList = newsDAO.findByType(type.toLowerCase());
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        } else {
            throw new ServiceException("Type may be only : фильм, книга or диск");
        }
        return typeList;
    }
}
