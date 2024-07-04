package com.scottdavidson.springai.applications;

import com.scottdavidson.springai.domain.Answer;
import com.scottdavidson.springai.domain.Question;
import com.scottdavidson.springai.domain.StateOrCountry;
import com.scottdavidson.springai.ports.in.AskQuestionQuery;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

@Service
public class QuestionService implements AskQuestionQuery {

    private final ChatClient chatClient;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Autowired
    private ResourceLoader resourceLoader;

    public QuestionService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();

    }

    public Answer getCapital(StateOrCountry stateOrCountry){
        
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", stateOrCountry));
        ChatResponse response = this.chatClient.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public Answer askQuestion(Question question) {


        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse response = this.chatClient.call(prompt);

        Answer answer = new Answer(response.getResult().getOutput().getContent());
        System.out.println("answer: " + answer);
        return answer;
    }
}
