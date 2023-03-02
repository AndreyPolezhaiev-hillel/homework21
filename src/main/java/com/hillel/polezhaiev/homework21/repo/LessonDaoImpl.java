package com.hillel.polezhaiev.homework21.repo;

import com.hillel.polezhaiev.homework21.model.Homework;
import com.hillel.polezhaiev.homework21.model.Lesson;
import com.hillel.polezhaiev.homework21.repo.db.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {

    private DataBaseConnection dataBase;
    private List<Lesson> lessonList = new LinkedList<>();

    public LessonDaoImpl(DataBaseConnection dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void addLesson(Lesson lesson) {
        if(lesson == null){
            System.out.println("paramater addLesson was null");
            return;
        }

        String sql =  " INSERT INTO lesson(name, updatedAt, homework_id)"
                    + " VALUES(?, ?, ?)";

        try {
            Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, lesson.getName());
            preparedStatement.setString(2, LocalDateTime.now().toString());
            preparedStatement.setInt(3, lesson.getHomework().getId());

            preparedStatement.execute();

            lessonList.add(lesson);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteLesson(Lesson lesson) {
        if(lesson == null){
            System.out.println("paramater deleteLesson was null");
            return;
        }

        String sql = "DELETE FROM lesson WHERE name = ?";

        try {
            Connection connection = dataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, lesson.getName());
            preparedStatement.execute();

            lessonList.remove(lesson);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Lesson> getLessons() {
        return lessonList;
    }

    @Override
    public Lesson getLessonById(Integer lessonId) {
        if(lessonId == null){
            System.out.println("paramater getLessonById was null");
            return null;
        }

        String sql = " SELECT * FROM lesson"
                    +" JOIN homework"
                    +" ON lesson.homework_id = homework.id"
                    +" WHERE lesson.id = ?";
        try{
            Connection connection = dataBase.getConnection();
            PreparedStatement statementLesson = connection.prepareStatement(sql);

            statementLesson.setInt(1, lessonId);
            statementLesson.execute();

            ResultSet resultSetLesson = statementLesson.getResultSet();


            if(resultSetLesson.next()){
                Lesson lesson = new Lesson();
                lesson.setId(resultSetLesson.getInt("id"));
                lesson.setName(resultSetLesson.getString("name"));
                lesson.setHomeworkId(resultSetLesson.getInt("homework_id"));

                String hmw = "SELECT * FROM homework WHERE id = ?";

                PreparedStatement statementHomework = connection.prepareStatement(hmw);
                statementHomework.setInt(1, lesson.getHomeworkId());
                statementHomework.execute();

                ResultSet resultSetHomework = statementHomework.getResultSet();
                resultSetHomework.next();

                Homework homework = new Homework();
                homework.setId(resultSetHomework.getInt("id"));
                homework.setName(resultSetHomework.getString("name"));
                homework.setDescription(resultSetHomework.getString("description"));

                lesson.setHomework(homework);
                return lesson;

            } else {
                Lesson lesson = new Lesson();
                lesson.setId(null);
                lesson.setName(null);
                lesson.setHomework(null);
                lesson.setHomeworkId(null);

                return lesson;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


//    @Override
//    public Lesson getLessonById(Integer lessonId) {
//        if(lessonId == null){
//            System.out.println("paramater getLessonById was null");
//            return null;
//        }
//
//        String sqlLesson = "SELECT * FROM lesson WHERE id = ?";
//        String sqlHomework = "SELECT * FROM homework WHERE id = ?";
//
//        try{
//            Connection connection = dataBase.getConnection();
//            PreparedStatement statementLesson = connection.prepareStatement(sqlLesson);
//
//            statementLesson.setInt(1, lessonId);
//            statementLesson.execute();
//
//            ResultSet resultSetLesson = statementLesson.getResultSet();
//
//
//            if(resultSetLesson.next()){
//                Lesson lesson = new Lesson();
//                lesson.setId(resultSetLesson.getInt("id"));
//                lesson.setName(resultSetLesson.getString("name"));
//                lesson.setHomeworkId(resultSetLesson.getInt("homework_id"));
//
//                PreparedStatement statementHomework = connection.prepareStatement(sqlHomework);
//                statementHomework.setInt(1, lesson.getHomeworkId());
//                statementHomework.execute();
//
//                ResultSet resultSetHomework = statementHomework.getResultSet();
//                resultSetHomework.next();
//
//                Homework homework = new Homework();
//                homework.setId(resultSetHomework.getInt("id"));
//                homework.setName(resultSetHomework.getString("name"));
//                homework.setDescription(resultSetHomework.getString("description"));
//
//                lesson.setHomework(homework);
//                return lesson;
//
//            } else {
//                Lesson lesson = new Lesson();
//                lesson.setId(null);
//                lesson.setName(null);
//                lesson.setHomework(null);
//                lesson.setHomeworkId(null);
//
//                return lesson;
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


