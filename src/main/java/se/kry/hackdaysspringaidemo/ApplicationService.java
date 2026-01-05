package se.kry.hackdaysspringaidemo;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

  private final MistralAiChatModel mistralAiChatModel;

  private final OllamaChatModel ollamaChatModel;

  private final OpenAiChatModel openAiChatModel;

  public ChatResponse saySomethingWithMistralAI() {
    return mistralAiChatModel.call(
        new Prompt(
            "In 3 words, explain the meaning of life.",
            MistralAiChatOptions.builder()
                .build()
        ));
  }

  public ChatResponse saySomethingWithOllamaAI() {
    return ollamaChatModel.call(
        new Prompt(
            "How to solve the DRAM availability crisis?",
            OllamaChatOptions.builder()
                .model("llama3.2")
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
