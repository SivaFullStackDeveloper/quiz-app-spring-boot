package com.siva.quiz.service;


import com.siva.quiz.dao.QuestionDao;
import com.siva.quiz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }

    public Optional<Question> updateQuestion(Question question, Integer questionId) {
        Optional<Question> questionExist = questionDao.findById(questionId);
        if(questionExist.isPresent()){
            Question existingQuestion = questionExist.get();
            existingQuestion.setQuestionTitle(question.getQuestionTitle());
            existingQuestion.setOption1(question.getOption1());
            existingQuestion.setOption2(question.getOption2());
            existingQuestion.setOption3(question.getOption3());
            existingQuestion.setOption4(question.getOption4());
            existingQuestion.setRightAnswer(question.getRightAnswer());
            existingQuestion.setDifficultyLevel(question.getDifficultyLevel());
            return Optional.of(questionDao.save(existingQuestion));
        }
        return Optional.empty();
    }

    public boolean deleteQuestion(Integer id) {
        Optional<Question> questionExist = questionDao.findById(id);
        if(questionExist.isPresent()){
            questionDao.deleteById(id);
            return true;
        }
        return false;
    }
}
