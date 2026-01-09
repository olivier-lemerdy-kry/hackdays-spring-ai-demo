package se.kry.hackdaysspringaidemo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import se.kry.hackdaysspringaidemo.domain.SectionAndWordsCount;

@Service
@RequiredArgsConstructor
public class ApplicationService {

  private final OllamaChatModel ollamaChatModel;
  private final EmbeddingModel embeddingModel;
  private final VectorStore vectorStore;

  public ChatResponse askWithTool(String prompt) {
    return ChatClient.builder(ollamaChatModel)
        .build().prompt()
        .user(prompt)
        .tools(new DateTimeTool())
        .call()
        .chatResponse();
  }

  public ChatResponse askWithoutTool(String prompt) {
    return ChatClient.builder(ollamaChatModel)
        .build().prompt()
        .user(prompt)
        .call()
        .chatResponse();
  }

  public List<SectionAndWordsCount> askOllamaWithAdvise(String prompt) {
    return ChatClient.builder(ollamaChatModel)
        .build().prompt()
        .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
        .user(prompt)
        .call()
        .entity(new ParameterizedTypeReference<>() {});
  }

  public static class DateTimeTool {

    @Tool(description = "Get the current date and time in the provided timezone (eg. Europe/Paris for Paris timezone)")
    public String getCurrentDateTime(String zone) {
      IO.println("Tool called with zone: " + zone);
      return LocalDateTime.now(ZoneId.of(zone)).toString();
    }
  }

  public EmbeddingResponse embed(String text) {
    return embeddingModel.embedForResponse(List.of(text));
  }


}
