package com.scottdavidson.springai.ports.in;

import com.scottdavidson.springai.domain.Answer;
import com.scottdavidson.springai.domain.Question;
import com.scottdavidson.springai.domain.StateOrCountry;

public interface AskQuestionQuery {
    Answer askQuestion(Question question);
    Answer getCapital(StateOrCountry stateOrCountry);

}
