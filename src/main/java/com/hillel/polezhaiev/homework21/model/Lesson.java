package com.hillel.polezhaiev.homework21.model;

import java.util.Objects;

public class Lesson {
    private Integer id;
    private String name;
    private Homework homework;
    private Integer homeworkId;

    public Lesson(Integer id, String name, Homework homework) {
        this.id = id;
        this.name = name;
        this.homework = homework;
        this.homeworkId = homework.getId();
    }

    public Lesson() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homework_id) {
        this.homeworkId = homework_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(id, lesson.id) && Objects.equals(name, lesson.name) && Objects.equals(homework, lesson.homework) && Objects.equals(homeworkId, lesson.homeworkId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, homework, homeworkId);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homework=" + homework +
                ", homework_id=" + homeworkId +
                '}';
    }
}
