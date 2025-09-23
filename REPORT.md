# Lab 1 Git Race -- Project Report

## 1. Description of Changes
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
 

- **Feature 2**: Multi-language support (English and Spanish).
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
