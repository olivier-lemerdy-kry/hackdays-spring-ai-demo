package se.kry.hackdaysspringaidemo;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

  private final MistralAiChatModel mistralAiChatModel;

  private final OpenAiChatModel openAiChatModel;

  public ChatResponse saySomethingWithMistralAI() {
    return openAiChatModel.call(
        new Prompt(
            "In 3 words, explain the meaning of life.",
            OpenAiChatOptions.builder()
                .model("gpt-4o")
                .maxTokens(150)  // Use maxTokens for non-reasoning models
                .build()
        ));
  }

  public ChatResponse saySomethingWithOpenAI() {
    return openAiChatModel.call(
        new Prompt(
            "In 3 words, explain the meaning of life.",
            OpenAiChatOptions.builder()
                .model("gpt-4o")
                .maxTokens(150)  // Use maxTokens for non-reasoning models
                .build()
        ));
  }

}
