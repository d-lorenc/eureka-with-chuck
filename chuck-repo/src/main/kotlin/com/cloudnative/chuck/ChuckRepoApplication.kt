package com.cloudnative.chuck

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@SpringBootApplication
@EnableDiscoveryClient
class ChuckRepoApplication

fun main(args: Array<String>) {
    SpringApplication.run(ChuckRepoApplication::class.java, *args)
}

val random = Random()
val jokes = arrayOf(
        "Chuck Norris can kill two stones with one bird.",
        "Chuck Norris counted to infinity. Twice.",
        "Chuck Norris can hear sign language."
)

@RestController
class ChuckController(@Value("\${vcap.application.instance_index}") val appId: String) {
    @GetMapping("/jokes/random")
    fun joke() = JokeResponse(
            joke = jokes[random.nextInt(jokes.size)],
            appId = appId
    )
}

data class JokeResponse(val joke: String, val appId: String)
