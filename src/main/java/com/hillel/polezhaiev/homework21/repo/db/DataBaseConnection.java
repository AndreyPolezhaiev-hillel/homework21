package com.hillel.polezhaiev.homework21.repo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnection implements AutoCloseable{


    private String username;
    private String password;
    private String url;
    private Connection connection;

    public DataBaseConnection(String url, String username, String password) {
        this.username = username;
        this.password = password;
        this.url = url;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
