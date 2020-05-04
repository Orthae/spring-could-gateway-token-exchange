package orthae.com.github.token.exchange.identity.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@SpringBootApplication
class TokenExchangeIdentityKotlinApplication

fun main(args: Array<String>) {
	runApplication<TokenExchangeIdentityKotlinApplication>(*args)
}

@RestController
class ExchangeController {
	@PostMapping("/identity/exchange")
	fun exchange(@RequestBody request: Mono<Exchange?>): Mono<Exchange?> {
		return request.flatMap { mapExchange(it) }
	}

	fun mapExchange(request: Exchange?): Mono<Exchange?> {
		val response : Exchange?
		if (request != null) {
			response = when (request.token) {
				"esEKSc" -> Exchange("pG07Ks")
				"QF6KIz" -> Exchange("EEPoh4")
				"8HHt1t" -> Exchange("SImTSO")
				"BhWimL" -> Exchange("7fIU4n")
				else -> null
			}
			return if(response != null) Mono.just(response) else Mono.empty()
		}
		return Mono.empty()
	}
}

data class Exchange(val token : String?)
