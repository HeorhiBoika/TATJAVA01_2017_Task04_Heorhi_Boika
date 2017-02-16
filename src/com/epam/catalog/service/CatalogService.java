package com.epam.catalog.service;

import com.epam.catalog.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by PC on 30.01.2017.
 */
public interface CatalogService {

    void addNewProduct(String type, String name, String genre) throws ServiceException;

    HashSet getCatalog() throws ServiceException;

    HashSet findByType(String type) throws ServiceException;

    HashSet findByName(String name) throws ServiceException;

    HashSet findByGenre(String genre) throws ServiceException;
}
