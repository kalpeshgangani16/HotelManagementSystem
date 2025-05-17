
# 🏨 Hotel Management System

A **Java-based Spring Boot application** designed to manage hotel operations, including guests, bookings, rooms, and hotels. The system features a RESTful architecture with clearly separated controller, service, DAO, and entity layers for maintainability and scalability.

---

## ✅ Features

- 🔐 Secure user authentication (Spring Security-ready)
- 🛏️ Room and hotel management
- 📅 Booking operations with full CRUD functionality
- 👤 Guest data management
- 🧠 Layered architecture (Controller, Service, DAO, Entity)
- 🔁 RESTful APIs for front-end integration
- 💾 Backend powered by JPA & Hibernate (supports H2/MySQL/etc.)

---

## 🧠 Functional Overview

| Endpoint | Description |
|---------|-------------|
| `/booking` | Manage bookings (create, update, delete, get) |
| `/guest` | Manage guest information |
| `/hotel` | CRUD operations for hotels |
| `/room` | Room availability, details, and management |
| `/auth/*` | User authentication endpoints (if security is active) |

---

## 📁 Project Structure

```text
hotel_management/
├── controller/           # REST Controllers (Booking, Guest, Hotel, Room)
│   ├── BookingController.java
│   ├── GuestController.java
│   ├── HotelController.java
│   └── RoomController.java
├── dao/                  # DAO interfaces for database access
│   ├── BookingDAO.java
│   ├── GuestDAO.java
│   ├── HotelDAO.java
│   └── RoomDAO.java
├── entities/             # Entity classes (JPA models)
│   ├── Booking.java
│   ├── Guest.java
│   ├── Hotel.java
│   └── Room.java
├── security/             # Spring Security configuration (optional)
├── services/             # Business logic layer
│   └── BookingService, GuestService, etc.
├── HotelManagementApplication.java  # Main Spring Boot app
└── PasswordEncoder.java             # Password encryption (if security is active)
```

---

## ⚙️ Installation & Setup

### 1. 📥 Clone the Repository

```bash
git clone https://github.com/kalpeshgangani16/HotelManagementSystem.git
cd HotelManagementSystem
```

### 2. 📦 Build the Project

Using **Maven**:

```bash
mvn clean install
```

Or **Gradle**:

```bash
./gradlew build
```

### 3. 🚀 Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Using Gradle:

```bash
./gradlew bootRun
```

> Visit: [http://localhost:8080](http://localhost:8080)

---

## 🔐 Security (If Enabled)

- 🔒 Passwords encrypted using `BCryptPasswordEncoder`
- 🛡️ Role-based access control supported
- 🧪 Token-based/JWT authentication ready for integration

---

## 🧪 Example API Usage

Sample JSON to create a guest:

```json
POST /guest
{
  "name": "John Doe",
  "email": "john@example.com",
  "phone": "1234567890"
}
```

---

## 🙏 Thank You

Thanks for checking out the Hotel Management System! Feel free to contribute or raise issues. Your feedback is welcome. 🏨
