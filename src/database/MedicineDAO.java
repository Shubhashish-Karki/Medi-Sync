package database;
import models.Medicine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {
    public void addMedication(Medicine med){
        String query = "INSERT INTO medications(med_name, dosage, frequency, start_date, end_date, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseHelper.connect();
        PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1,med.getMed_name());
            pstmt.setString(2,med.getMed_dosage());
            pstmt.setString(3,med.getMed_frequency());
            pstmt.setDate(4,med.getStart_date());
            pstmt.setDate(5,med.getEnd_date());
            pstmt.setString(6,med.getNotes());
            pstmt.executeUpdate();
            System.out.println("Medication added successfully");

            }
        catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        
    
    }

    

}
