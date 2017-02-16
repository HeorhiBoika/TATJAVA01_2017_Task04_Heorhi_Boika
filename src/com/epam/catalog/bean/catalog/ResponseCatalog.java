package com.epam.catalog.bean.catalog;

import com.epam.catalog.bean.Catalog;
import com.epam.catalog.bean.Response;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by PC on 31.01.2017.
 */
public class ResponseCatalog extends Response {

    private HashSet<Catalog> news;

    public ResponseCatalog() {

    }

    public HashSet<Catalog> getNews() {

        return news;

    }

    public void setNews(HashSet<Catalog> news) {

        this.news = news;

    }

}
