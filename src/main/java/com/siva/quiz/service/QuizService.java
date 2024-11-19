package com.siva.quiz.service;

import com.siva.quiz.dao.QuestionDao;
import com.siva.quiz.dao.QuizDao;
import com.siva.quiz.model.Question;
import com.siva.quiz.model.QuestionWrapper;
import com.siva.quiz.model.Quiz;
import com.siva.quiz.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;

    public Quiz createQuiz(String category, int noOfQuestions, String title) {
        List<Question> randomQuestionByCategory = questionDao.findRandomQuestionByCategory(category, noOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuizCategory(category);
        quiz.setQuestions(randomQuestionByCategory);
        return quizDao.save(quiz);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer quizId) {
        Optional<Quiz> quiz = quizDao.findById(quizId);
        List<Question> quizQuestions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappersForUsers = new ArrayList<>();

        for (Question question : quizQuestions) {
            QuestionWrapper q = new QuestionWrapper(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
            );
            questionWrappersForUsers.add(q);
        }
        return new ResponseEntity<>(questionWrappersForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getQuizScore(Integer id, List<Response> responses) {
        Quiz quizQuestion = quizDao.findById(id).get();
        List<Question> questions = quizQuestion.getQuestions();
        int rightAnswers = 0;
        int i = 0;
        for (Response response : responses) {

            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                rightAnswers++;
            }
            i++;
        }

        return new ResponseEntity<>(rightAnswers, HttpStatus.OK);
    }
}
