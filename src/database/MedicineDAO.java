package database;
import models.Medicine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

    //add medicine
    public void addMedication(Medicine med)
    {
        String query = "INSERT INTO medications(med_name, dosage, frequency, start_date, end_date, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (
        Connection conn = DatabaseHelper.connect();
        PreparedStatement pstmt = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setString(1,med.getMed_name());
            pstmt.setString(2,med.getMed_dosage());
            pstmt.setString(3,med.getMed_frequency());
            pstmt.setDate(4,med.getStart_date());
            pstmt.setDate(5,med.getEnd_date());
            pstmt.setString(6,med.getNotes());
            // pstmt.executeUpdate();
            // System.out.println("Medication added successfully");

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) 
            {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) 
                {
                    if (generatedKeys.next()) 
                    {
                        int generatedId = generatedKeys.getInt(1);  // Retrieve the generated med_id
                        med.setMed_id(generatedId);  // Set the generated med_id in the Medicine object
                        System.out.println("Inserted medication with ID: " + generatedId);
                    }
                }
            }
        }   catch(SQLException e)
                {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        //view medicine 
        public List<Medicine> getAllMedicines(){
            List<Medicine> medicines = new ArrayList<>();
            String query = "SELECT * FROM medications";

            try(
            Connection conn = DatabaseHelper.connect();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery())
            {
                while(rs.next()){
                    int med_id = rs.getInt("med_id");
                    String med_name = rs.getString("med_name");
                    String med_dosage = rs.getString("dosage");
                    String frequency = rs.getString("frequency");
                    Date start_date = rs.getDate("start_date");
                    Date end_date = rs.getDate("end_date");
                    String notes = rs.getString("notes");

                    Medicine med = new Medicine(med_id, med_name, med_dosage, frequency, start_date, end_date, notes);
                    medicines.add(med);
                }
            } catch(SQLException e){
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();



            }
          
            return medicines;
    }

    public List<Integer> getAllMedicineIDs(){
        List<Integer> medIDs = new ArrayList<>();
        String query = "SELECT med_id FROM medications";

        try(
        Connection conn = DatabaseHelper.connect();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery())
        {
            while(rs.next()){
                medIDs.add(rs.getInt("med_id"));
            }
        } catch(SQLException e){
            e.printStackTrace();
    }
    return medIDs;
}

public Medicine getMedicineById(int medId) {
    String query = "SELECT * FROM medications WHERE med_id = ?";
    try (Connection conn = DatabaseHelper.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, medId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return new Medicine(
                rs.getInt("med_id"),
                rs.getString("med_name"),
                rs.getString("dosage"),
                rs.getString("frequency"),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getString("notes")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    //get last inserted id
    public int getLastInsertedId(){
        String query = "SELECT LAST_INSERT_ID()";
        try(
        Connection conn = DatabaseHelper.connect();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery()){
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public void updateMedcation(Medicine med){
        String query = "UPDATE medications SET med_name = ?, dosage =?, frequency = ?, start_date = ?, end_date = ?, notes = ? WHERE med_id = ?";

        try(Connection conn = DatabaseHelper.connect();
         PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, med.getMed_name());
            pstmt.setString(2, med.getMed_dosage());
            pstmt.setString(3, med.getMed_frequency());
            pstmt.setDate(4, med.getStart_date());
            pstmt.setDate(5, med.getEnd_date());
            pstmt.setString(6, med.getNotes());
            pstmt.setInt(7, med.getMed_id());
            pstmt.executeUpdate();

            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0){
            System.out.println("Medication updated successfully");}
            else{
                System.out.println("No medication found with the given ID");
            }
         }
            catch(SQLException e){
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
    }



    // public static void main(String[] args){
    //     MedicineDAO medicineDAO = new MedicineDAO();
    //     List<Integer> medicines = medicineDAO.getAllMedicineIDs();
    //     for(int med: medicines){
    //         System.out.println(med);
    //     }
    // }
}