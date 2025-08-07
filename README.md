# Notification-test-backend

This is a notification service developed with Spring Boot that allows creating notifications and listing the logs of sent notifications.

## Technologies Used

- Java 17  
- Spring Boot 3.5.4  
- Spring Data JPA  
- PostgreSQL (via Docker)  
- Lombok  
- MapStruct  
- Liquibase (for database migrations)  

## Installation and Execution

### Prerequisites

- Java 17 JDK installed  
- Maven installed  
- Docker installed (for the database)  

### Steps to Run

1. **Start the PostgreSQL database using Docker:**

```bash
docker-compose up -d
```

2. **Install dependencies and build the project:**

```bash
mvn clean install
```

3. **Run the Spring Boot application:**

```bash
mvn spring-boot:run
```

Or run the main class `NotificationApplication` directly from your IDE.

## Database

The PostgreSQL database is provisioned using Docker. Below are the system tables:

### Table: `user`

| Field         | Type         | Description                            |
|---------------|--------------|----------------------------------------|
| id            | SERIAL       | Auto-increment primary key             |
| name          | VARCHAR(100) | User's name (not null)                 |
| email         | VARCHAR(150) | User's email (unique, not null)        |
| phone_number  | VARCHAR(20)  | User's phone number                    |

### Table: `categories`

| Field | Type         | Description               |
|-------|--------------|---------------------------|
| id    | SERIAL       | Auto-increment primary key |
| name  | VARCHAR(100) | Category name (not null)   |

### Table: `notification_types`

| Field | Type         | Description                   |
|-------|--------------|-------------------------------|
| id    | SERIAL       | Auto-increment primary key     |
| name  | VARCHAR(100) | Notification type (not null)   |

### Table: `subscription`

| Field       | Type | Description                                       |
|-------------|------|---------------------------------------------------|
| id          | SERIAL | Auto-increment primary key                     |
| user_id     | INT    | Foreign key to `user` table (not null)         |
| category_id | INT    | Foreign key to `categories` table (not null)   |

### Table: `channels`

| Field               | Type | Description                                         |
|---------------------|------|-----------------------------------------------------|
| id                  | SERIAL | Auto-increment primary key                     |
| user_id             | INT    | Foreign key to `user` table (not null)           |
| notification_type_id| INT    | Foreign key to `notification_types` (not null)   |

### Table: `log_history`

| Field               | Type      | Description                                         |
|---------------------|-----------|-----------------------------------------------------|
| id                  | SERIAL    | Auto-increment primary key                          |
| user_id             | INT       | Foreign key to `user` table (not null)              |
| category_id         | INT       | Foreign key to `categories` table (not null)        |
| notification_type_id| INT       | Foreign key to `notification_types` table (not null)|
| message             | TEXT      | Notification message (not null)                     |
| sent_timestamp      | TIMESTAMP | Timestamp of sending (default: CURRENT_TIMESTAMP)   |

## API Endpoints

### 1. Health Check

Checks if the application is running.

- **URL:** `/healthCheck`  
- **Method:** `GET`  
- **Response:**  
```
OK
```

### 2. List Categories

Returns all available categories.

- **URL:** `/category`  
- **Method:** `GET`  
- **Response:**  
```json
[
  { "id": 1, "name": "Sports" },
  { "id": 2, "name": "Finance" },
  { "id": 3, "name": "Movies" }
]
```

### 3. Create Notification

Creates a new notification for users subscribed to the specified category.

- **URL:** `/notification/create`  
- **Method:** `POST`  
- **Request Body:**  
```json
{
  "categoryId": 2,
  "message": "Test Finance category"
}
```  
- **Response:**  
```
ok
```

### 4. Notification History

Returns the history of sent notifications.

- **URL:** `/notification/history`  
- **Method:** `GET`  
- **Response:**  
```json
[
  {
    "id": 17,
    "user": {
      "name": "user name",
      "email": "test@example.com",
      "phoneNumber": "+55 35 99432-1678"
    },
    "category": { "name": "Finance" },
    "notificationType": { "name": "E-Mail" },
    "message": "Test",
    "sentTimestamp": "2025-08-06T01:33:44.544435"
  },
  {
    "id": 16,
    "user": {
      "name": "user name",
      "email": "test@example.com",
      "phoneNumber": "+55 35 99432-1678"
    },
    "category": { "name": "Finance" },
    "notificationType": { "name": "SMS" },
    "message": "Test ",
    "sentTimestamp": "2025-08-06T01:33:44.544435"
  }
]
```
