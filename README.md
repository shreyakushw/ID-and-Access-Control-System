# ID-and-Access-Control-System

## Introduction

This project demonstrates how user information is managed in applications, including registration and login functionalities. Users can create new accounts and log in using their credentials. The application performs authentication by checking user credentials against a MySQL database. If the username and password match, the user is granted access; otherwise, an error is displayed. 

When registering, users must provide details including Name, Date of Birth, Gender, Address, Contact Number, Email Address, and Password. The application validates the provided details and ensures fields are not left empty and passwords are unique. The Date of Birth must not exceed the current date. Upon successful registration, users are redirected to a confirmation page and then to the login page. Successful login directs users to the application's homepage.

The project uses Java for coding, JavaFX for designing the application, and MySQL for storing user registration information and login credentials.

## Implementation Details

### Creating a Maven Project

1. **Open Eclipse IDE**
   - Go to `File → New → Maven Project`.

2. **Setup Project**
   - Check the `Create a simple project` checkbox.
   - Use the default Workspace location.
   - Click `Next`.

3. **Provide Project Information**
   - GroupId: `com.cdac`
   - ArtifactId: `KMS`
   - Click `Finish`.

4. **Verify Project**
   - Your new Maven project should now appear in the Eclipse IDE.

### Creating a New Package and Main Class

1. **Create a New Package**
   - Right-click on the Maven project → `New → Package`.
   - Add a source folder and name the package.
   - Click `Finish`.

2. **Create a New Class**
   - Right-click on the package → `New → Class`.
   - Name the class: `App`.
   - Click `Finish`.

### Configuring Build Path

1. **Configure Build Path**
   - Right-click on `App.java` → `Build Path → Configure Build Path`.
   - In the dialog box, click on `Libraries` and then `Add External JARs`.
   - Add necessary JARs.

### Adding Dependencies for Connecting MySQL Database

1. **Add Dependencies**
   - Right-click on `App.java` → `Run As → Run Configurations`.
   - In the dialog box, click on `Dependencies` → `Classpath Entries` → `Add External JARs`.
   - Add the required JARs.

### JavaFX Application

1. **Create JavaFX Application**
   - Extend `App.java` from `javafx.application.Application` and override its `start()` method.

2. **Design Layouts**
   - Use JavaFX `AnchorPane` for creating pages: `LoginPage`, `Registration Page`, `RegistrationConfirmationPage`, and `HomePage`.
   - Design layouts using Scene Builder and FXML.

### Creating Database in MySQL

1. **Database Schema**
   - Create a database in MySQL with two tables: `Customers` and `Login_Info`.
   
   **Customers Table**
   - `Email_id varchar(50) not null unique`
   - `Password_c varchar(10) not null unique`
   - `Fname char(20) not null`
   - `Lname char(20)`
   - `date_of_birth date not null`
   - `Gender char(6) not null`
   - `Address varchar(70) not null`
   - `contact_no int(10) not null`
   - `primary key(Email_id)`

   **Login_Info Table**
   - `Username varchar(50) not null`
   - `Password_l varchar(10) not null`
   - `primary key(Password_l)`
   - `foreign key(Username) references Customers(Email_id)`

### Creating Controller Classes

1. **Controller Classes**
   - Create a controller class for each page to handle backend logic.
   - Each controller class contains methods for button actions and other functionalities.

### Run the Application

1. **Run Application**
   - Right-click on `App.java` → `Run As → Java Application`.
