# ⚡ PulseFit Engine

A lightweight, asynchronous telemetry ingestion and athletic recovery engine built on Spring Boot 3, Java 21 Virtual Threads, and Groq's LPU infrastructure.

[![Swagger Docs](https://img.shields.io/badge/Swagger-Interactive_UI-brightgreen)](https://pulsefit-backend-joej.onrender.com/swagger-ui.html)
[![Live API](https://img.shields.io/badge/Render-Deployed-blue)](https://pulsefit-backend-joej.onrender.com)
[![Java 21](https://img.shields.io/badge/Java-21_Virtual_Threads-orange)](https://openjdk.org/projects/loom/)

---

## 💡 Why I Built This

Most AI integrations are slow, blocking, or fragile when external APIs drop. I built **PulseFit Engine** to explore two core backend engineering challenges:

1. **Handling high-concurrency intake without thread starvation:** Leveraging Java 21's Virtual Threads (Project Loom) to process incoming workout telemetry concurrently without traditional OS thread overhead.
2. **Resilient Hybrid LLM Pipeline:** Serving sub-second AI recovery insights using Groq Cloud (`llama-3.3-70b`) in production while gracefully falling back to local Ollama instances when offline or rate-limited.

---

## ⚡ Try It Live

You can test the entire pipeline directly in your browser without cloning or running Postman:

👉 **[Open Interactive Swagger UI](https://pulsefit-backend-joej.onrender.com/swagger-ui.html)**

### Quick Test Flow in Swagger:
1. **Ingest Telemetry:** Send a `POST` to `/api/v1/telemetry` with your workout details.
2. **Fetch Log History:** Query `GET` `/api/v1/telemetry/user/{email}` to confirm PostgreSQL persistence.
3. **Trigger AI Recovery:** Fire `GET` `/api/v1/telemetry/ai/recovery/{email}` to watch Groq parse your workout metrics and stream personalized recovery instructions in under 500ms.

---

## ⚙️ Key Technical Highlights

* **Java 21 Virtual Threads:** Swapped classic thread pools for lightweight virtual threads to handle high throughput telemetry intake with minimal RAM usage.
* **Sub-Second LLM Inference:** Powered by Groq LPUs (`llama-3.3-70b-versatile`), delivering tailored recovery plans in ~200–500ms (10x faster than standard cloud LLM endpoints).
* **Fault-Tolerant AI Engine:** Built-in fallback architecture handles offline state gracefully without crashing the core telemetry ingestion pipeline.
* **Auto-Documented OpenAPI 3.0:** Full request/response schema validation and mock payload generators integrated via Springdoc.

---

## 🛠️ Tech Stack

* **Core Engine:** Java 21, Spring Boot 3.x
* **Concurrency:** Project Loom (Virtual Threads)
* **AI & LLM Orchestration:** Groq Cloud LPU API, LangChain4j, Ollama (Local)
* **Data Layer:** PostgreSQL, Spring Data JPA / Hibernate
* **API Spec & Deployment:** Swagger / OpenAPI 3.0, Render PaaS

---

## 📡 API Reference

| Endpoint | Method | Description |
| :--- | :--- | :--- |
| `/api/v1/telemetry` | `POST` | Ingest raw workout payload & trigger async analytics |
| `/api/v1/telemetry` | `GET` | Retrieve all system-wide telemetry logs |
| `/api/v1/telemetry/user/{email}` | `GET` | Fetch workout history for a specific athlete |
| `/api/v1/telemetry/ai/recovery/{email}` | `GET` | Generate live LLM recovery plan based on recent workouts |

---

## 🚀 Running Locally

```bash
# 1. Clone repo
git clone [https://github.com/](https://github.com/)<YOUR_GITHUB_USERNAME>/pulse-fit.git
cd pulse-fit

# 2. Set environment variables
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/pulsefit_db
export GROQ_API_KEY=your_groq_key_here

# 3. Run application
mvn clean spring-boot:run