package com.epam.catalog.dao.connectionPool;

import com.epam.catalog.dao.exception.DAOException;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by PC on 08.02.2017.
 */
public class ConnectionPool {
    private static final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/News";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";
    private static final int Pool_size = 1;

    private BlockingQueue<Connection> connectionsQueue;

    private static ConnectionPool instance = new ConnectionPool();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initPoolData() throws DAOException {
        try {
            Class.forName(DB_DRIVER_NAME);
            connectionsQueue = new ArrayBlockingQueue<>(Pool_size);
            for (int i = 0; i < Pool_size; i++) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                connectionsQueue.add(connection);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DAOException(e.getMessage(), e);
        } catch (IllegalArgumentException e){
            throw new DAOException("Error, size of pool!!!");
        }
    }

    public void dispose() throws DAOException {
        clearConnectionsQueue();
    }

    private void clearConnectionsQueue() throws DAOException {
        try {
            closeConnectionsQueue(connectionsQueue);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public Connection takeConnection() throws DAOException {
        Connection connection;
        try {
            connection = connectionsQueue.take();
        } catch (InterruptedException e) {
            throw new DAOException(e.getMessage(), e);
        }
        return connection;
    }

    public void putConnection(Connection connection) throws DAOException {
        if ((connection != null) && (connectionsQueue.size() <= Pool_size)) {
            connectionsQueue.add(connection);
        }
    }


    public void closeConnection(Connection con) throws DAOException {
        try {
            con.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public void closeConnection(ResultSet rs, Statement st) throws DAOException {
        try {
            rs.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
        try {
            st.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    public void closeConnection(Statement st) throws DAOException {
        try {
            st.close();
        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            connection.close();
        }
    }
}
