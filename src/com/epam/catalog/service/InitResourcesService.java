package com.epam.catalog.service;

import com.epam.catalog.service.exception.ServiceException;

/**
 * Created by PC on 16.02.2017.
 */
public interface InitResourcesService {

    void init_Resources() throws ServiceException;

    void close_Resources() throws ServiceException;
}
