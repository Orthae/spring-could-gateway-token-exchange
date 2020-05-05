package orthae.com.github.tokenexchangegatewayjava;


import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class ExchangeFilter implements WebFilter {

  public ExchangeFilter(WebClient webClient) {
    this.webClient = webClient;
  }

  private final WebClient webClient;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return webClient
            .post()
            .uri("http://identity:8080/identity/exchange")
            .body(prepareBody(exchange))
            .retrieve()
            .bodyToMono(Exchange.class)
            // Replacing Authorization header with token from identity service
            .map(it -> exchange.getRequest().mutate().header("Authorization", it.getToken()))
            .flatMap(ignored -> chain.filter(exchange));
  }


  public BodyInserter<Exchange, ReactiveHttpOutputMessage> prepareBody(ServerWebExchange serverExchange) {
    final List<String> headers = serverExchange.getRequest().getHeaders().get("Authorization");
    final String token = headers != null ? headers.get(0) : null;
    return BodyInserters.fromValue(new Exchange(token));
  }


}
