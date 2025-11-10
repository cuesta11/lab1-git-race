package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.ui.Model
import org.springframework.ui.ExtendedModelMap
import org.springframework.context.MessageSource
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.isNull
import java.util.Locale

class HelloControllerUnitTests {
    private lateinit var controller: HelloController
    private lateinit var apiController: HelloApiController
    private lateinit var model: Model
    private lateinit var messageSource: MessageSource
    
    @BeforeEach
    fun setup() {
        // Create a mock MessageSource
        messageSource = mock(MessageSource::class.java)
        
        // Configure mock to return appropriate greetings
        `when`(messageSource.getMessage(anyString(), isNull(), any(Locale::class.java)))
            .thenAnswer { invocation ->
                val key = invocation.getArgument<String>(0)
                when (key) {
                    "greeting.morning" -> "Good morning"
                    "greeting.afternoon" -> "Good afternoon"
                    "greeting.evening" -> "Good evening"
                    "greeting.night" -> "Good night"
                    else -> "Hello"
                }
            }
        
        controller = HelloController("Test Message", messageSource)
        apiController = HelloApiController(messageSource)
        model = ExtendedModelMap()
    }
    
    @Test
    fun `should return welcome view with default message`() {
        val view = controller.welcome(model, "")
        
        assertThat(view).isEqualTo("welcome")
        val message = model.getAttribute("message") as String
        assertThat(message).matches("^(Good morning|Good afternoon|Good evening|Good night)!$")
        assertThat(model.getAttribute("name")).isEqualTo("")
    }
    
    @Test
    fun `should return welcome view with personalized message`() {
        val view = controller.welcome(model, "Developer")
        
        assertThat(view).isEqualTo("welcome")
        val message = model.getAttribute("message") as String
        assertThat(message).matches("^(Good morning|Good afternoon|Good evening|Good night), Developer!$")
        assertThat(model.getAttribute("name")).isEqualTo("Developer")
    }
    
    @Test
    fun `should return API response with timestamp`() {
        val response = apiController.helloApi("Test")
        
        assertThat(response).containsKey("message")
        assertThat(response).containsKey("timestamp")
        
        val message = response["message"]!!
        assertThat(message).matches("^(Good morning|Good afternoon|Good evening|Good night), Test!$")
        assertThat(response["timestamp"]).isNotNull()
    }
}
