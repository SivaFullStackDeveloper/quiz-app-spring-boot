package com.siva.quiz.dao;

import com.siva.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);
    @Query(value = "SELECT * FROM questions q WHERE q.category = :quizCategory ORDER BY RANDOM() LIMIT :noOfQuestions",nativeQuery = true)
    List<Question> findRandomQuestionByCategory( String quizCategory, int noOfQuestions);

}
