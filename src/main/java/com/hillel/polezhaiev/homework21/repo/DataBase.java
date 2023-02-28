package com.hillel.polezhaiev.homework21.repo;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBase {
    Connection getConnection();
    void close(Connection connection) throws SQLException;
}
