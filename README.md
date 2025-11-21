# Habit Tracker API

A simple, clean REST-based backend service for tracking personal habits — built with Spring Boot, JPA, and H2 database.

**Live Preview / GitHub Repo**: [SakshiKhandare/habit-tracker](https://github.com/SakshiKhandare/habit-tracker)

---

## Tech Stack

- Java 17 (or higher)  
- Spring Boot (Web, Data JPA)  
- H2 Database (file-based / in-memory)  
- Maven for dependency & build management  
- REST API design with DTOs, Services, Repositories  
- JSON request/response format  

---

## Features

- Create habits
- Get habit by ID
- List all habits
- Filter habits by frequency (DAILY / WEEKLY)
- Pagination support (page, size)
- Update habits (PUT)
- Partial update habits (PATCH)
- Delete habits
- Mark habits as completed or incomplete
- Request validation on inputs
- Global exception handling (Validation + Not Found + Enum errors)
- H2 Database with web console view

---

## API Endpoints

### Create Habit
POST /api/habits

Request Body:
{
  "title": "Read Books",
  "description": "Read 10 pages daily",
  "frequency": "DAILY",
  "startDate": "2025-01-10"
}

Response: 201 Created

---

### Get Habit by ID
GET /api/habits/{id}

Response: 200 OK or 404 Not Found

---

### Get All Habits (with optional filter + pagination)
GET /api/habits?frequency=DAILY&page=0&size=10

Query Params:
frequency = DAILY | WEEKLY
page = default 0
size = default 10

Response: Paged list of habits

---

### Update Habit (Full Update - PUT)
PUT /api/habits/{id}

Body:
{
  "title": "Updated Title",
  "description": "Updated Description",
  "frequency": "WEEKLY",
  "startDate": "2025-02-01"
}

---

### Partial Update Habit (PATCH)
PATCH /api/habits/{id}

Example body:
{
  "title": "Only Title Updated"
}

---

### Delete Habit
DELETE /api/habits/{id}

Response: 204 No Content

---

### Mark Habit Completed
POST /api/habits/{id}/complete

### Mark Habit Incomplete
POST /api/habits/{id}/incomplete

Both return updated habit with "completed": true/false

---

## H2 Database Details

H2 Console URL:
http://localhost:8080/h2-console

Credentials:
JDBC URL: jdbc:h2:file:./data/habitdb
User: sa
Password: (blank)

Example Query:
SELECT * FROM HABIT;

---

## Running the Project

Clone:
git clone https://github.com/SakshiKhandare/habit-tracker.git
cd habit-tracker

Run:
mvn spring-boot:run

---
<img width="1980" height="1210" alt="create habit" src="https://github.com/user-attachments/assets/fd7fb2a2-026c-47e5-a8ca-04ab10b802cb" />
<img width="1988" height="958" alt="create habit - freq required" src="https://github.com/user-attachments/assets/910bdc79-1b06-4338-9c6e-e6c39a7f0760" />
<img width="2030" height="1432" alt="get habit by frequency" src="https://github.com/user-attachments/assets/2a27319c-98ba-4e18-a7ad-366f0e54b3df" />
<img width="1992" height="1484" alt="get all habits" src="https://github.com/user-attachments/assets/e46f39cf-9178-42cc-b835-c5707fcf17f8" />
<img width="2032" height="1168" alt="patch habit" src="https://github.com/user-attachments/assets/8eb36cf0-b7a3-4a07-8334-c717b14dd7d3" />
<img width="2022" height="826" alt="delete habit" src="https://github.com/user-attachments/assets/212d9349-2a51-4ef2-84f7-c1734cf036d9" />

## Author

Sakshi Khandare 

GitHub: https://github.com/SakshiKhandare

