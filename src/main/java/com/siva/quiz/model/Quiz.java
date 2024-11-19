package com.siva.quiz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "quiz_category")
    private String quizCategory;
    @Column(name = "title")
    private String title;
    @Column(name = "questions")
    @ManyToMany
    List<Question>questions;
}
