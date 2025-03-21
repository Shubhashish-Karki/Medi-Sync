# Medi-Sync

## Introduction
Welcome to the Medi-Sync project! This is a self-learning project designed to help users manage their medication schedules. The application allows users to add, view, and update medicine contents, and it provides notifications for medication times.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or above
- Visual Studio Code (VS Code) with the following extensions:
  - Language Support for Java by Red Hat
  - Project Manager for Java (optional, for enhanced project management features)
- MySQL Database Server
- MySQL JDBC Driver (download from [MySQL website](https://dev.mysql.com/downloads/connector/j/))

### Folder Structure
The workspace contains the following folders by default:
- `src`: The folder to maintain source code.
- `lib`: The folder to maintain dependencies.
- `bin`: The folder where compiled output files will be generated.

If you need to customize the folder structure, you can update the settings in `.vscode/settings.json`.

### Dependency Management
The JAVA PROJECTS view in VS Code allows you to manage your dependencies. For more details, refer to the [Project Manager for Java documentation](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Setting Up the Project

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd <repository-name>
   ```

2. **Configure Database Settings**
   - Open `src/database/DatabaseHelper.java`.
   - Update the username and password for your database connection.
   - Ensure you have the MySQL JDBC driver in your `lib` folder.

3. **Create the Database and Table**
   - Create a database named `medication`.
   - Execute the following SQL command to create the `Medications` table:
     ```sql
     CREATE TABLE IF NOT EXISTS Medications (
         med_id INT AUTO_INCREMENT PRIMARY KEY,
         med_name VARCHAR(100) NOT NULL,
         dosage VARCHAR(50),
         frequency VARCHAR(50),
         start_date DATE,
         end_date DATE,
         notes TEXT
     );
     ```

4. **Run the Application**
   - Open the project in VS Code.
   - Build and run the application from the `App.java` file.

## Features

1. **Add Medication**
   - Users can add new medication entries with details such as name, dosage, frequency, start date, end date, and notes.

2. **View Medication**
   - Users can view all current medication entries.

3. **Update Medication**
   - Users can update existing medication entries.

4. **Notifications**
   - The application will notify users when it's time to take their medication.

## JDBC Connection

The application uses JDBC (Java Database Connectivity) to interact with the MySQL database. The `DatabaseHelper.java` file contains the JDBC connection setup.

### Example JDBC Connection Code
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/medication";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

- **URL**: The JDBC URL to connect to the MySQL database.
- **USER**: The username for the database.
- **PASSWORD**: The password for the database.

Make sure to replace `your_username` and `your_password` with your actual database credentials.

## Contribution

If you would like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push to your fork.
4. Open a pull request to merge your changes into the main repository.

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact
For any questions or feedback, please contact [your-email@example.com].

---

This README provides a comprehensive guide to setting up and using the Medi-Sync project. Feel free to explore the code and contribute to its development!
