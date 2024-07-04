package com.scottdavidson.springai.adapters.web;

import com.scottdavidson.springai.domain.Answer;
import com.scottdavidson.springai.domain.Question;
import com.scottdavidson.springai.domain.StateOrCountry;
import com.scottdavidson.springai.ports.in.AskQuestionQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final AskQuestionQuery askQuestionQuery;

    public QuestionController(AskQuestionQuery askQuestionQuery) {
        this.askQuestionQuery = askQuestionQuery;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question){
        return this.askQuestionQuery.askQuestion(question);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody StateOrCountry stateOrCountry){
        return this.askQuestionQuery.getCapital(stateOrCountry);
    }

    @PostMapping("/capital-extended")
    public Answer getCapitalExtended(@RequestBody StateOrCountry stateOrCountry){
        return this.askQuestionQuery.getCapitalWithExtendedInformation(stateOrCountry);
    }


}
