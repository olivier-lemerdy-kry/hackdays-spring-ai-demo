package se.kry.hackdaysspringaidemo;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SaySomethingController {

  private final ApplicationService applicationService;

  @GetMapping("say/something")
  public ChatResponse saySomething() {
    return applicationService.saySomethingWithMistralAI();
  }

}
