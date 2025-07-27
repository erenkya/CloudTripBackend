# CloudTrip API

A Spring Boot REST API for managing flight bookings and user authentication. This application provides endpoints for user management, flight operations, and JWT-based authentication.

## Features

- 🔐 JWT-based authentication
- ✈️ Flight management (add, retrieve flights)
- 👤 User management and validation
- 🎫 Flight booking and cancellation system
- 📊 RESTful API design

## Technologies Used

- **Spring Boot** - Main framework
- **Spring Web** - REST API development
- **JWT** - Authentication and authorization
- **Java** - Programming language

## API Endpoints

### Authentication Controller (`/auth`)

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| POST | `/auth/token` | Generate JWT token | `email`, `password` (form params) |
| GET | `/auth/validate` | Validate JWT token | `token` (query param) |

#### Example Usage:
```bash
# Generate token
POST /auth/token
Content-Type: application/x-www-form-urlencoded
email=user@example.com&password=userpassword

# Validate token
GET /auth/validate?token=your-jwt-token
```

### Flight Controller (`/flights`)

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/flights/getAllFlights` | Retrieve all flights | None |
| POST | `/flights/addFlight` | Add a single flight | `Flight` object |
| POST | `/flights/addFlights` | Add multiple flights | `List<Flight>` |

#### Example Usage:
```bash
# Get all flights
GET /flights/getAllFlights

# Add single flight
POST /flights/addFlight
Content-Type: application/json
{
  "flightNumber": "AA123",
  "origin": "NYC",
  "destination": "LAX",
  "price": 299.99
}

# Add multiple flights
POST /flights/addFlights
Content-Type: application/json
[
  {
    "flightNumber": "AA123",
    "origin": "NYC", 
    "destination": "LAX",
    "price": 299.99
  },
  {
    "flightNumber": "UA456",
    "origin": "SFO",
    "destination": "ORD", 
    "price": 189.50
  }
]
```

### User Controller (`/user`)

| Method | Endpoint | Description | Parameters/Body |
|--------|----------|-------------|-----------------|
| GET | `/user/validate/{email}/{password}` | Validate user credentials | Path variables |
| GET | `/user/getAllUsers` | Get all users | None |
| GET | `/user/getUser/{id}` | Get user by ID | `id` (path variable) |
| POST | `/user/add` | Add new user | `User` object |
| POST | `/user/purchaseFlight/{userId}/{flightId}` | Purchase a flight | Path variables |
| PUT | `/user/cancelFlight/{userId}/{flightId}` | Cancel flight booking | Path variables |

#### Example Usage:
```bash
# Validate user
GET /user/validate/user@example.com/password123

# Get all users
GET /user/getAllUsers

# Get specific user
GET /user/getUser/1

# Add new user
POST /user/add
Content-Type: application/json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securepassword"
}

# Purchase flight
POST /user/purchaseFlight/1/123

# Cancel flight
PUT /user/cancelFlight/1/123
```

## Project Structure

```
src/main/java/com/eren/CloudTrip/
├── controller/
│   ├── AuthController.java      # JWT authentication endpoints
│   ├── FlightController.java    # Flight management endpoints  
│   └── UserController.java      # User management endpoints
├── service/
│   ├── JwtService.java          # JWT token operations
│   ├── UserService.java         # User business logic
│   └── FlightService.java       # Flight business logic
└── model/
    ├── User.java                # User entity
    └── Flight.java              # Flight entity
```

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/cloudtrip.git
   cd cloudtrip
   ```

2. **Build the project**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access the API**
   - Base URL: `http://localhost:8080`
   - The application will start on port 8080 by default

## Configuration

Make sure to configure the following in your `application.properties`:

```properties
# Server configuration
server.port=8080

# JWT configuration (add your secret key)
jwt.secret=your-secret-key
jwt.expiration=86400

# Database configuration (if using a database)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
```

## Security Features

- **JWT Authentication**: Secure token-based authentication
- **Password Validation**: User credentials are validated before token generation
- **Token Validation**: Endpoint to verify JWT token validity
- **Email Extraction**: Extract user email from valid JWT tokens

## Response Format

The API returns responses in the following formats:

- **Success**: HTTP 200 with data or success message
- **Authentication**: JWT tokens for successful login
- **Validation**: Boolean responses or descriptive messages
- **Errors**: Appropriate HTTP status codes with error messages

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

**Developer**: Eren  
**Project**: CloudTrip API  
**Repository**: [GitHub Repository URL]

---

⭐ If you find this project helpful, please give it a star on GitHub!
