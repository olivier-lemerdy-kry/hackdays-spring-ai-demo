package se.kry.hackdaysspringaidemo;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kry.hackdaysspringaidemo.domain.ActorFilms;
import se.kry.hackdaysspringaidemo.ingest.PdfIngestion;

@RestController
@RequiredArgsConstructor
public class SaySomethingController {

  private final ApplicationService applicationService;

  private final PdfIngestion pdfIngestion;

  @GetMapping("ask/something")
  public ChatResponse saySomething() {
    return applicationService.askOllama("Tell me something smart and fun");
  }

  @GetMapping("ask/something-with-advice")
  public ChatResponse askSomethingWithAdvice() {
    return applicationService.askOllamaWithAdvise("");
  }

  @GetMapping("actor/films")
  public List<ActorFilms> getActorFilms() {
    return applicationService.generateSpecificMoviesWithOllamaAI();
  }

  @GetMapping("load/pdf")
  public void loadPdf() {
    pdfIngestion.load();
  }

}
