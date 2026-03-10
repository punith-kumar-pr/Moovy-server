# API Documentation

## Overview
This document provides a comprehensive description of the API, including all endpoints, authentication details, role-based access control, request/response examples, and setup instructions.

## Endpoints
- `POST /api/v1/login`: Authenticate user and retrieve a token.
- `GET /api/v1/users`: Retrieve a list of users. Requires admin role.
- `GET /api/v1/users/{id}`: Retrieve a single user by ID.
- `PUT /api/v1/users/{id}`: Update user details. Requires admin role.

## Authentication
- Uses JWT (JSON Web Token) for authentication.
- Include the token in the `Authorization` header as `Bearer <token>`.

## Role-Based Access Control
- **Admin**: Can access all endpoints.
- **User**: Can access user-specific endpoints.

## Request/Response Examples
### Login Request
```json
{
  "username": "user",
  "password": "pass"
}
```

### Login Response
```json
{
  "token": "jwt_token_here"
}
```

## Setup Instructions
1. Clone the repository.
2. Install dependencies using `npm install`.
3. Set up environment variables in a `.env` file.
4. Run the application using `npm start`.
