# E-Commerce Monolithic Application

A Spring Boot monolithic application for managing products, customers, and orders in an e-commerce system.

## Overview

This project contains three modules inside a single Spring Boot application:

- Product Management
- Customer Management
- Order Management

All modules run in one application, use one database, and are deployed as one unit.

## Tech Stack

- Java 17+
- Spring Boot 3.5.14
- Spring Web
- Spring Data JPA
- MySQL
- Jakarta Validation
- Swagger/OpenAPI
- Maven

## Project Structure

```text
ecommerce-monolithic
├── src/main/java/com/ecommerce/monolith
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── exception
│   ├── repository
│   └── service
├── src/main/resources
│   └── application.properties
└── pom.xml
```

## Modules

### Product Module

Handles product details such as name, description, price, and available quantity.

### Customer Module

Handles customer details such as name, email, phone, and address.

### Order Module

Handles order placement. While placing an order, the application validates the customer, validates the product, checks product stock, calculates total amount, reduces product quantity, and stores the order.

## Database Setup

Create the database before running the application:

```sql
CREATE DATABASE ecommerce_monolithic_db;
```

Update database credentials in:

```text
src/main/resources/application.properties
```

```properties
spring.datasource.username=root
spring.datasource.password=root
```

## Application Configuration

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

## Running the Application

Run the project as a Spring Boot application.

Application URL:

```text
http://localhost:8084
```

Swagger URL:

```text
http://localhost:8084/swagger-ui/index.html
```

## API Endpoints

### Product APIs

```text
POST   /api/products
GET    /api/products
GET    /api/products/{productId}
PUT    /api/products/{productId}
DELETE /api/products/{productId}
```

Sample request:

```json
{
  "productName": "Laptop",
  "description": "Dell laptop",
  "price": 55000,
  "quantity": 10
}
```

### Customer APIs

```text
POST   /api/customers
GET    /api/customers
GET    /api/customers/{customerId}
PUT    /api/customers/{customerId}
DELETE /api/customers/{customerId}
```

Sample request:

```json
{
  "name": "Mithun",
  "email": "mithun@gmail.com",
  "phone": "9876543210",
  "address": "Bengaluru"
}
```

### Order APIs

```text
POST   /api/orders
GET    /api/orders
GET    /api/orders/{orderId}
GET    /api/orders/customer/{customerId}
PUT    /api/orders/{orderId}
DELETE /api/orders/{orderId}
```

Sample request:

```json
{
  "customerId": 1,
  "productId": 1,
  "quantity": 2
}
```

## Testing Flow

1. Create a product.
2. Create a customer.
3. Create an order using the created product ID and customer ID.
4. Verify product quantity is reduced after order placement.
5. Test get, update, and delete APIs.

## Key Features

- Product CRUD
- Customer CRUD
- Order CRUD
- Order total calculation
- Product stock update after order placement
- Input validation
- Centralized exception handling
- Swagger API documentation
- MySQL database integration

## Author

Mithun Raj M R
