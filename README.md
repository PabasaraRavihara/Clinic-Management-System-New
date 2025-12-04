# 🏥 Clinic Management System - Backend

This is the backend server for the **Clinic Management System**, built using **Java Spring Boot**. It provides RESTful APIs to manage patients, doctors, appointments, medical records, and billing operations.

## 🚀 Features

* **User Authentication:** Secure login for Admins, Doctors, and Patients.
* **Patient Management:** Registration, profile updates, and viewing medical history.
* **Doctor Portal:** Dashboard to view appointments, manage patients, and add medical records.
* **Admin Dashboard:** Manage doctors, view system statistics, and oversee operations.
* **Appointment System:** Scheduling, updating, and canceling appointments.
* **Medical Records:** Digital storage for diagnosis, treatments, and prescriptions.
* **Billing System:** Generate invoices for appointments.

## 🛠️ Tech Stack

* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Database:** MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Security:** Spring Security (JWT Configuration available)
* **Build Tool:** Maven

## 📂 Project Structure

```text
src
├── main
│   ├── java/com/example/Clinic_Management_System
│   │   ├── config       # Security & CORS Config
│   │   ├── controller   # REST API Controllers
│   │   ├── model        # JPA Entities (Database Tables)
│   │   ├── repository   # Database Access Layer
│   │   ├── service      # Business Logic
│   │   └── ClinicManagementSystemApplication.java
│   └── resources
│       └── application.properties # Database Configuration
