# Moovy - Movie Management Backend

A robust Spring Boot REST API for managing movies, user watchlists, ratings, and reviews with comprehensive authentication and role-based access control.

## 🎯 Overview

Moovy is a movie application similar to IMDb. It allows users to browse, search, and filter movies, as well as manage personal watchlists, mark movies as watched, rate them, and write reviews. Users can also apply advanced filters to discover movies by genre and other criteria.

## ✨ Features

- **User Authentication**: JWT-based authentication with role-based access control
- **Movie Management**: Browse, search, and filter movies by genre
- **Watchlist Management**: Create and manage personal watchlists
- **Watched Movies**: Track movies you've already watched
- **Ratings & Reviews**: Rate and review movies
- **Admin Dashboard**: Manage users, assign roles, and control access
- **Secure API**: All endpoints protected with Spring Security

## 🛠 Technologies Used

- **Backend**: Java 21 with Spring Boot 3.3.2
- **Database**: MySQL 8.0+
- **Authentication**: JWT (JSON Web Tokens)
- **Security**: Spring Security 6
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven
- **Frontend**: Android (Kotlin & Jetpack Compose) [[code repo]](https://github.com/Shashankappu/MovieTime)

## 📋 Prerequisites

- Java 21 or higher
- MySQL 8.0 or higher
- Maven 3.6+
- Git

## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/punith-kumar-pr/Moovy-server.git
cd Moovy-server
```

### 2. Configure Database

Create a MySQL database:

```sql
CREATE DATABASE moovy;
```

Update `application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/moovy
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Configure JWT Secret

Update `application.properties` with a strong JWT secret (minimum 32 characters):

```properties
app.jwtSecret=your-very-long-secret-key-change-this-in-production-at-least-32-chars
app.jwtExpirationInMs=86400000
```

### 4. Build and Run

```bash
mvn clean package
mvn spring-boot:run
```

The server will start at `http://localhost:8080`

## 🔐 Authentication & Authorization

### User Roles

The system supports three roles:

- **ROLE_USER**: Regular user (default role for all new registrations)
- **ROLE_ADMIN**: Full administrative access
- **ROLE_MODERATOR**: Moderation capabilities

### JWT Token Structure

After login/registration, you'll receive a JWT token:

```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "userId": 1,
  "username": "john_doe",
  "email": "john@example.com"
}
```

### Using JWT Token

Include the token in all authenticated requests:

```
Authorization: Bearer <your_jwt_token>
```

---

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

---

## 🔑 Authentication Endpoints

### 1. User Registration

**Endpoint**: `POST /auth/register`  
**Access**: Public  
**Description**: Register a new user account

**Request Body**:
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePassword123",
  "firstName": "John",
  "lastName": "Doe"
}
```

**Response** (200 OK):
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "userId": 1,
  "username": "john_doe",
  "email": "john@example.com"
}
```

**Example cURL**:
```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "SecurePassword123",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

---

### 2. User Login

**Endpoint**: `POST /auth/login`  
**Access**: Public  
**Description**: Login with existing credentials

**Request Body**:
```json
{
  "username": "john_doe",
  "password": "SecurePassword123"
}
```

**Response** (200 OK):
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "userId": 1,
  "username": "john_doe",
  "email": "john@example.com"
}
```

**Example cURL**:
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "SecurePassword123"
  }'
```

---

## 👤 User Management Endpoints

### 3. Create User (Admin Only)

**Endpoint**: `POST /user/create`  
**Access**: ROLE_ADMIN  
**Description**: Create a new user account (admin only)

**Request Body**:
```json
{
  "username": "new_user",
  "email": "newuser@example.com",
  "password": "SecurePassword123",
  "firstName": "New",
  "lastName": "User"
}
```

**Response** (200 OK):
```json
{
  "userId": 2,
  "username": "new_user",
  "email": "newuser@example.com",
  "firstName": "New",
  "lastName": "User",
  "createdAt": "2024-03-10",
  "updatedAt": "2024-03-10"
}
```

**Example cURL**:
```bash
curl -X POST http://localhost:8080/api/v1/user/create \
  -H "Authorization: Bearer <admin_token>" \
  -H "Content-Type: application/json" \
  -d '{...}'
```

---

### 4. Update User (Admin Only)

**Endpoint**: `PUT /user/update/{userId}`  
**Access**: ROLE_ADMIN  
**Description**: Update user information

**Request Body**:
```json
{
  "firstName": "Updated",
  "lastName": "Name",
  "mobile": "+919876543210"
}
```

**Response** (200 OK):
```json
{
  "userId": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "firstName": "Updated",
  "lastName": "Name",
  "updatedAt": "2024-03-10"
}
```

**Example cURL**:
```bash
curl -X PUT http://localhost:8080/api/v1/user/update/1 \
  -H "Authorization: Bearer <admin_token>" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Updated",
    "lastName": "Name"
  }'
```

---

### 5. Change Password

**Endpoint**: `PUT /user/change-password/{userId}`  
**Access**: ROLE_USER, ROLE_ADMIN  
**Description**: Change user password

**Request Body**:
```json
{
  "currentPassword": "OldPassword123",
  "newPassword": "NewPassword456"
}
```

**Response** (200 OK):
```json
{
  "userId": 1,
  "username": "john_doe",
  "email": "john@example.com"
}
```

**Example cURL**:
```bash
curl -X PUT http://localhost:8080/api/v1/user/change-password/1 \
  -H "Authorization: Bearer <your_token>" \
  -H "Content-Type: application/json" \
  -d '{
    "currentPassword": "OldPassword123",
    "newPassword": "NewPassword456"
  }'
```

---

### 6. Change Contact Information

**Endpoint**: `PUT /user/change-contact/{userId}`  
**Access**: ROLE_USER, ROLE_ADMIN  
**Description**: Update email and/or mobile number

**Request Body**:
```json
{
  "email": "newemail@example.com",
  "mobile": "+919876543210",
  "password": "CurrentPassword123"
}
```

**Response** (200 OK):
```json
{
  "userId": 1,
  "username": "john_doe",
  "email": "newemail@example.com",
  "mobile": "+919876543210"
}
```

**Example cURL**:
```bash
curl -X PUT http://localhost:8080/api/v1/user/change-contact/1 \
  -H "Authorization: Bearer <your_token>" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "newemail@example.com",
    "mobile": "+919876543210",
    "password": "CurrentPassword123"
  }'
```

---

## 🎬 Movie Endpoints

### 7. Get All Movies

**Endpoint**: `GET /movies`  
**Access**: Public  
**Description**: Retrieve all movies

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "title": "Inception",
    "summary": "A skilled thief who steals corporate secrets...",
    "voteAverage": 8.8,
    "voteCount": 15000,
    "releaseDate": "2010-07-16",
    "genres": [
      {
        "id": 1,
        "genreName": "Action"
      },
      {
        "id": 2,
        "genreName": "Sci-Fi"
      }
    ]
  }
]
```

**Example cURL**:
```bash
curl -X GET http://localhost:8080/api/v1/movies
```

---

### 8. Get Top Rated Movies

**Endpoint**: `GET /movies/top-rated`  
**Access**: Public  
**Description**: Get movies sorted by ratings

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "title": "Inception",
    "voteAverage": 8.8,
    "voteCount": 15000
  }
]
```

**Example cURL**:
```bash
curl -X GET http://localhost:8080/api/v1/movies/top-rated
```

---

### 9. Search Movies by Title

**Endpoint**: `GET /movies/{title}`  
**Access**: Public  
**Description**: Search movies by title

**Path Parameters**:
- `title` (String): Movie title to search

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "title": "Inception",
    "summary": "...",
    "voteAverage": 8.8
  }
]
```

**Example cURL**:
```bash
curl -X GET http://localhost:8080/api/v1/movies/Inception
```

---

### 10. Get Movies by Genre

**Endpoint**: `GET /movies/by-genre?genre={genreName}`  
**Access**: Public  
**Description**: Filter movies by genre

**Query Parameters**:
- `genre` (String): Genre name

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "title": "Inception",
    "genres": [{"id": 1, "genreName": "Action"}]
  }
]
```

**Example cURL**:
```bash
curl -X GET "http://localhost:8080/api/v1/movies/by-genre?genre=Action"
```

---

### 11. Search Movies with Filters

**Endpoint**: `GET /movies/search?query={searchText}&genreName={genreName}`  
**Access**: Public  
**Description**: Advanced search with optional genre filter

**Query Parameters**:
- `query` (String, required): Search text
- `genreName` (String, optional): Genre to filter

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "title": "Inception",
    "summary": "..."
  }
]
```

**Example cURL**:
```bash
curl -X GET "http://localhost:8080/api/v1/movies/search?query=dream&genreName=Sci-Fi"
```

---

### 12. Get Movies by Multiple Genres

**Endpoint**: `GET /movies/by-genres?genres={genre1}&genres={genre2}`  
**Access**: Public  
**Description**: Filter movies by multiple genres

**Query Parameters**:
- `genres` (List of String): Multiple genres to filter

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "title": "Inception",
    "genres": [...]
  }
]
```

**Example cURL**:
```bash
curl -X GET "http://localhost:8080/api/v1/movies/by-genres?genres=Action&genres=Sci-Fi"
```

---

## 🎭 Genre Endpoints

### 13. Get All Genres

**Endpoint**: `GET /genres`  
**Access**: Public  
**Description**: Retrieve all available genres

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "genreName": "Action"
  },
  {
    "id": 2,
    "genreName": "Comedy"
  },
  {
    "id": 3,
    "genreName": "Drama"
  }
]
```

**Example cURL**:
```bash
curl -X GET http://localhost:8080/api/v1/genres
```

---

## 📋 Watchlist Endpoints

### 14. Add Movie to Watchlist

**Endpoint**: `POST /watchlist`  
**Access**: ROLE_USER, ROLE_ADMIN  
**Description**: Add a movie to user's watchlist

**Request Body**:
```json
{
  "userId": 1,
  "movieId": 5
}
```

**Response** (200 OK):
```json
{
  "id": 100,
  "userId": 1,
  "movieId": 5
}
```

**Example cURL**:
```bash
curl -X POST http://localhost:8080/api/v1/watchlist \
  -H "Authorization: Bearer <your_token>" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "movieId": 5
  }'
```

---

### 15. Get Watchlist Movies

**Endpoint**: `GET /watchlist/user/{userId}/movies`  
**Access**: ROLE_USER, ROLE_ADMIN  
**Description**: Retrieve all movies in user's watchlist

**Path Parameters**:
- `userId` (Integer): User ID

**Response** (200 OK):
```json
[
  {
    "id": 5,
    "title": "The Matrix",
    "summary": "...",
    "voteAverage": 8.7,
    "genres": [...]
  }
]
```

**Example cURL**:
```bash
curl -X GET http://localhost:8080/api/v1/watchlist/user/1/movies \
  -H "Authorization: Bearer <your_token>"
```

---

### 16. Remove Movie from Watchlist

**Endpoint**: `DELETE /watchlist/user/{userId}/remove-movie/{movieId}`  
**Access**: ROLE_USER, ROLE_ADMIN  
**Description**: Remove a movie from watchlist

**Path Parameters**:
- `userId` (Integer): User ID
- `movieId` (Integer): Movie ID

**Response** (200 OK):
```json
"Movie removed from watch list successfully."
```

**Example cURL**:
```bash
curl -X DELETE http://localhost:8080/api/v1/watchlist/user/1/remove-movie/5 \
  -H "Authorization: Bearer <your_token>"
```

---

## 👁️ Watched Movies Endpoints

### 17. Mark Movie as Watched

**Endpoint**: `POST /watchedmovies`  
**Access**: ROLE_USER, ROLE_ADMIN  
**Description**: Mark a movie as watched

**Request Body**:
```json
{
  "userId": 1,
  "movieId": 5
}
```

**Response** (200 OK):
```json
{
  "id": 50,
  "userId": 1,
  "movieId": 5
}
```

**Example cURL**:
```bash
curl -X POST http://localhost:8080/api/v1/watchedmovies \
  -H "Authorization: Bearer <your_token>" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "movieId": 5
  }'
```

---

### 18. Get Watched Movies

**Endpoint**: `GET /watchedmovies/user/{userId}/movies`  
**Access**: ROLE_USER, ROLE_ADMIN  
**Description**: Retrieve all movies marked as watched

**Path Parameters**:
- `userId` (Integer): User ID

**Response** (200 OK):
```json
[
  {
    "id": 5,
    "title": "The Matrix",
    "summary": "...",
    "voteAverage": 8.7
  }
]
```

**Example cURL**:
```bash
curl -X GET http://localhost:8080/api/v1/watchedmovies/user/1/movies \
  -H "Authorization: Bearer <your_token>"
```

---

### 19. Remove Movie from Watched

**Endpoint**: `DELETE /watchedmovies/user/{userId}/remove-movie/{movieId}`  
**Access**: ROLE_USER, ROLE_ADMIN  
**Description**: Remove a movie from watched list

**Path Parameters**:
- `userId` (Integer): User ID
- `movieId` (Integer): Movie ID

**Response** (200 OK):
```json
"Movie removed from watch list successfully."
```

**Example cURL**:
```bash
curl -X DELETE http://localhost:8080/api/v1/watchedmovies/user/1/remove-movie/5 \
  -H "Authorization: Bearer <your_token>"
```

---

## 👨‍💼 Admin Endpoints

### 20. Get All Users

**Endpoint**: `GET /admin/users`  
**Access**: ROLE_ADMIN  
**Description**: Retrieve list of all users (admin only)

**Response** (200 OK):
```json
[
  {
    "userId": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "isActive": true,
    "createdAt": "2024-03-10"
  }
]
```

**Example cURL**:
```bash
curl -X GET http://localhost:8080/api/v1/admin/users \
  -H "Authorization: Bearer <admin_token>"
```

---

### 21. Assign Roles to User

**Endpoint**: `PUT /admin/users/{userId}/roles`  
**Access**: ROLE_ADMIN  
**Description**: Assign or update user roles

**Path Parameters**:
- `userId` (Integer): User ID

**Request Body**:
```json
["user", "admin"]
```

**Response** (200 OK):
```json
"Roles updated successfully!"
```

**Example cURL**:
```bash
curl -X PUT http://localhost:8080/api/v1/admin/users/2/roles \
  -H "Authorization: Bearer <admin_token>" \
  -H "Content-Type: application/json" \
  -d '["user", "admin"]'
```

**Available Roles**:
- `user` - ROLE_USER
- `admin` - ROLE_ADMIN
- `mod` - ROLE_MODERATOR

---

### 22. Deactivate User

**Endpoint**: `DELETE /admin/users/{userId}`  
**Access**: ROLE_ADMIN  
**Description**: Deactivate a user account

**Path Parameters**:
- `userId` (Integer): User ID

**Response** (200 OK):
```json
"User deactivated successfully!"
```

**Example cURL**:
```bash
curl -X DELETE http://localhost:8080/api/v1/admin/users/2 \
  -H "Authorization: Bearer <admin_token>"
```

---

## 🗄️ Database Schema

### Tables Overview

1. **users** - User account information
2. **roles** - Available roles (ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR)
3. **user_roles** - User-Role mapping
4. **movies** - Movie information
5. **genres** - Movie genres
6. **movie_genres** - Movie-Genre mapping
7. **watch_list** - User watchlist
8. **watched_movies** - Movies watched by users
9. **rating** - User ratings
10. **reviews** - User reviews

---

## 📊 Response Status Codes

| Status Code | Meaning |
|-------------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created |
| 400 | Bad Request - Invalid input |
| 401 | Unauthorized - Missing/invalid token |
| 403 | Forbidden - Access denied |
| 404 | Not Found - Resource not found |
| 409 | Conflict - Resource already exists |
| 500 | Internal Server Error |

---

## 🔒 Security Features

### Password Hashing
- Passwords are hashed using BCrypt
- Never store plain text passwords

### JWT Token Security
- Tokens expire after 24 hours (configurable)
- Use HTTPS in production
- Keep JWT secret secure and change it regularly

### Role-Based Access Control (RBAC)
- Different endpoints require different roles
- Public endpoints available without authentication
- Protected endpoints require valid JWT token

---

## 📝 Common Error Responses

### 401 Unauthorized
```json
{
  "timestamp": "2024-03-10T10:30:00.000+00:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "User not found"
}
```

### 403 Forbidden
```json
{
  "timestamp": "2024-03-10T10:30:00.000+00:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access Denied"
}
```

### 400 Bad Request
```json
{
  "timestamp": "2024-03-10T10:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Username is already taken!"
}
```

---

## 🧪 Testing the API

### Using Postman
1. Import the provided Postman collection
2. Set the `bearer_token` variable after login
3. Execute requests with proper headers

### Using cURL
See examples provided in each endpoint documentation

### Using Swagger UI (Optional)
Add Springdoc OpenAPI dependency for automatic API documentation

---

## 🐛 Troubleshooting

### Issue: "User not found with username"
- Ensure the username is registered in the system
- Check username spelling and case sensitivity

### Issue: "Roles updated successfully! but roles not reflected"
- Ensure you're using a new JWT token after role update
- Existing tokens won't reflect new roles until re-login

### Issue: "Access Denied" error
- Verify you have the required role for the endpoint
- Check the Authorization header is properly formatted
- Ensure token is not expired

---

## 📋 Database Schema Diagram

![db-schema-v1](./screenshots/db-schema-v1.png)

---

## 📱 Screenshots of Android App

![screenshot-0](./screenshots/moovy_app_screenshots.png)

---

## 🤝 Project Members

- **[Punith Kumar P R](https://github.com/punith-kumar-pr)** - Backend Developer
- **[Shashank S P](https://github.com/Shashankappu)** - Android Developer

---

## 📄 License

This project is licensed under the MIT License

---

## 🙏 Thank You

Thank you for using Moovy! We appreciate your interest and feedback.

For issues, suggestions, or contributions, please visit our [GitHub repository](https://github.com/punith-kumar-pr/Moovy-server).

---

**Last Updated**: March 10, 2026  
**API Version**: 1.0  
**Backend Version**: 0.0.1
