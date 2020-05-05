package orthae.com.github.tokenexchangegateway.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.ReactiveHttpOutputMessage
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@SpringBootApplication
class TokenExchangeGatewayKotlinApplication{
	@Bean
	fun webClient(): WebClient? {
		return WebClient.create()
	}
}

fun main(args: Array<String>) {
	runApplication<TokenExchangeGatewayKotlinApplication>(*args)
}

@Component
class ExchangeFilter(private val webClient: WebClient) : WebFilter {

	override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
		return webClient
				.post()
				.body(prepareBody(exchange))
				.retrieve()
				.bodyToMono(Exchange::class.java)
				// Replacing Authorization header with token from identity service
				.map { exchange.request.mutate().header("Authorization", it.token) }
				.flatMap { chain.filter(exchange) }
	}
}

fun prepareBody(serverExchange: ServerWebExchange): BodyInserter<Exchange, ReactiveHttpOutputMessage> {
	val exchange = Exchange(serverExchange.request.headers["Authorization"]?.get(0))
	return BodyInserters.fromValue(exchange)
}


data class Exchange(val token : String?)