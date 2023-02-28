package com.hillel.polezhaiev.homework21.homeworkservice;

import com.hillel.polezhaiev.homework21.model.Homework;
import com.hillel.polezhaiev.homework21.repo.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HomeworkService {
    private DataBase dataBase;

    public HomeworkService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void addHomework(Homework homework){
        if(homework == null){
            System.out.println("paramater was null");
            return;
        }

        String sql =  "INSERT INTO homework(name, description)"
                    + " VALUES(?, ?)";

        try(Connection connection = dataBase.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, homework.getName());
            preparedStatement.setString(2, homework.getDescription());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
