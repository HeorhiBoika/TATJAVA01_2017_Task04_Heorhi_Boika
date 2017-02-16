package com.epam.catalog.controller.catalogController;

import com.epam.catalog.bean.Catalog;
import com.epam.catalog.bean.catalog.RequestCatalog;
import com.epam.catalog.bean.catalog.ResponseCatalog;
import com.epam.catalog.controller.Controller;
import com.epam.catalog.view.Console;

import java.util.HashSet;


/**
 * Created by PC on 01.02.2017.
 */
public class CatalogController extends Controller {

    public void initResources() {
        RequestCatalog requestCatalog = new RequestCatalog();
        requestCatalog.setCommand("INIT_RESOURCES");
        ResponseCatalog responseCatalog = (ResponseCatalog) executeTask(requestCatalog);
        render(responseCatalog);
        if (responseCatalog.getStatus() == 1) {
            Console console = Console.getInstance();
            console.showCatalogAction();
        }
    }

    public void closeResources() {
        RequestCatalog requestCatalog = new RequestCatalog();
        requestCatalog.setCommand("CLOSE_RESOURCES");
        executeTask(requestCatalog);
    }

    public void addNewProduct(String type, String name, String genre) {
        RequestCatalog requestCatalog = new RequestCatalog();
        requestCatalog.setCommand("ADD_PRODUCT");
        requestCatalog.setType(type);
        requestCatalog.setName(name);
        requestCatalog.setGenre(genre);
        ResponseCatalog responseCatalog = (ResponseCatalog) executeTask(requestCatalog);
        render(responseCatalog);
        Console console = Console.getInstance();
        console.showCatalogAction();
    }

    public void findByType(String type) {
        RequestCatalog requestCatalog = new RequestCatalog();
        requestCatalog.setCommand("FIND_BY_TYPE");
        requestCatalog.setType(type);
        ResponseCatalog responseCatalog = (ResponseCatalog) executeTask(requestCatalog);
        render(responseCatalog);
        if (responseCatalog.getStatus() == 1) {
            printCatalog(responseCatalog);
        }
        Console console = Console.getInstance();
        console.showCatalogAction();
    }

    public void findByName(String name) {
        RequestCatalog requestCatalog = new RequestCatalog();
        requestCatalog.setCommand("FIND_BY_NAME");
        requestCatalog.setName(name);
        ResponseCatalog responseCatalog = (ResponseCatalog) executeTask(requestCatalog);
        render(responseCatalog);
        if (responseCatalog.getStatus() == 1) {
            printCatalog(responseCatalog);
        }
        Console console = Console.getInstance();
        console.showCatalogAction();
    }

    public void findByGenre(String genre) {
        RequestCatalog requestCatalog = new RequestCatalog();
        requestCatalog.setCommand("FIND_BY_GENRE");
        requestCatalog.setGenre(genre);
        ResponseCatalog responseCatalog = (ResponseCatalog) executeTask(requestCatalog);
        render(responseCatalog);
        if (responseCatalog.getStatus() == 1) {
            printCatalog(responseCatalog);
        }
        Console console = Console.getInstance();
        console.showCatalogAction();
    }

    public void getCatalog() {
        RequestCatalog requestCatalog = new RequestCatalog();
        requestCatalog.setCommand("GET_CATALOG");
        ResponseCatalog responseCatalog = (ResponseCatalog) executeTask(requestCatalog);
        render(responseCatalog);
        if (responseCatalog.getStatus() == 1) {
            printCatalog(responseCatalog);
        }
        Console console = Console.getInstance();
        console.showCatalogAction();

    }


    public void printCatalog(ResponseCatalog responseCatalog) {
        HashSet<Catalog> news = responseCatalog.getNews();
        System.out.println("-------------------");
        for (Catalog line : news) {
            System.out.println(line);

        }
        System.out.println("-------------------");
    }


}
