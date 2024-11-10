# Real Estate Sales Lead Management System

A Spring Boot-based CRM system tailored for real estate agents to manage and track sales leads, follow-ups, and house listings. The system includes dashboards, CRUD functionality, lead tracking, and admin features for user management.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)
- [Screenshot](#screenshot)

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
3. **Lead Management**: Users can add new leads, view lead details, and edit or delete existing leads in a CRUD table.
4. **Lead Tracking**: Agents can track leads through stages in the sales pipeline, such as property viewing, property selection, loan approval, and sales.
5. **Follow-Up Management**: Agents can use the 2x2x2 follow-up technique to track lead interactions, with automatic scheduling of new follow-up dates based on follow-up activity.
6. **House Listing Management**: Users can add, view, edit, and delete house listings in a CRUD table for property management.
7. **Admin Access**: Admin users can approve new registrations and manage user access.

## License

This project is licensed under the UITM.

---

## Screenshot

1. **User Login**:

   ![LOGIN](https://github.com/user-attachments/assets/551fc902-4947-4e03-bc5a-b7653e78cf84)
   
2. **Registration**:

   ![REGISTER](https://github.com/user-attachments/assets/c3425057-7b18-4f40-9f67-2bfce4d84aab)

3. **Dashboard**:

   ![dashbaord](https://github.com/user-attachments/assets/d37b9d82-da4c-4fe6-8501-1f8ab19cdf83)zz
   
4. **Lead Registration**:

   ![lead-register](https://github.com/user-attachments/assets/9f0301df-0d7c-4425-8ec0-dc5ffcc45dec)
   
5. **Lead Management**:

   ![manage lead](https://github.com/user-attachments/assets/3f97e0b3-53be-4669-a5cf-eed78335e52e)
   
7. **Lead Tracking**:

   ![lead tracking](https://github.com/user-attachments/assets/ec089d16-416d-47e7-aab5-8d7ebaa2b520)

   
6. **Follow-Up**:

   ![follow-up](https://github.com/user-attachments/assets/e28a223f-7a19-436c-a798-0d12f8b60595)

7. **Add House Listing**:

   ![submit listing](https://github.com/user-attachments/assets/85be4631-2067-4f79-9385-4a7150794e9a)

10. **House Listing Management**:

   ![manalisting](https://github.com/user-attachments/assets/b3e9baaf-2c87-4fa7-a1cc-98da66e36708)

11. **Admin Access**:
    
   ![manage user](https://github.com/user-attachments/assets/dd597847-d0e9-461f-9f09-4949fbb4a892)
