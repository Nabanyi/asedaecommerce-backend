# 🛍️ Ecommerce REST API

## 📌 Overview

This REST API, built with Spring Boot, provides a comprehensive backend solution for an e-commerce platform. It offers endpoints for managing user authentication, product catalogs, order processing, shipping, and customer reviews.

<img src="SwaggerUI-Screenshot.png" alt="Ecommerce API">

## 🚀 Key Features

* **User Authentication:** Secure user registration and login with JWT-based authentication.
* **Product Management:** CRUD operations for products, categories, and brands.
* **Order Processing:** Create, track, and manage customer orders.
* **Shipping Management:** Handle shipping addresses and delivery statuses.
* **Customer Reviews:** Allow customers to leave reviews and ratings for products.
* **Secure Endpoints:** JWT authentication for protected API endpoints.
* **RESTful Design:** Clean and predictable API endpoints for easy integration.

## 🛠️ Tech Stack

* **Spring Boot:** Robust Java framework for building RESTful APIs.
* **Spring Security & JWT:** Secure authentication and authorization.
* **JPA (Hibernate):** Object-Relational Mapping (ORM) for database interactions.
* **[Your Database - e.g., MySQL, PostgreSQL, etc.]:** Relational database for data storage.
* **Gradle/Maven:** Build and dependency management.
* **Swagger/OpenAPI:** API documentation and testing.

## 🔑 Authentication

* The API uses JSON Web Tokens (JWT) for authentication.
* Users must obtain a JWT token through the login endpoint to access protected routes.
* Include the JWT token in the `Authorization` header as a Bearer token (e.g., `Bearer <token>`).

## 📘 API Documentation

* The API documentation is available through Swagger/OpenAPI.
* Access the Swagger UI at `http://localhost:[port]/swagger-ui.html` (replace `8081` with your application's port).

## ⚙️ Installation & Setup

1.  **Clone the Repository:**

    ```bash
    git clone [git@github.com:Nabanyi/asedaecommerce-backend.git]
    cd [asedaecommerce-backend]
    ```

2.  **Configure Database:**

    * Modify the `application.properties` or `application.yml` file to configure your database connection.

3.  **Build and Run:**

    * **Gradle:** `./gradlew bootRun`
    * **Maven:** `mvn spring-boot:run`

4.  **Access API:**

    * The API will be accessible at `http://localhost:8081/api`.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for any improvements or bug fixes.

## 📝 License

MIT License, Apache 2.0

## 🚀 Happy Coding!
