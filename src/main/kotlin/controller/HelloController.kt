package es.unizar.webeng.hello.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import java.time.LocalTime


object GreetingHelper {
    fun getTimeBasedGreeting(messageSource: MessageSource): String {
        val now = LocalTime.now()
        val locale = LocaleContextHolder.getLocale()
        val key = when (now.hour) {
            in 6..11 -> "greeting.morning"
            in 12..19 -> "greeting.afternoon"
            in 20..22 -> "greeting.evening"
            else -> "greeting.night"
        }
        return messageSource.getMessage(key, null, locale)
    }
}

/**
 * Main controller for the web interface.
 */
@Controller
class HelloController(
    @param:Value("\${app.message:Hello World}") 
    private val message: String,
    private val messageSource: MessageSource
) {
    @GetMapping("/")
    fun welcome(
        model: Model,
        @RequestParam(defaultValue = "") name: String
    ): String {
        val timeBasedGreeting = GreetingHelper.getTimeBasedGreeting(messageSource)
        val greeting = if (name.isNotBlank()) "$timeBasedGreeting, $name!" else "$timeBasedGreeting!"
        model.addAttribute("message", greeting)
        model.addAttribute("name", name)
        return "welcome"
    }
}

@RestController
class HelloApiController(
    private val messageSource: MessageSource
) {
    companion object {
        // Simple in-memory history to store generated greetings
        private val history = mutableListOf<String>()
    }

    @GetMapping("/api/hello", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun helloApi(@RequestParam(defaultValue = "World") name: String): Map<String, String> {
        val timeBasedGreeting = GreetingHelper.getTimeBasedGreeting(messageSource)
        val fullGreeting = "$timeBasedGreeting, $name!"
        history.add(fullGreeting)
        return mapOf(
            "message" to fullGreeting,
            "timestamp" to java.time.Instant.now().toString()
        )
    }

    @GetMapping("/api/history", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun historyApi(): List<String> = history.toList()
}
