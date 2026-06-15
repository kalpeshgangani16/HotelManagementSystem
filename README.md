# 🏨 Hotel Management System

A **Java-based Spring Boot application** designed to manage hotel operations, including guests, bookings, rooms, and hotels. The system features a RESTful architecture with clearly separated controller, service, DAO, and entity layers for maintainability and scalability.

---

## ✅ Features

- 🔐 **Secure User Authentication:** Spring Security config with role-based access controls.
- 🛏️ **Room & Hotel Management:** Create and manage hotel info and room details.
- 📅 **Booking Operations:** Advanced booking management with conflict detection (ensures rooms cannot be double-booked).
- 👤 **Guest Data Management:** Manage customer registrations and contacts.
- 🧠 **Layered Architecture:** Controller, Service, DAO (Spring Data JPA), and Entity layers.
- 🔁 **RESTful APIs:** JSON-based REST APIs ready for integration with frontend applications.
- 💾 **Backend Persistence:** Powered by JPA & Hibernate with MySQL database configuration.

---

## 📁 Project Structure

```text
hotel_management/
├── src/main/java/com/project/hotel_management/
│   ├── controller/      # REST Controllers (Booking, Guest, Hotel, Room)
│   │   ├── BookingController.java
│   │   ├── GuestController.java
│   │   ├── HotelController.java
│   │   └── RoomController.java
│   ├── dao/             # Data Access Objects (JPA Repositories)
│   │   ├── BookingDAO.java
│   │   ├── GuestDAO.java
│   │   ├── HotelDAO.java
│   │   └── RoomDAO.java
│   ├── entities/        # JPA Entity models
│   │   ├── Booking.java
│   │   ├── Guest.java
│   │   ├── Hotel.java
│   │   └── Room.java
│   ├── security/        # Spring Security Config
│   │   └── hotelSecurity.java
│   ├── services/        # Service interfaces & implementations
│   │   ├── BookingService.java & BookingServiceImpl.java
│   │   ├── GuestService.java & GuestServiceImpl.java
│   │   ├── HotelService.java & HotelServiceImpl.java
│   │   └── RoomService.java & RoomServiceImpl.java
│   ├── HotelManagementApplication.java  # Main Application Entry Point
│   └── PasswordEncoder.java             # BCrypt encryption utility for seeding users
└── pom.xml              # Maven dependency management
```

---

## ⚙️ Installation & Setup

### 1. 📥 Clone the Repository

```bash
git clone https://github.com/kalpeshgangani16/HotelManagementSystem.git
cd HotelManagementSystem/HotelManagementSystem-main
```

### 2. 🗄️ Configure Database
Make sure MySQL is running and create the schema as defined in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sem4_jt
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### 3. 📦 Build & Run the Application

Using **Maven**:

```bash
# Build project
mvn clean install

# Run application
mvn spring-boot:run
```

> **Note:** The server starts on port **`8090`** by default (configured in `application.properties`).

---

## 🔐 Security Configuration

Access control is managed by Spring Security using HTTP Basic Authentication:
* **Admin** (`ROLE_ADMIN`): Full CRUD permissions on hotels, rooms, and bookings (including deletions).
* **Manager** (`ROLE_MANAGER`): Can manage bookings and rooms.
* **Receptionist** (`ROLE_RECEPTIONIST`): Can manage guests and bookings.
* **Guest** (`ROLE_GUEST`): Can view rooms and retrieve their own bookings.

Passwords should be encrypted using `BCryptPasswordEncoder`. Use `PasswordEncoder.java` to generate encrypted passwords for your database seeding.

---

## 🧠 Functional API Overview

All endpoint paths are prefixed with `/api`.

| Controller | HTTP Method | Endpoint | Description |
|------------|-------------|----------|-------------|
| **Hotels** | `POST` | `/api/hotels` | Create hotel (restricted to max 1 hotel) |
| | `GET` | `/api/hotels` | Retrieve hotel details |
| | `PUT` | `/api/hotels/{hotelId}` | Update hotel details |
| | `DELETE` | `/api/hotels/{hotelId}` | Remove hotel |
| **Rooms** | `POST` | `/api/rooms` | Create a new room |
| | `GET` | `/api/rooms` | List all rooms |
| | `GET` | `/api/rooms/{roomId}` | Get specific room by ID |
| | `PUT` | `/api/rooms/{roomId}` | Update room details |
| | `DELETE` | `/api/rooms/{roomId}` | Delete a room |
| **Guests** | `POST` | `/api/guests` | Register a new guest |
| | `GET` | `/api/guests` | Get list of all registered guests |
| | `GET` | `/api/guests/{guestId}` | Retrieve a guest profile |
| | `PUT` | `/api/guests/{guestId}` | Update guest details |
| | `DELETE` | `/api/guests/{guestId}`| Remove a guest |
| **Bookings**| `POST` | `/api/bookings/with_guest` | Create booking using existing guest ID |
| | `POST` | `/api/bookings/no_guest` | Create booking and guest simultaneously |
| | `GET` | `/api/bookings` | List all bookings (custom summarized JSON format) |
| | `GET` | `/api/bookings/{bookingId}` | Retrieve full booking info |
| | `GET` | `/api/bookings/my/{firstName}` | Get all bookings matching guest's first name |
| | `PUT` | `/api/bookings/{bookingId}` | Update check-in/out dates or room/guest |
| | `DELETE` | `/api/bookings/{bookingId}` | Cancel / delete a booking |

---

## 🧪 Example API Usage

### Create Guest (`POST /api/guests`)

**Request Payload:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890"
}
```

### Create Booking for New Guest (`POST /api/bookings/no_guest`)

**Request Payload:**
```json
{
  "checkInDate": "2026-07-01",
  "checkOutDate": "2026-07-05",
  "room": {
    "id": 1
  },
  "guest": {
    "firstName": "Alice",
    "lastName": "Smith",
    "email": "alice.smith@example.com",
    "phoneNumber": "9876543210"
  }
}
```

---

## 🙏 Thank You

Thanks for checking out the Hotel Management System! Feel free to contribute or raise issues. Your feedback is welcome. 🏨

## 👤 Author

**Kalpesh Gangani**
