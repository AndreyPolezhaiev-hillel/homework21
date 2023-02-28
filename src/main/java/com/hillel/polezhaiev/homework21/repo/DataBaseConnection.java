package com.hillel.polezhaiev.homework21.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseConnection implements DataBase{


    private String username;
    private String password;

    public DataBaseConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/homework_hillel";

            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                return connection;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close(Connection connection) throws SQLException {
        connection.close();
    }
}
