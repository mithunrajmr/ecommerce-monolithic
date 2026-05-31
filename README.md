# 🛒 E-Commerce Monolithic Application

A Spring Boot **monolithic e-commerce application** built with Product, Customer, and Order modules inside one single project.

This project demonstrates CRUD operations, layered architecture, DTO usage, validation, exception handling, MySQL database integration, Swagger API documentation, and Postman testing.

---

## 📌 Project Overview

This is a **monolithic application**, which means all business modules are developed inside one Spring Boot project.

The application contains three main modules:

- **Product Module** - manages product details
- **Customer Module** - manages customer details
- **Order Module** - manages customer orders

Unlike microservices, this project has:

- One Spring Boot application
- One port
- One database
- One deployable unit

---

## 🧱 Architecture

```text
ecommerce-monolithic/
│
├── src/main/java/com/ecommerce/monolith
│   │
│   ├── controller
│   │   ├── ProductController.java
│   │   ├── CustomerController.java
│   │   └── OrderController.java
│   │
│   ├── dto
│   │   ├── ProductDto.java
│   │   ├── CustomerDto.java
│   │   └── OrderDto.java
│   │
│   ├── entity
│   │   ├── Product.java
│   │   ├── Customer.java
│   │   └── CustomerOrder.java
│   │
│   ├── repository
│   │   ├── ProductRepository.java
│   │   ├── CustomerRepository.java
│   │   └── OrderRepository.java
│   │
│   ├── service
│   │   ├── ProductService.java
│   │   ├── ProductServiceImpl.java
│   │   ├── CustomerService.java
│   │   ├── CustomerServiceImpl.java
│   │   ├── OrderService.java
│   │   └── OrderServiceImpl.java
│   │
│   └── exception
│       ├── ResourceNotFoundException.java
│       └── GlobalExceptionHandler.java
│
└── src/main/resources/application.properties
```

---

## 🔁 Request Flow

```text
Client / Postman / Swagger
          |
          v
Controller Layer
          |
          v
Service Layer
          |
          v
Repository Layer
          |
          v
MySQL Database
```

All modules communicate internally inside the same application. No RestTemplate or API Gateway is used in this monolithic project.

---

## 🛠️ Technologies Used

- Java 17 / Java 21 compatible
- Spring Boot 3.5.14
- Spring Web
- Spring Data JPA
- MySQL
- Jakarta Validation
- Swagger / OpenAPI
- Maven
- STS 4
- Postman
- GitHub

---

## 🗄️ Database

Create the database in MySQL before running the application:

```sql
CREATE DATABASE ecommerce_monolithic_db;
```

Database used:

```text
ecommerce_monolithic_db
```

---

## 🚀 Application Port

| Application | Port | Base URL |
|---|---:|---|
| E-Commerce Monolithic App | 8084 | http://localhost:8084 |

---

## ▶️ How to Run

1. Create the MySQL database:

```sql
CREATE DATABASE ecommerce_monolithic_db;
```

2. Update MySQL username and password in:

```text
src/main/resources/application.properties
```

3. Run the application:

```text
Run As → Spring Boot App
```

4. Application starts on:

```text
http://localhost:8084
```

---

## ⚙️ Application Properties

```properties
spring.application.name=ecommerce-monolithic

server.port=8084

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_monolithic_db
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false
```

If MySQL has no password, use:

```properties
spring.datasource.password=
```

---

## 📘 Swagger URL

After running the application, open:

```text
http://localhost:8084/swagger-ui/index.html
```

Swagger can be used to test Product, Customer, and Order APIs directly from the browser.

---

## 📮 API Endpoints

## Product APIs

Base URL:

```text
http://localhost:8084/api/products
```

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/products` | Create product |
| GET | `/api/products` | Get all products |
| GET | `/api/products/{productId}` | Get product by ID |
| PUT | `/api/products/{productId}` | Update product |
| DELETE | `/api/products/{productId}` | Delete product |

Sample Create Product Request:

```json
{
  "productName": "Laptop",
  "description": "Dell laptop",
  "price": 55000,
  "quantity": 10
}
```

---

## Customer APIs

Base URL:

```text
http://localhost:8084/api/customers
```

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/customers` | Create customer |
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{customerId}` | Get customer by ID |
| PUT | `/api/customers/{customerId}` | Update customer |
| DELETE | `/api/customers/{customerId}` | Delete customer |

Sample Create Customer Request:

```json
{
  "name": "Mithun",
  "email": "mithun@gmail.com",
  "phone": "9876543210",
  "address": "Bengaluru"
}
```

---

## Order APIs

Base URL:

```text
http://localhost:8084/api/orders
```

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/orders` | Create order |
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/{orderId}` | Get order by ID |
| GET | `/api/orders/customer/{customerId}` | Get orders by customer ID |
| PUT | `/api/orders/{orderId}` | Update order |
| DELETE | `/api/orders/{orderId}` | Delete order |

Sample Create Order Request:

```json
{
  "customerId": 1,
  "productId": 1,
  "quantity": 2
}
```

Expected response includes calculated total amount:

```json
{
  "orderId": 1,
  "customerId": 1,
  "productId": 1,
  "quantity": 2,
  "totalAmount": 110000.0,
  "orderStatus": "PLACED",
  "orderDate": "2026-05-31T..."
}
```

---

## ✅ Testing Flow

Use this order while testing in Postman or Swagger:

```text
1. Create product
2. Create customer
3. Create order using productId and customerId
4. Get all products
5. Get all customers
6. Get all orders
7. Get orders by customer ID
```

---

## 🧪 Negative Test Cases

### Create Order With Invalid Customer ID

```json
{
  "customerId": 999,
  "productId": 1,
  "quantity": 2
}
```

Expected response:

```text
Customer not found with id: 999
```

---

### Create Order With Invalid Product ID

```json
{
  "customerId": 1,
  "productId": 999,
  "quantity": 2
}
```

Expected response:

```text
Product not found with id: 999
```

---

### Create Order With Quantity More Than Stock

```json
{
  "customerId": 1,
  "productId": 1,
  "quantity": 100
}
```

Expected response:

```text
Insufficient product quantity
```

---

## 🧠 Key Concepts Demonstrated

- Monolithic architecture
- Single Spring Boot application
- Single database
- Single application port
- CRUD operations
- Layered architecture
- DTO and Entity separation
- Repository pattern
- Service layer business logic
- Order total calculation
- Product stock reduction after order placement
- Exception handling using `@RestControllerAdvice`
- Validation using Jakarta Validation
- MySQL database integration
- Swagger API documentation
- Postman testing
- GitHub version control

---

## 📌 Why This Is Monolithic

This project is monolithic because Product, Customer, and Order modules are inside one single Spring Boot application.

```text
One project
One port
One database
One deployable application
```

There is no service-to-service communication because all modules are part of the same application.

---

## 🔍 Monolithic vs Microservices

### Monolithic Application

```text
Product, Customer, and Order are inside one project.
They share one database and one port.
Internal modules call each other directly through service/repository classes.
```

### Microservices Application

```text
Each module is a separate Spring Boot application.
Each service has its own database and port.
Services communicate using REST APIs, RestTemplate, Feign, or messaging.
```

---

## 🧾 Simple Viva Explanation

```text
I created an E-Commerce Monolithic Application using Spring Boot.
It has Product, Customer, and Order modules inside one single application.
The application runs on port 8084 and uses one MySQL database.
Product, Customer, and Order have CRUD operations.
When creating an order, the application checks whether the customer and product exist, calculates the total amount, reduces product quantity, and saves the order.
Swagger and Postman were used to test the APIs.
```

---

## 👨‍💻 Author

**Mithun Raj M R**

---

## 📄 Note

This project was created as a monolithic Spring Boot assignment to demonstrate how multiple modules can exist inside one single application.
