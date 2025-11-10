# Lab 1 Git Race -- Project Report

## Description of Changes

### Feature 1: Dynamic Greeting
- Implemented time-based greeting in `HelloController.kt` (morning/afternoon/evening/late night).
- Added unit, MVC, and integration tests using regex to cover all time ranges.
- Verified with `./gradlew test` → all tests passed.

### Feature 2: Internationalization (i18n)
- Added message bundles: `messages.properties` (EN) and `messages_es.properties` (ES).
- Configured `application.properties` with `spring.messages.basename=messages`.
- Implemented `LocaleConfig.kt` with `SessionLocaleResolver` and `LocaleChangeInterceptor` (`?lang=xx` parameter).
- Updated controllers to fetch messages from `MessageSource` and templates with `th:text="#{...}"`.
- **Current limitation:** locale switching works (debug shows correct locale), but greetings and titles remain static (always Spanish).  
- Documented as a **partial implementation**.

### Feature 3: Greeting History
- Extended `HelloApiController` with a simple in-memory `history` list.
- Every call to `/api/hello` stores the generated greeting in history.
- Added new endpoint `/api/history` returning all greetings in JSON.
- Integration test ensures:
  - Multiple greetings are stored after consecutive `/api/hello` requests.
  - `/api/history` returns them correctly.
- Verified with both manual `curl` requests and automated integration tests.

---

## Technical Decisions
- **Kotlin & Spring Boot**: followed project template with minimalistic approach.
- **Testing**: integration tests preferred to validate feature behavior end-to-end.
- **i18n**: implemented with standard Spring `MessageSource` but kept partial due to unresolved template/API binding issue.
- **History implementation**: opted for simplest approach (static list inside controller) instead of service injection, as acceptable for this lab context.

---

## Learning Outcomes
- Understood branch workflow with `origin` (fork) and `upstream` (teacher repo).
- Practiced implementing features incrementally and validating with unit/MVC/integration tests.
- Learned Spring Boot i18n configuration (locale resolver, interceptors, message bundles).
- Reinforced debugging practices (logs, curl tests, and test isolation).
- Balanced trade-offs between clean architecture (service layer) and pragmatic simplicity (static list for history).

---


## AI Disclosure
### AI Tools Used
- ChatGPT (for explanations, guidance, code skeletons).

### AI-Assisted Work
- Guidance for structuring `REPORT.md`.
- Suggestions for controller logic and i18n configuration.
- Advice on Git workflow and Docker usage.
- Approx. 30% AI-assisted, 70% manual implementation and adaptation.
- A lot of help with branchs and git.

### Original Work
- Manual setup of environment, Gradle, Docker, and repo workflow.
- Implemented and debugged code locally.
- Ran and validated all tests and application behavior.
- Developed understanding of i18n, REST controllers, and integration testing by actively modifying and testing the code.