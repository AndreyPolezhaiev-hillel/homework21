package com.hillel.polezhaiev.homework21.repo;

import com.hillel.polezhaiev.homework21.model.Lesson;

import java.util.List;

public interface LessonDao {
    void addLesson(Lesson lesson);
    void deleteLesson(Lesson lesson);
    List<Lesson> getLessons();
    Lesson getLessonById(Integer lessonId);
}
