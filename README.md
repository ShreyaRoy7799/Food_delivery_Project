# 🍕 Food Delivery Application

A full-stack Food Delivery web application using Spring Boot and Angular.

The Food Delivery Application is a full-stack web project with an Angular frontend for a modern and responsive user experience, and a Spring Boot backend managing REST APIs and business logic. It uses MySQL as the database to store user, restaurant, menu, and order data. The app features user authentication, dynamic restaurant and menu listings, order processing, and admin management features to deliver a smooth online food ordering experience.

## ✨ Features

- REST API
- User Registration & Login
- Restaurant & Menu Management
- Cart Management
- Order Placement & History
- Admin Panel
- Responsive UI with Angular
- Role-based access (Admin/User)
- Authentication & Authorization

## 🧰 Technology Stack

### 🔙 Backend

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven

### 🔜 Frontend

- Angular
- Angular CLI
- Bootstrap
- TypeScript

## ▶️ How to Run

> Start the backend server before the frontend client.

### ✅ Backend

1. Install MySQL and create a database named `food_app`
2. Configure database credentials in `src/main/resources/application.properties`
3. Navigate to backend folder:
    ```bash
    cd Food-App-Backend-SpringBoot/food-app-backend
    ```
4. Build and run the backend:
    ```bash
    mvn install
    mvn spring-boot:run
    ```
5. Server will run on: `http://localhost:8080`

### ✅ Frontend

1. Install Node.js and npm
2. Navigate to frontend folder:
    ```bash
    cd Food-App-Frontend-Angular/Food-App-Frontend
    ```
3. Install dependencies:
    ```bash
    npm install
    ```
4. Run the Angular app:
    ```bash
    ng serve
    ```
5. Open in browser: `http://localhost:4200`

> **Note:** The backend API URL is set in `src/environments/environment.ts` (default is `http://localhost:8080/api`)

## 📌 Important Notes

- Spring Boot will auto-load mock data if `import.sql` is provided.
- Ensure backend is up and running before frontend starts.
- Use Postman to test APIs independently.
- JWT token-based authorization is used across the system.