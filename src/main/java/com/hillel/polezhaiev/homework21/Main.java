package com.hillel.polezhaiev.homework21;

import com.hillel.polezhaiev.homework21.repo.HomeworkDaoImpl;
import com.hillel.polezhaiev.homework21.repo.HomeworkDao;
import com.hillel.polezhaiev.homework21.repo.LessonDaoImpl;
import com.hillel.polezhaiev.homework21.repo.LessonDao;
import com.hillel.polezhaiev.homework21.model.Homework;
import com.hillel.polezhaiev.homework21.model.Lesson;
import com.hillel.polezhaiev.homework21.repo.db.DataBaseConnection;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        String username = System.getenv("MYSQL_USER");
        String password = System.getenv("MYSQL_PASSWORD");
        String url = "jdbc:mysql://127.0.0.1:3306/homework_hillel";

        DataBaseConnection dataBase = new DataBaseConnection(url, username, password);

        Homework homework1 = new Homework(1, "homeworkMath", "count: 5 + 5");
        Homework homework2 = new Homework(2, "homeworkLiterature", "read a book");
        Homework homework3 = new Homework(3, "homeworkSport", "work out 5 minutes");

        HomeworkDao homeworkService = new HomeworkDaoImpl(dataBase);

//        homeworkService.addHomework(homework1);
//        homeworkService.addHomework(homework2);
//        homeworkService.addHomework(homework3);

        Lesson lesson1 = new Lesson(1, "Math", homework1);
        Lesson lesson2 = new Lesson(2, "Literature", homework2);
        Lesson lesson3 = new Lesson(3, "Sport", homework3);

        LessonDao lessonService = new LessonDaoImpl(dataBase);

//        lessonService.addLesson(null);
//        lessonService.addLesson(lesson2);
//        lessonService.addLesson(lesson3);

        Lesson lessonById1 = lessonService.getLessonById(3);
        Lesson lessonById2 = lessonService.getLessonById(4);
        Lesson lessonById3 = lessonService.getLessonById(2);
        System.out.println(lessonById1);
        System.out.println(lessonById2);
        System.out.println(lessonById3);

        System.out.println();
        lessonService.deleteLesson(lesson2);

        System.out.println();
        for (Lesson lesson: lessonService.getLessons()) {
            System.out.println(lesson);
        }

        dataBase.close();
        System.out.println();
    }
}
