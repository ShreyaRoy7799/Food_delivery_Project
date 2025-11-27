# 🍕 Food Delivery Application

A full-stack Food Delivery web application using Spring Boot and Angular.

The Food Delivery Application is a full-stack web project with an Angular frontend for a modern and responsive user experience, and a Spring Boot backend managing REST APIs and business logic. It uses MySQL as the database to store user, restaurant, menu, and order data. The app features user authentication, dynamic restaurant and menu listings, order processing, and admin management features to deliver a smooth online food ordering experience.

<img width="1898" height="890" alt="Screenshot 2025-11-26 172438" src="https://github.com/user-attachments/assets/5c167dbc-b4d0-4f45-8d3a-413f7989de8f" />

<img width="1901" height="894" alt="Screenshot 2025-11-26 172509" src="https://github.com/user-attachments/assets/ce17ae3c-7325-4c64-9be9-3ebcc334a454" />

<img width="1911" height="901" alt="Screenshot 2025-11-26 171011" src="https://github.com/user-attachments/assets/0f885a70-b25d-41d0-8515-3bed06d35ed6" />

<img width="1872" height="884" alt="Screenshot 2025-11-26 171036" src="https://github.com/user-attachments/assets/307a5716-12b0-463b-b25c-8cfdb7e413f5" />

<img width="1909" height="883" alt="Screenshot 2025-11-26 171111" src="https://github.com/user-attachments/assets/5da42b6d-29c4-42d5-8c6d-1ffb4a8b804a" />

<img width="1908" height="896" alt="Screenshot 2025-11-26 171137" src="https://github.com/user-attachments/assets/ee1f4c53-b215-460c-b3df-9f82e0cda8ae" />

<img width="1891" height="892" alt="Screenshot 2025-11-26 171159" src="https://github.com/user-attachments/assets/6a526d5f-4778-412a-80fe-9a46a6928865" />

<img width="1896" height="882" alt="Screenshot 2025-11-26 172045" src="https://github.com/user-attachments/assets/aea83bd6-c664-40b7-8728-96e8c131c3fb" />

<img width="1867" height="878" alt="Screenshot 2025-11-26 172135" src="https://github.com/user-attachments/assets/bacd3df7-b47f-451c-b382-47d9bf2f9f6d" />

<img width="1895" height="945" alt="Screenshot 2025-11-26 172209" src="https://github.com/user-attachments/assets/e7e64862-3432-49ae-9d51-4264dfb9df95" />

<img width="1867" height="872" alt="Screenshot 2025-11-26 172230" src="https://github.com/user-attachments/assets/c9bf835d-35e2-4e24-abe2-e49e0a5cfb1f" />

<img width="1863" height="870" alt="Screenshot 2025-11-26 172246" src="https://github.com/user-attachments/assets/b7035f3a-8bd4-4b83-aa9a-8c4ea8a97c8a" />

<img width="1906" height="893" alt="Screenshot 2025-11-26 172351" src="https://github.com/user-attachments/assets/f2a5c34e-fec8-4eee-83af-60c4d176e9dd" />

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
