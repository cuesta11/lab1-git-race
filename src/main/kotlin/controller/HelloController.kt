package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalTime

@Controller
class HelloController(
    @param:Value("\${app.message:Hello World}")
    private val message: String
) {

    @GetMapping("/")
    fun welcome(
        model: Model,
        @RequestParam(defaultValue = "") name: String
    ): String {
        val timeBasedGreeting = getTimeBasedGreeting()
        val greeting = if (name.isNotBlank()) "$timeBasedGreeting, $name!" else "$timeBasedGreeting!"
        model.addAttribute("message", greeting)
        model.addAttribute("name", name)
        return "welcome"
    }

    /**
     * Returns a greeting based on the current time of day.
     */
    private fun getTimeBasedGreeting(): String {
        val now = LocalTime.now()
        return when (now.hour) {
            in 3..12 -> "Good morning"
            in 13..19 -> "Good afternoon"
            in 20..22 -> "Good evening"
            else -> "Good night"
        }
    }
}

@RestController
class HelloApiController {

    @GetMapping("/api/hello", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloApi(@RequestParam(defaultValue = "World") name: String): Map<String, String> {
        val timeBasedGreeting = getTimeBasedGreeting()
        return mapOf(
            "message" to "$timeBasedGreeting, $name!",
            "timestamp" to java.time.Instant.now().toString()
        )
    }

    private fun getTimeBasedGreeting(): String {
        val now = LocalTime.now()
        return when (now.hour) {
            in 6..11 -> "Good morning"
            in 12..19 -> "Good afternoon"
            else -> "Good evening"
        }
    }
}
