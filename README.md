# Real Estate Sales Lead Management System

A Spring Boot-based CRM system tailored for real estate agents to manage and track sales leads, follow-ups, and house listings. The system includes dashboards, CRUD functionality, lead tracking, and admin features for user management.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)

## Features

1. **Dashboard**:
   - Displays total leads for each user and lead conversion rates.
   - Shows house listings from all AFZ Realty agents, allowing users to explore co-agent opportunities.

2. **Lead Management**:
   - Allows users to add and manage new leads through a CRUD table.

3. **Lead Tracking**:
   - Tracks leads through sales pipeline stages such as property viewing, property selection, loan approval, and final sales.

4. **Follow-Up Management**:
   - Implements the 2x2x2 follow-up technique for tracking lead interactions, with automated scheduling of new follow-up dates.

5. **House Listing Management**:
   - CRUD functionality for users to store and manage property listings.

6. **Admin Management**:
   - Admins can edit and delete user accounts and approve new user registrations with approve/disapprove controls.

## Tech Stack

- **Java**: Version 21
- **Spring Boot**: 3.3.0
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-security`
  - `spring-boot-starter-thymeleaf`
  - `spring-boot-starter-web`
  - `spring-boot-starter-validation`
- **Database**: MySQL
- **Frontend**: Thymeleaf, Bootstrap, FontAwesome, jQuery
- **Webjars**: For jQuery (version 3.6.0)
- **Testing**:
  - `spring-boot-starter-test`
  - `spring-security-test`

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/slmsystem.git
   cd slmsystem
   ```

2. Set up the database:
   - Configure MySQL with a new database and update the connection details in `src/main/resources/application.properties`.

3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Open a browser and go to `http://localhost:8080` to access the application.

## Usage

1. **User Login**: Registered users can log in with credentials provided by the admin.
2. **Dashboard**: Users can view their lead statistics and check listings from other agents for co-agent opportunities.
3. **Admin Access**: Admin users can approve new registrations and manage user access.

## License

This project is licensed under the UITM.

---
