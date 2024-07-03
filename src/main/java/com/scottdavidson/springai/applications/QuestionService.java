package com.scottdavidson.springai.applications;

import com.scottdavidson.springai.ports.in.AskQuestionQuery;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class QuestionService implements AskQuestionQuery {

    private final ChatClient chatClient;

    public QuestionService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();

    }
    @Override
    public String askQuestion(String question) {


        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = this.chatClient.call(prompt);

        return response.getResult().getOutput().getContent();
    }
}
