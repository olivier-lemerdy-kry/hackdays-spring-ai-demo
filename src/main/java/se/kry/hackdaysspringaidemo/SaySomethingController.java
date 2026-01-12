package se.kry.hackdaysspringaidemo;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kry.hackdaysspringaidemo.domain.ActorFilms;

@RestController
@RequiredArgsConstructor
public class SaySomethingController {

  private final ApplicationService applicationService;

  @GetMapping("ask/ollama")
  public ChatResponse askOllama() {
    return applicationService.askOllama("What is the meaning of life?");
  }

  @GetMapping("ask/mistral")
  public ChatResponse askMistralAI() {
    return applicationService.askMistralAI("What is the meaning of life?");
  }

  @GetMapping("actor/films")
  public List<ActorFilms> getActorFilms() {
    return applicationService.generateSpecificMoviesWithOllamaAI();
  }

}
