package com.siva.quiz.controller;

import com.siva.quiz.model.Question;
import com.siva.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> allQuestions = questionService.getAllQuestions();
        return new ResponseEntity<>(allQuestions, HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        List<Question> questionByCategory = questionService.getQuestionByCategory(category);
        return new ResponseEntity<>(questionByCategory, HttpStatus.OK);

    }

    @PostMapping("addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question questionAdded = questionService.addQuestion(question);
        return new ResponseEntity<>(questionAdded, HttpStatus.CREATED);
    }

    @PutMapping("updateQuestion/{questionId}")
    public ResponseEntity<Optional<Question>> updateQuestion(@RequestBody Question question, @PathVariable Integer questionId) {
        Optional<Question> updatedQuestion = questionService.updateQuestion(question, questionId);
        if (updatedQuestion.isPresent()) {
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("deleteQuestion/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Integer questionId) {
        boolean isDeleted = questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
