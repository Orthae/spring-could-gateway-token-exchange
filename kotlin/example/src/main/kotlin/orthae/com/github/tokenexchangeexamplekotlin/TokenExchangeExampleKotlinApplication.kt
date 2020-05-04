package orthae.com.github.tokenexchangeexamplekotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@SpringBootApplication
class TokenExchangeExampleKotlinApplication

fun main(args: Array<String>) {
	runApplication<TokenExchangeExampleKotlinApplication>(*args)
}

@RestController
class ExampleController {

	@GetMapping("/example")
	fun example(@RequestHeader(value = "Authorization", required = false) auth: String?): Mono<String> {
		return if (auth != null) Mono.just(auth) else Mono.empty()
	}
}