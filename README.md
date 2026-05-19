\# Banking App - Spring Boot REST API



A full-featured Banking REST API built with Spring Boot, JWT Authentication, and MySQL.



\## Tech Stack

\- Java 17

\- Spring Boot 3.2

\- Spring Security + JWT

\- Spring Data JPA + MySQL

\- Swagger UI (OpenAPI 3)

\- JUnit + Mockito



\## Features

\- User Registration \& Login with JWT Authentication

\- BCrypt Password Hashing

\- Create, Read, Update, Delete Bank Accounts

\- Deposit \& Withdraw Money

\- Transfer Money Between Accounts

\- Input Validation \& Global Exception Handling

\- API Documentation with Swagger UI



\## How to Run

1\. Clone the repository

2\. Create MySQL database named `bankingapp`

3\. Copy `application.properties.example` to `application.properties`

4\. Add your database credentials

5\. Run `BankingappApplication.java`

6\. Open `http://localhost:8080/swagger-ui/index.html`



\## API Endpoints

| Method | URL | Description |

|--------|-----|-------------|

| POST | /auth/register | Register new user |

| POST | /auth/login | Login and get JWT token |

| GET | /accounts | Get all accounts |

| GET | /accounts/{id} | Get account by ID |

| POST | /accounts/create | Create new account |

| PUT | /accounts/{id}/deposit/{amount} | Deposit money |

| PUT | /accounts/{id}/withdraw/{amount} | Withdraw money |

| DELETE | /accounts/{id} | Delete account |

| POST | /accounts/transfer | Transfer money |

