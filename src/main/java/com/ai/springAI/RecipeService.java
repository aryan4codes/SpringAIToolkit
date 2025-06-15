package com.ai.springAI;


import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeService {

    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients , String cuisine, String dietaryRestrictions){
        String template = String.format(
                """
                        I want to create a recipe with the following requirements:
                        ingredients: %s
                        
                        cuisine: %s
                        
                        dietary restrictions: %s
                        
                        Help me create a recipe!
                        """,
                ingredients, cuisine, dietaryRestrictions
        );
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of("ingredients", ingredients, "cuisine", cuisine, "dietaryRestrictions", dietaryRestrictions);

        Prompt prompt = promptTemplate.create(params);

        return chatModel.call(prompt).getResult().getOutput().getText().toString();
    }
}
