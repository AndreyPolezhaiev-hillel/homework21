package com.hillel.polezhaiev.homework21.repo;

import com.hillel.polezhaiev.homework21.model.Homework;
import com.hillel.polezhaiev.homework21.repo.db.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HomeworkDaoImpl implements HomeworkDao {
    private DataBaseConnection dataBase;

    public HomeworkDaoImpl(DataBaseConnection dataBase) {
        this.dataBase = dataBase;
    }

    public void addHomework(Homework homework){
        if(homework == null){
            System.out.println("paramater was null");
            return;
        }

        String sql =  "INSERT INTO homework(name, description)"
                    + " VALUES(?, ?)";

        try {
            Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, homework.getName());
            preparedStatement.setString(2, homework.getDescription());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
