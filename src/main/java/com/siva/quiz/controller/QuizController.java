package com.siva.quiz.controller;


import com.siva.quiz.model.Question;
import com.siva.quiz.model.QuestionWrapper;
import com.siva.quiz.model.Quiz;
import com.siva.quiz.model.Response;
import com.siva.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("quiz")
@RestController
public class QuizController{
    @Autowired
    private QuizService quizService;
    @PostMapping("/createQuiz")
    public ResponseEntity<Quiz> createQuiz(@RequestParam String quizCategory,
    @RequestParam int noOfQuestions, @RequestParam String title) {
        Quiz quiz = quizService.createQuiz(quizCategory, noOfQuestions, title);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);

    }

    @GetMapping("/getQuiz/{quizId}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Integer quizId) {
       // List<Question> quiz = quizService.getQuiz();
        return  quizService.getQuizQuestion(quizId);
       // return new ResponseEntity<>( HttpStatus.OK);

    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response) {
        return  quizService.getQuizScore(id,response);
    }
}
