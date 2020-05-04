package orthae.com.github.tokenexchangegateway.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
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
				.body(BodyInserters.fromValue(extractToken(exchange)))
				.retrieve()
				.bodyToMono(Exchange::class.java)
				.map { exchange.request.mutate().header("Authorization", it.token) }
				.flatMap { chain.filter(exchange) }
	}
}

fun extractToken(serverExchange: ServerWebExchange): Exchange {
	return Exchange(serverExchange.request.headers["Authorization"]?.get(0))
}


data class Exchange(val token : String?)