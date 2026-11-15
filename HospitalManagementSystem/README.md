# Hospital Management System

A learning/demo web application built with Java 17, Spring Boot, Spring MVC, Thymeleaf, Spring Data JPA, Hibernate, MySQL, HTML and CSS.

## Included modules
- Admin login/logout (Spring Security)
- Dashboard summary
- Patient CRUD (create, list, update, delete)
- Doctor CRUD (create, list, update, delete)
- Appointment booking/list/delete
- Billing create/list/delete

## Requirements
- JDK 17
- MySQL Server / MySQL Workbench
- Eclipse IDE or Spring Tool Suite (STS)
- Internet connection for the first Maven dependency download

## Database setup
1. Start MySQL.
2. Run: `CREATE DATABASE hospital_db;`
3. Open `src/main/resources/application.properties`.
4. Set `spring.datasource.username` and `spring.datasource.password` to your own MySQL credentials.
5. Hibernate creates/updates the application tables automatically.

## Import into Eclipse
1. Extract `HospitalManagementSystem.zip`.
2. In Eclipse choose File > Import > Maven > Existing Maven Projects.
3. Select the extracted `HospitalManagementSystem` folder and click Finish.
4. Wait for Maven to download dependencies.
5. Open `HospitalManagementSystemApplication.java`.
6. Right-click > Run As > Java Application.
7. Visit `http://localhost:8080`.

## Demo administrator login
- Username: `admin`
- Password: `Admin@123`

## Important
This is an educational starter application, not a production-ready hospital system. The demo admin account is configured in `SecurityConfig.java`; change it before deployment. Real patient data requires stronger access controls, audit trails, backups, privacy safeguards, secure deployment, and compliance with applicable laws. Delete actions in this demo use GET links for simplicity and should be changed to CSRF-protected POST forms before production use.
