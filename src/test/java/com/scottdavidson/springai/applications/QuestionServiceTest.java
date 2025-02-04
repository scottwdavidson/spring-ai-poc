package com.scottdavidson.springai.applications;

import com.scottdavidson.springai.domain.Answer;
import com.scottdavidson.springai.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {

    private final String DAD_JOKE_PROMPT = "Tell me a Dad joke.";
    private final String PYTHON_COUNT_TO_TEN_PROMPT = "Write a python script to output numbers from 1 to 10";

    @Autowired
    QuestionService questionService;

    @org.junit.jupiter.api.Test
    void askQuestion() {

        Answer answer = questionService.askQuestion(new Question(DAD_JOKE_PROMPT));
        System.out.println("Got the answer ... ");
        System.out.println(answer.answer());

    }
}