# Lab 1 Git Race -- Project Report

### Feature 2: Internacionalización (i18n)

Permite cambiar entre inglés y español mediante el parámetro URL `?lang=<en|es>`.

**Implementación**:

1. **Archivos de mensajes**:
   - `messages.properties` (inglés): `greeting.morning=Good morning`, `welcome.title=Welcome to the Modern Web App!`
   - `messages_es.properties` (español): `greeting.morning=Buenos días`, `welcome.title=¡Bienvenido a la Aplicación Web Moderna!`

2. **Configuración Spring** (`LocaleConfig.kt`):
   - `MessageSource` bean con UTF-8 encoding
   - `SessionLocaleResolver` (locale por defecto: inglés)
   - `LocaleChangeInterceptor` con parámetro `lang`

3. **Controladores**:
   - Inyectan `MessageSource` y usan `LocaleContextHolder.getLocale()`
   - `GreetingHelper` centraliza la lógica (DRY)

4. **Vista**:
   - Título internacionalizado: `<h1 th:text="#{welcome.title}">...</h1>`
   - Línea debug para verificar locale actual

**Funcionamiento**:
- `/` → Inglés (default)
- `/?lang=es` → Español
- `/?lang=en` → Inglés
- El locale persiste en sesión

**Archivos modificados**:
- `src/main/kotlin/es/unizar/webeng/hello/config/LocaleConfig.kt` (NEW)
- `src/main/kotlin/controller/HelloController.kt`
- `src/main/resources/messages*.properties` (NEW)
- `src/main/resources/templates/welcome.html`
- Todos los tests actualizados

---

## 2. Decisiones Técnicas

- **Patrón DRY**: Helper object `GreetingHelper` elimina duplicación de código
- **Spring Boot i18n estándar**: `ResourceBundleMessageSource` + `LocaleChangeInterceptor`
- **Session-based locale**: Persiste preferencia del usuario
- **UTF-8 encoding**: Soporta caracteres españoles (ñ, á, ¡, ¿)
- **Tests flexibles**: Usan regex para aceptar cualquier saludo válido según hora

---

## 3. Aprendizajes

- Sistema i18n de Spring Boot (`MessageSource`, `LocaleResolver`, interceptores)
- Inyección de dependencias en Kotlin con Spring
- Testing con Mockito para mocks de beans Spring
- Expresiones Thymeleaf para mensajes (`#{key}`)
- Debugging de problemas de configuración (faltaba bean `MessageSource`)
- Refactorización para eliminar código duplicado

---

## 4. Uso de IA

### Herramientas
- ChatGPT (debugging y consultas)

### Contribución estimada: 30% IA

**IA ayudó con**:
- Estructura inicial de `LocaleConfig`
- Identificar que faltaba el bean `MessageSource`
- Sugerencias de mocks con Mockito
- Patrones regex para tests
- Comentarios KDoc
- Redaccion de REPORT.md

**Trabajo original**:
- Implementación completa en Kotlin
- Decisión de usar `GreetingHelper` object
- Testing manual en navegador
- Debugging y fix de tests
- Refactorización de código duplicado
- Documentación técnica completa


