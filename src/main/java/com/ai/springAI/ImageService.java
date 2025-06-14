package com.ai.springAI;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageService {

    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel, OpenAiImageModel openAiImageModel1) {
        this.openAiImageModel = openAiImageModel1;
    }

    public ImageResponse generateImage(String prompt, int n , int width , int height) throws IOException {
        ImageResponse imageResponse = openAiImageModel.call(
                new ImagePrompt(prompt, OpenAiImageOptions.builder().model("dall-e-2").N(n).width(width).height(height).build())
        );

        return imageResponse;
    }
}
