package es.unizar.webeng.hello.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.ui.Model
import org.springframework.ui.ExtendedModelMap

class HelloControllerUnitTests {
    private lateinit var controller: HelloController
    private lateinit var model: Model
    
    @BeforeEach
    fun setup() {
        controller = HelloController("Test Message")
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
    fun `should return welcome view with dynamic greeting`() {
        val view = controller.welcome(model, "Developer")

        assertThat(view).isEqualTo("welcome")
        val message = model.getAttribute("message") as String

        // Check that the message starts with one of the expected greetings
        assertThat(message).matches("^(Good morning|Good afternoon|Good evening|Good night), Developer!$")
        assertThat(model.getAttribute("name")).isEqualTo("Developer")
    }

    
    @Test
    fun `should return API response with dynamic greeting`() {
        val apiController = HelloApiController()
        val response = apiController.helloApi("Test")

        assertThat(response).containsKey("message")
        assertThat(response).containsKey("timestamp")

        val message = response["message"]!!
        assertThat(message).matches("^(Good morning|Good afternoon|Good evening|Good night), Test!$")
    }
}
