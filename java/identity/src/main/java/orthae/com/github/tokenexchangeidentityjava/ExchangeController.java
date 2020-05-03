package orthae.com.github.tokenexchangeidentityjava;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ExchangeController {

  @PostMapping("/identity/exchange")
  public Mono<Exchange> exchange(@RequestBody Mono<Exchange> request) {
    return request.flatMap(this::mapExchange);
  }

  public Mono<Exchange> mapExchange(Exchange request) {
    final Exchange response = new Exchange();
    switch (request.getToken()) {
      case "esEKSc":
        response.setToken("pG07Ks");
        break;
      case "QF6KIz":
        response.setToken("EEPoh4");
        break;
      case "8HHt1t":
        response.setToken("SImTSO");
        break;
      case "BhWimL":
        response.setToken("7fIU4n");
        break;
      default:
        return Mono.empty();
    }
    return Mono.just(response);
  }

}
