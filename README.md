# Leave Management System (Full Stack)

Welcome to your first full stack project! This Leave Management System lets employees apply for leave and admins manage those requests. It’s built with a modern tech stack: **Spring Boot** (Java) for the backend, **React** (with Material-UI) for the frontend, and **PostgreSQL** for the database.

---

## 🌱 What is this project?
A web app where:
- **Employees** can log in, apply for leave, and see their leave history.
- **Admins** can log in, view all leave requests, approve/reject them, and add comments.

You built both the backend (API, database, authentication) and the frontend (UI, user experience) yourself!

---

## 🧠 How it works (in simple terms)
- **Frontend (React + MUI):**
  - Users interact with forms and dashboards in their browser.
  - The UI talks to the backend using HTTP requests (REST API).
  - Uses Material-UI for a modern, responsive look.
- **Backend (Spring Boot):**
  - Handles all the logic: authentication, saving leave requests, user roles, etc.
  - Uses JWT tokens for secure login.
  - Talks to the PostgreSQL database to store and retrieve data.
- **Database (PostgreSQL):**
  - Stores users, leave requests, and their statuses.

---

## 🗂️ Project Structure (Full Stack)
```
leave-management/
├── leave-management/                # Backend (Spring Boot)
│   ├── src/main/java/com/example/leave_management/
│   │   ├── config/        # App config
│   │   ├── controller/    # API endpoints
│   │   ├── dto/           # Data transfer objects
│   │   ├── entity/        # Database models
│   │   ├── repository/    # Database access
│   │   ├── security/      # JWT & security
│   │   └── service/       # Business logic
│   ├── src/main/resources/
│   │   └── application.properties
│   ├── Dockerfile, docker-compose.yml, pom.xml, etc.
│   └── ...
├── leave-management-frontend/       # Frontend (React)
│   ├── src/
│   │   ├── components/    # Reusable UI (Navbar, Spinner, etc.)
│   │   ├── context/       # Auth context
│   │   ├── pages/         # Main pages (AdminDashboard, etc.)
│   │   ├── services/      # API calls
│   │   └── ...
│   ├── public/
│   ├── package.json, ...
│   └── ...
└── ...
```

---

## 👀 How to revise/understand this project (for your future self)
- **Start with the README** (this file!) for setup and structure.
- **Backend:**
  - Look at `controller/` for API endpoints (what the app can do).
  - `service/` has the main logic (how things are done).
  - `entity/` and `repository/` show how data is stored.
  - `security/` handles login and permissions.
- **Frontend:**
  - `pages/` are the main screens (AdminDashboard, Login, etc.).
  - `components/` are reusable UI pieces.
  - `services/api.js` is how the frontend talks to the backend.
- **To run locally:**
  - Start the backend (Spring Boot) and frontend (React) separately.
  - Use the default users to log in and test.
- **To review code:**
  - Trace a feature end-to-end (e.g., "Apply for leave" → frontend form → API call → backend logic → database).
  - Use the Postman collection to test APIs directly.
- **If you get stuck:**
  - Check the troubleshooting section below.
  - Google error messages—this is normal!

---

## 🎯 Features

### Authentication & Authorization
- ✅ JWT-based authentication
- ✅ Role-based access control (Admin & Employee)
- ✅ Secure password encryption with BCrypt
- ✅ Token generation and validation

### Employee Features
- ✅ Apply for leave requests
- ✅ View personal leave history
- ✅ Multiple leave types (Sick, Annual, Personal, etc.)

### Admin Features
- ✅ View all pending leave requests
- ✅ Approve or reject leave requests
- ✅ Add comments to decisions
- ✅ Manage employee leave applications

### Technical Features
- ✅ Spring Boot 3.5.4 with Java 21
- ✅ Spring Security with JWT
- ✅ Spring Data JPA with PostgreSQL
- ✅ Docker containerization
- ✅ Comprehensive API documentation
- ✅ Postman collection for testing

---

## 🚀 Quick Start

### Prerequisites
- Java 21
- Maven 3.6+
- PostgreSQL 15+
- Node.js & npm (for frontend)
- Docker & Docker Compose (optional)

### 1. Clone the repo
```bash
cd leave-management
```

### 2. Set up PostgreSQL database
```sql
CREATE DATABASE leavemanagement;
CREATE USER postgres WITH PASSWORD 'root';
GRANT ALL PRIVILEGES ON DATABASE leavemanagement TO postgres;
```

### 3. Start the backend (Spring Boot)
```bash
cd leave-management
./mvnw spring-boot:run
```

### 4. Start the frontend (React)
```bash
cd leave-management-frontend
npm install
npm start
```

### 5. Option: Run everything with Docker
```bash
docker-compose up --build
```

---

## 🔐 Default Users

The application creates default users on startup:

### Admin User
- **Username:** `admin`
- **Password:** `admin123`
- **Role:** ADMIN

### Employee User
- **Username:** `employee`
- **Password:** `employee123`
- **Role:** EMPLOYEE

---

## 📚 API Documentation
(See below for example requests and endpoints, or use the provided Postman collection.)

---

## 🧪 Testing with Postman
1. Import the Postman collection (`Leave_Management_API.postman_collection.json`).
2. Use the default users to log in and get tokens.
3. Try out the API endpoints for both employee and admin roles.

---

## 📝 More Details
(Everything below is kept from your original README: API endpoints, configuration, database schema, security, deployment, troubleshooting, license, contributing, and support.)

## 🚀 Quick Start

### Prerequisites
- Java 21
- Maven 3.6+
- PostgreSQL 15+
- Docker & Docker Compose (optional)

### Option 1: Local Development

1. **Clone and navigate to the project**
   ```bash
   cd leave-management
   ```

2. **Set up PostgreSQL database**
   ```sql
   CREATE DATABASE leavemanagement;
   CREATE USER postgres WITH PASSWORD 'root';
   GRANT ALL PRIVILEGES ON DATABASE leavemanagement TO postgres;
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

### Option 2: Docker Setup

1. **Build and run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

2. **Access the application**
   - Application: http://localhost:8080
   - Database: localhost:5432

## 🔐 Default Users

The application creates default users on startup:

### Admin User
- **Username:** `admin`
- **Password:** `admin123`
- **Role:** ADMIN

### Employee User
- **Username:** `employee`
- **Password:** `employee123`
- **Role:** EMPLOYEE

## 📚 API Documentation

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
    "username": "newemployee",
    "password": "password123"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
    "username": "employee",
    "password": "employee123"
}
```

### Employee Endpoints

#### Apply for Leave
```http
POST /api/employee/leave
Authorization: Bearer <token>
Content-Type: application/json

{
    "startDate": "2024-01-15",
    "endDate": "2024-01-17",
    "reason": "Personal vacation",
    "leaveType": "ANNUAL_LEAVE"
}
```

#### Get My Leave Requests
```http
GET /api/employee/leave
Authorization: Bearer <token>
```

### Admin Endpoints

#### Get Pending Leave Requests
```http
GET /api/admin/leave/pending
Authorization: Bearer <token>
```

#### Approve/Reject Leave Request
```http
PUT /api/admin/leave/{leaveId}/approve
Authorization: Bearer <token>
Content-Type: application/json

{
    "status": "APPROVED",
    "comments": "Approved - Enjoy your vacation!"
}
```

## 🧪 Testing with Postman

1. **Import the Postman collection**
   - Open Postman
   - Import `Leave_Management_API.postman_collection.json`

2. **Set up environment variables**
   - `base_url`: `http://localhost:8080`
   - `employee_token`: (will be set after login)
   - `admin_token`: (will be set after login)

3. **Test workflow**
   1. Login as employee → Copy token to `employee_token`
   2. Login as admin → Copy token to `admin_token`
   3. Apply for leave as employee
   4. View pending requests as admin
   5. Approve/reject leave as admin

## 🔧 Configuration

### Application Properties
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/leavemanagement
spring.datasource.username=postgres
spring.datasource.password=root

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
app.jwt.secret=shreyaSuperSecretKey
app.jwt.expiration=86400000
```

### Environment Variables
- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password

## 🐳 Docker Commands

### Build and Run
```bash
# Build the application
docker build -t leave-management .

# Run with Docker Compose
docker-compose up --build

# Run in background
docker-compose up -d

# Stop services
docker-compose down
```

### Individual Containers
```bash
# Build image
docker build -t leave-management .

# Run container
docker run -p 8080:8080 leave-management
```

## 📊 Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);
```

### Leave Requests Table
```sql
CREATE TABLE leave_requests (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason VARCHAR(500) NOT NULL,
    leave_type VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    approved_by BIGINT,
    admin_comments VARCHAR(500),
    FOREIGN KEY (employee_id) REFERENCES users(id),
    FOREIGN KEY (approved_by) REFERENCES users(id)
);
```

## 🔒 Security Features

### JWT Implementation
- **Token Generation**: Uses HMAC-SHA256 algorithm
- **Token Validation**: Automatic validation on each request
- **Token Expiration**: Configurable expiration time (24 hours default)
- **Secret Key**: Configurable secret for token signing

### Role-Based Access Control
- **ADMIN Role**: Full access to all endpoints
- **EMPLOYEE Role**: Limited to employee-specific endpoints
- **Method Security**: `@PreAuthorize` annotations for fine-grained control

### Password Security
- **BCrypt Encryption**: Secure password hashing
- **Salt Generation**: Automatic salt generation for each password
- **Validation**: Password strength validation

## 🚀 Deployment

### Production Considerations
1. **Change JWT Secret**: Update `app.jwt.secret` in production
2. **Database Security**: Use strong database passwords
3. **HTTPS**: Enable HTTPS in production
4. **Environment Variables**: Use environment variables for sensitive data
5. **Logging**: Configure appropriate logging levels

### Environment Setup
```bash
# Production environment variables
export SPRING_PROFILES_ACTIVE=prod
export SPRING_DATASOURCE_URL=jdbc:postgresql://prod-db:5432/leavemanagement
export APP_JWT_SECRET=your-super-secret-production-key
```

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Verify PostgreSQL is running
   - Check database credentials
   - Ensure database exists

2. **JWT Token Issues**
   - Verify token format: `Bearer <token>`
   - Check token expiration
   - Validate JWT secret configuration

3. **Permission Denied**
   - Verify user role
   - Check endpoint authorization
   - Validate token authenticity

### Logs
```bash
# View application logs
docker-compose logs app

# View database logs
docker-compose logs postgres

# Follow logs in real-time
docker-compose logs -f app
```

## 📝 License

This project is created for educational purposes as part of the Week 4 Mini Project assignment.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📞 Support

For questions or issues:
1. Check the troubleshooting section
2. Review the API documentation
3. Test with the provided Postman collection

---

**Happy Coding! 🎉** 