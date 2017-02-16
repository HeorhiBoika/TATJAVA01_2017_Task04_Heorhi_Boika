package com.epam.catalog.dao.impl;


import com.epam.catalog.bean.Catalog;
import com.epam.catalog.dao.NewsDAO;
import com.epam.catalog.dao.connectionPool.ConnectionPool;
import com.epam.catalog.dao.exception.DAOException;

import java.sql.*;
import java.util.*;

/**
 * Created by PC on 30.01.2017.
 */
public class SQLCatalogDAO implements NewsDAO {


    public SQLCatalogDAO() {
    }

    @Override
    public void addProduct(String type, String name, String genre) throws DAOException {
        if (isNewsContainsInDB(type, name, genre)) {
            ConnectionPool connectionPool = null;
            Connection connection = null;
            Statement statement = null;
            try {
                connectionPool = ConnectionPool.getInstance();
                connectionPool.initPoolData();
                connection = connectionPool.takeConnection();
                statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO `News`.`Catalog` (`TypeNews`, `NameNews`, `Genre`) VALUES('" + type + "', '" + name + "', '" + genre + "');");
            } catch (SQLException e) {
                connectionPool.closeConnection(connection);
                throw new DAOException(e.getMessage(), e);
            } finally {
                connectionPool.putConnection(connection);
                connectionPool.closeConnection(statement);
            }
        } else {
            throw new DAOException("This news contained in catalog");
        }
    }

    private boolean isNewsContainsInDB(String type, String name, String genre) throws DAOException {
        String sqlRequest = "select * from catalog where NameNews=" + "'" + name + "'" + "and TypeNews=" + "'" + type + "'" + "and Genre=" + "'" + genre + "'";
        ConnectionPool connectionPool = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(sqlRequest);
            rs = ps.executeQuery();
            while (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            connectionPool.closeConnection(connection);
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.putConnection(connection);
            connectionPool.closeConnection(rs, ps);
        }
        return true;
    }

    @Override
    public HashSet findByName(String name) throws DAOException {
        HashSet<Catalog> findByNameList = new HashSet<>();
        String sqlRequest = "select * from catalog where NameNews=" + "'" + name + "'";
        ConnectionPool connectionPool = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(sqlRequest);
            rs = ps.executeQuery();
            while (rs.next()) {
                findByNameList.add(new Catalog(rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            connectionPool.closeConnection(connection);
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.closeConnection(rs, ps);
            connectionPool.putConnection(connection);
        }
        if (findByNameList.isEmpty()) {
            throw new DAOException("Error, this name didn't find in catalog");
        }
        return findByNameList;
    }

    @Override
    public HashSet findByGenre(String genre) throws DAOException {
        HashSet<Catalog> findByGenreList = new HashSet<>();
        String sqlRequest = "select * from catalog where Genre=" + "'" + genre + "'";
        ConnectionPool connectionPool = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(sqlRequest);
            rs = ps.executeQuery();
            while (rs.next()) {
                findByGenreList.add(new Catalog(rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            connectionPool.closeConnection(connection);
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.putConnection(connection);
            connectionPool.closeConnection(rs, ps);
        }
        if (findByGenreList.isEmpty()) {
            throw new DAOException("Error, this genre didn't find in catalog");
        }
        return findByGenreList;
    }

    @Override
    public HashSet findByType(String type) throws DAOException {
        HashSet<Catalog> findByTypeList = new HashSet<>();
        String sqlRequest = "select * from catalog where TypeNews=" + "'" + type + "'";
        ConnectionPool connectionPool = null;
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            ps = connection.prepareStatement(sqlRequest);
            rs = ps.executeQuery();
            while (rs.next()) {
                findByTypeList.add(new Catalog(rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            connectionPool.closeConnection(connection);
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.putConnection(connection);
            connectionPool.closeConnection(rs, ps);
        }
        if (findByTypeList.isEmpty()) {
            throw new DAOException("Error, this type didn't find in catalog");
        }
        return findByTypeList;
    }

    @Override
    public HashSet getCatalog() throws DAOException {
        HashSet<Catalog> news = new HashSet<>();
        ConnectionPool connectionPool = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM catalog");
            while (rs.next()) {
                news.add(new Catalog(rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            connectionPool.closeConnection(connection);
            throw new DAOException(e.getMessage(), e);
        } finally {
            connectionPool.putConnection(connection);
            connectionPool.closeConnection(rs, statement);
        }
        if(news.isEmpty()){
            throw new DAOException("Catalog is empty!");
        }
        return news;
    }
}
