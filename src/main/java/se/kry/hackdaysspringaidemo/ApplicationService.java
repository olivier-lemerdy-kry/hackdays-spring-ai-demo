package se.kry.hackdaysspringaidemo;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import se.kry.hackdaysspringaidemo.domain.ActorFilms;

@Service
@RequiredArgsConstructor
public class ApplicationService {

  private final MistralAiChatModel mistralAiChatModel;

  private final OllamaChatModel ollamaChatModel;

//  private final OpenAiChatModel openAiChatModel;

  public ChatResponse askMistralAI(String prompt) {
    return mistralAiChatModel.call(
        new Prompt(
            prompt,
            MistralAiChatOptions.builder()
                .build()
        ));
  }

  ChatResponse askOllama(String prompt) {
    return ollamaChatModel.call(
        new Prompt(
            prompt,
            OllamaChatOptions.builder()
                .model("llama3.2")
                .build()
        ));
  }

//  public ActorFilms generateMoviesWithOllamaAI() {
//    var chatClient = ChatClient.builder(ollamaChatModel).build();
//    return chatClient
//        .prompt()
//        .user("Generate the filmography for a random actor.")
//        .call()
//        .entity(ActorFilms.class);
//  }

  public List<ActorFilms> generateSpecificMoviesWithOllamaAI() {
    var chatClient = ChatClient.builder(ollamaChatModel).build();
    return chatClient
        .prompt()
        .user("Generate the filmography of 5 movies for Tom Hanks and Bill Murray.")
        .call()
        .entity(new ParameterizedTypeReference<>() {
        });
  }

//  public ChatResponse saySomethingWithOpenAI() {
//    return openAiChatModel.call(
//        new Prompt(
//            "In 3 words, explain the meaning of life.",
//            OpenAiChatOptions.builder()
//                .model("gpt-4o")
//                .maxTokens(150)  // Use maxTokens for non-reasoning models
//                .build()
//        ));
//  }

}
