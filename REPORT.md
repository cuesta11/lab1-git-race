# Lab 1 Git Race -- Project Report

## 1. Description of Changes

### Feature 1: Dynamic Greeting by Time of Day
**Description of Changes**  
- Modified `HelloController.kt` and `HelloApiController.kt` to include a method `getTimeBasedGreeting()` that selects the appropriate greeting based on the current hour.  
- Updated the web and API endpoints to return greetings such as “Good morning”, “Good afternoon”, “Good evening”, or “Good night”.  
- Adjusted `welcome.html` to display the dynamic greeting.  
- Updated unit, MVC, and integration tests to validate the new behavior using regex patterns.  

**Technical Decisions**  
- Used `java.time.LocalTime` to determine the hour of the day.  
- Chose four time ranges:  
  - 03:00–12:00 → Good morning  
  - 13:00–19:00 → Good afternoon  
  - 20:00–22:00 → Good evening  
  - Others → Good night  
- Tests were updated to validate multiple possible outcomes, ensuring robustness across different times of execution.  

**Learning Outcomes**  
- Learned how to extend a Spring Boot + Kotlin controller with new logic.  
- Practiced writing flexible tests (regex) for non-deterministic outputs.  
- Understood how to modify MVC and integration tests consistently with new business logic.
 

### Feature 2: Internationalization (i18n) with `?lang` parameter
  
Enable English/Spanish localization for:
1) the dynamic greeting (web + API), and  
2) the page title on the welcome view,  
switchable via the URL parameter `?lang=<en|es>`.

**Description of changes**
- **Message bundles**
  - `src/main/resources/messages.properties` (English, default)  
    - `greeting.morning=Good morning`, `greeting.afternoon=Good afternoon`, `greeting.evening=Good evening`, `greeting.night=Good night`, `welcome.title=Welcome to the Modern Web App!`
  - `src/main/resources/messages_es.properties` (Spanish)  
    - `greeting.morning=Buenos días`, `greeting.afternoon=Buenas tardes`, `greeting.evening=Buenas noches`, `greeting.night=Buenas noches`, `welcome.title=¡Bienvenido a la Aplicación Web Moderna!`
- **Spring configuration**
  - `application.properties`:  
    `spring.messages.basename=messages`  
    `spring.messages.encoding=UTF-8`
  - `LocaleConfig.kt`: `SessionLocaleResolver` (default `Locale.ENGLISH`) + `LocaleChangeInterceptor` with `paramName=lang` to honor `?lang=es|en`.
- **Controllers**
  - `HelloController.kt` / inner `HelloApiController`: inject `MessageSource` and resolve the greeting via  
    `messageSource.getMessage(key, null, LocaleContextHolder.getLocale())`,  
    where `key` is chosen by hour (`greeting.morning|afternoon|evening|night`).
- **View**
  - `welcome.html`: use Thymeleaf message lookup for the title:  
    `<h1 th:text="#{welcome.title}">...</h1>`  
  - Added a temporary debug line to display the effective locale:  
    `[debug locale: <span th:text="${#locale}">en</span>]`.

**What works**
- The `LocaleChangeInterceptor` is registered and active.  
- The effective locale **does change** with `?lang=en` / `?lang=es` (confirmed by the debug `[debug locale: en|es]`).  
- API and web greeting are produced through `MessageSource` using the current locale.

**What we attempted but did not succeed**
- Despite the locale switching and the i18n setup, the **rendered page title and greeting text remain in Spanish** even when `[debug locale: en]` is shown and `messages.properties` (EN) is the default bundle.
- We validated:
  - bundles exist and keys match,
  - template uses `th:text="#{welcome.title}"`,
  - controllers call `MessageSource` with `LocaleContextHolder.getLocale()`,
  - cache cleared and clean rebuilds (`./gradlew clean bootRun`),
  - fresh incognito sessions to avoid `SessionLocaleResolver` stickiness.
- Conclusion for this iteration: **locale flips correctly, but message resolution still returns the Spanish variant in the rendered view/API**. We leave the locale debug visible (`[debug locale: en|es]`) to prove the parameter is honored, even though the title text itself is not switching.
 

- **Feature 3**: Greeting history stored in memory and exposed via REST + optional web page.
- Updated `README.md` with new endpoints and features.
- Added KDoc documentation to controllers and services.

## 2. Technical Decisions
- **Dynamic greeting**: Implemented in `HelloController.kt` using system time.
- **Multi-language**: Used Spring Boot i18n with `messages.properties` + `messages_es.properties`.
- **Greeting history**: Stored in a simple in-memory list (no database, to keep it lightweight).
- **Testing**: Verified with existing JUnit tests + added small new tests.
- **Docker**: Application also tested inside `docker-compose.dev.yml`.

## 3. Learning Outcomes
- Understood how to extend a Kotlin + Spring Boot application.
- Learned how to add i18n support in Spring Boot.
- Gained experience with REST API endpoints and Thymeleaf templates.
- Improved Git workflow with branches and clean commit history.
- Practiced documenting features in Markdown.

## 4. AI Disclosure
### AI Tools Used
- ChatGPT (for explanations, guidance, code skeletons).
  
### AI-Assisted Work
- Guidance for structuring `REPORT.md`.
- Suggestions for controller logic and i18n configuration.
- Advice on Git workflow and Docker usage.
- Approx. 30% AI-assisted, 70% manual implementation and adaptation.

### Original Work
- Implementation of features in Kotlin and Spring Boot.
- Manual testing and debugging.
- Writing documentation and adapting AI suggestions to project requirements.
