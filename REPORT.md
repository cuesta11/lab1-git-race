# Lab 1 Git Race -- Project Report

## Description of Changes

### Feature 1: Dynamic Greeting
- Implemented time-based greeting in `HelloController.kt` and `HelloApiController.kt` (morning/afternoon/evening/night).
- Centralized logic in `GreetingHelper` object to avoid code duplication (DRY principle).
- Time ranges: 6-11h (morning), 12-19h (afternoon), 20-22h (evening), 23-5h (night).
- Added unit, MVC, and integration tests using regex to cover all time ranges.
- Verified with `./gradlew test` → all tests passed.

### Feature 2: Internationalization (i18n)
- Added message bundles: `messages.properties` (EN) and `messages_es.properties` (ES).
- Configured `LocaleConfig.kt` with explicit `MessageSource` bean (UTF-8 encoding), `SessionLocaleResolver` (default: English), and `LocaleChangeInterceptor` (`?lang=` parameter).
- Updated controllers to inject `MessageSource` and fetch messages using `LocaleContextHolder.getLocale()`.
- Modified templates with Thymeleaf message expressions: `th:text="#{welcome.title}"`.
- Integrated with dynamic greeting: time-based greetings now change language based on `?lang=en` or `?lang=es`.
- Locale persists in HTTP session.
- Improved time ranges and fixed typo ("Good nigth" → "Good night") from previous implementation.
- Verified with manual browser testing and automated tests → all 12 tests passed.

### Feature 3: Greeting History (API)
- Added in-memory history (static list) in `HelloApiController`.
- Each call to `/api/hello` stores the full dynamic + i18n greeting (e.g. "Good morning, Ana!" / "Buenos días, Ana!").
- New endpoint `/api/history` returns the accumulated greetings (snapshot copy with `toList()`).
- Added integration test: performs two `/api/hello` calls and asserts both names appear in `/api/history` response.
- Kept intentionally simple (no persistence, no size limit) per lab scope.

---

## Technical Decisions
- **Kotlin & Spring Boot**: followed project template with clean architecture.
- **DRY principle**: `GreetingHelper` object centralizes time-based greeting logic, avoiding duplication between web and API controllers.
- **Spring Boot i18n**: standard `ResourceBundleMessageSource` with explicit bean configuration (auto-configuration wasn't sufficient).
- **Session-based locale**: `SessionLocaleResolver` persists user's language choice across requests.
- **UTF-8 encoding**: essential for Spanish characters (ñ, á, ¡, ¿).
- **Testing**: integration tests validate end-to-end behavior; unit tests mock `MessageSource` with Mockito; MVC tests use regex patterns for flexible assertions.
 - **History storage simplicity**: Chosen `mutableListOf<String>()` inside companion object—acceptable for educational scope (single-instance JVM, no concurrency hazards considered here).
 - **Snapshot return**: `history.toList()` avoids accidental external mutation.
 - **Non-persistent**: No DB—fits lab constraints and keeps focus on web/i18n aspects.

---

## Learning Outcomes
- Understood branch workflow with Git (feature branches, merge strategies, conflict resolution).
- Practiced implementing features incrementally and validating with unit/MVC/integration tests.
- Learned Spring Boot i18n configuration (`MessageSource`, `LocaleResolver`, `LocaleChangeInterceptor`, message bundles).
- Understood the importance of explicit bean configuration when auto-configuration fails.
- Reinforced debugging practices (manual testing, test isolation, analyzing conflicts).
- Applied DRY principle to refactor duplicated code.
- Learned to use Mockito for mocking Spring beans in Kotlin.
- Gained experience with Thymeleaf message expressions and locale handling.

---

## AI Disclosure
### AI Tools Used
- ChatGPT (for explanations, guidance, debugging assistance).

### AI-Assisted Work
- Guidance for structuring `LocaleConfig.kt` and identifying missing `MessageSource` bean.
- Suggestions for Mockito mocking in unit tests.
- Advice on regex patterns for flexible test assertions.
- Guidance on Git workflow (branching, merging, conflict resolution).
- Help with structuring `REPORT.md`.
- Approx. **30% AI-assisted**, **70% manual** implementation and adaptation.

### Original Work
- Manual implementation of all Kotlin code and Spring configuration.
- Decision to extract `GreetingHelper` as shared object (DRY).
- Integration of i18n with existing dynamic greeting feature.
- Running tests, analyzing failures, debugging issues, and iterating on fixes.
- Manual verification in browser with `?lang=es` and `?lang=en`.
- Refactoring duplicated code between controllers.
- Writing comprehensive documentation based on actual implementation.


