# Bajaj Finserv Hiring Challenge: REST API

This repository contains the source code for a REST API developed as a solution for the Bajaj Finserv hiring challenge. The application is built using **Java 21** and the **Spring Boot** framework.

The API exposes a single endpoint that accepts an array of alphanumeric strings and returns a structured JSON object containing user details, categorized data, and calculated values based on the input.

---

## 🚀 API Endpoint

The API has one primary endpoint for processing data.

* [cite_start]**Method:** `POST` [cite: 29]
* [cite_start]**URL:** `/bfhl` [cite: 30]

### Request Body
The endpoint expects a JSON object with a single key, `data`, which holds an array of strings.

**Example Request:**
```json
{
    "data": ["a", "1", "334", "4", "R", "$"]
}
