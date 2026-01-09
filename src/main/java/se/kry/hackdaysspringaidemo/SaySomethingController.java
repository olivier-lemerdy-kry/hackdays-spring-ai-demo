package se.kry.hackdaysspringaidemo;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.kry.hackdaysspringaidemo.domain.SectionAndWordsCount;
import se.kry.hackdaysspringaidemo.ingest.PdfIngestion;

@RestController
@RequiredArgsConstructor
public class SaySomethingController {

  private final ApplicationService applicationService;

  private final PdfIngestion pdfIngestion;

  @GetMapping("functions/embed")
  public Map<String, Object> embed(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
    return Map.of("embeddings", applicationService.embed(message));
  }

  @GetMapping("ask/with-tool")
  public ChatResponse askSomethingWithTool() {
    return applicationService.askWithTool("What time is it in Tokyo right now?");
  }

  @GetMapping("ask/without-tool")
  public ChatResponse askSomethingWithoutTool() {
    return applicationService.askWithoutTool("What time is it in Tokyo right now?");
  }

  @GetMapping("ask/something-with-advice")
  public List<SectionAndWordsCount> askSomethingWithAdvice() {
    return applicationService.askOllamaWithAdvise("Give me all the section title and the words count in their content in my document?");
  }

  @GetMapping("load/pdf")
  public void loadPdf() {
    pdfIngestion.load();
  }

}
