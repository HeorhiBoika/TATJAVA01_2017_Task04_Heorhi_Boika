package com.epam.catalog.controller.impl;

import com.epam.catalog.bean.Request;
import com.epam.catalog.bean.Response;
import com.epam.catalog.bean.catalog.ResponseCatalog;
import com.epam.catalog.controller.Command;
import com.epam.catalog.controller.exception.ControllerException;
import com.epam.catalog.service.InitResourcesService;
import com.epam.catalog.service.exception.ServiceException;
import com.epam.catalog.service.factory.ServiceFactory;

/**
 * Created by PC on 09.02.2017.
 */
public class CloseResources implements Command {
    @Override
    public Response execute(Request request) throws ControllerException {
        ResponseCatalog response = new ResponseCatalog();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        InitResourcesService initResourcesService = serviceFactory.getInitResourcesService();
        try {
            initResourcesService.close_Resources();
        } catch (ServiceException e) {
            response.setErrorMessage(e.getMessage());
            response.setStatus(-1);
        }
        return response;
    }
}
