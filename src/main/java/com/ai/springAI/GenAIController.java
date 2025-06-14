package com.ai.springAI;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final OpenAiImageModel openAiImageModel;
    private final ImageService imageService;

    public GenAIController(ChatService chatService, OpenAiImageModel openAiImageModel, ImageService imageService) {
        this.chatService = chatService;
        this.openAiImageModel = openAiImageModel;
        this.imageService = imageService;
    }

    @GetMapping("ask")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("generate-image")
    public List<String> generateImages(HttpServletResponse response, @RequestParam String prompt,
                                       @RequestParam(value="n", defaultValue = "1") int n,
                                       @RequestParam(value="width", defaultValue = "1024") int width,
                                       @RequestParam(value="height", defaultValue = "1024") int height) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt, n , width , height);
        //streams to get urls from imageresponse

        List<String> imageURLs = imageResponse.getResults().stream().map(result -> result.getOutput().getUrl()).collect(Collectors.toList());

        return imageURLs;
    }
}
