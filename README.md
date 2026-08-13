# ⚡ PulseFit Engine

> High-Performance Telemetry Intake & Real-Time LLM Athletic Recovery Intelligence Engine

PulseFit Engine is a backend microservice built with **Spring Boot 3** and **Java 21 Virtual Threads**, engineered to handle high-throughput workout telemetry intake and deliver instant, personalized athletic recovery advice powered by **Groq Cloud LLMs (Llama 3.3)** and local **Ollama** fallbacks.

---

## 🌐 Live Services

* **Live API Base URL:** `https://pulsefit-backend-joej.onrender.com`
* **Interactive Swagger Docs:** [https://pulsefit-backend-joej.onrender.com/swagger-ui.html](https://pulsefit-backend-joej.onrender.com/swagger-ui.html)

---

## 🏗️ Architecture & Key Features

* **Java 21 Virtual Threads (Project Loom):** Handles thousands of concurrent telemetry ingestion requests with lightweight thread management and minimal memory overhead.
* **Hybrid LLM Recovery Intelligence:**
  * **Production:** Powered by Groq Cloud API (`llama-3.3-70b-versatile`) for sub-second AI inference.
  * **Development Fallback:** Seamlessly connects to local Ollama (`llama3.2`) when running offline.
* **PostgreSQL Database:** Managed relational storage hosted on Render for user telemetry and workout records.
* **OpenAPI 3.0 / Swagger UI:** Auto-generated interactive API documentation for live testing and external integration.

---

## 🛠️ Tech Stack

* **Language/Framework:** Java 21 / Spring Boot 3.x
* **AI Orchestration:** Groq Cloud API, Ollama, LangChain4j
* **Database & Persistence:** PostgreSQL, Spring Data JPA / Hibernate
* **Deployment:** Render (Cloud PaaS)
* **Documentation:** Springdoc OpenAPI (Swagger UI)

---

## 📡 API Endpoints Overview

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/v1/telemetry` | Ingest workout telemetry payload & trigger async analytics |
| `GET` | `/api/v1/telemetry` | Retrieve all workout telemetry logs |
| `GET` | `/api/v1/telemetry/user/{email}` | Retrieve workout history for a specific athlete |
| `GET` | `/api/v1/telemetry/ai/recovery/{email}` | Generate real-time AI recovery advice via Groq LLM |
