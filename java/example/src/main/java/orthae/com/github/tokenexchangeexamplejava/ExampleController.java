package orthae.com.github.tokenexchangeexamplejava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
public class ExampleController {

  @GetMapping("/example")
  public Mono<String> example(@RequestHeader(value = "Authorization", required = false) String auth){
    return auth != null ? Mono.just(auth) : Mono.empty();
  }
}
