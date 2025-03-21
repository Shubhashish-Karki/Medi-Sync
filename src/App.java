import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import database.MedicineDAO;
import models.Medicine;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JScrollPane;
import ui.CustomPanel;
import ui.UpdateMedicineForm;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Medicine Tracker");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Background Image
        CustomPanel panel = new CustomPanel("/resources/images/background.jpg");
        frame.add(panel);
        // JPanel panel = new JPanel();
        // frame.add(panel);
        // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add Medication");
        JButton viewButton  = new JButton("View Medications");
        JButton updateButton = new JButton("Update Medication");

        //Add "Add" button
        panel.add(addButton);

        //Add "View" button
        panel.add(viewButton);

        //Add "Update" button
        panel.add(updateButton);

        //  add ActionListener
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a form/dialog to add medication
                JFrame addFrame = new JFrame("Add Medication");
                addFrame.setSize(400, 400);
                addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel addPanel = new JPanel();
                addPanel.setLayout(new GridLayout(7, 2));  // 7 rows, 2 columns
                addFrame.add(addPanel);

                JLabel nameLabel = new JLabel("Medication Name:");
                JTextField nameField = new JTextField(20);
                JLabel dosageLabel = new JLabel("Dosage:");
                JTextField dosageField = new JTextField(20);
                JLabel freqLabel = new JLabel("Frequency:");
                JTextField freqField = new JTextField(20);
                JLabel startLabel = new JLabel("Start Date (yyyy-mm-dd):");
                JTextField startField = new JTextField(20);
                JLabel endLabel = new JLabel("End Date (yyyy-mm-dd):");
                JTextField endField = new JTextField(20);
                JLabel notesLabel = new JLabel("Notes:");
                JTextArea notesArea = new JTextArea(5, 20);

                

                JButton submitButton = new JButton("Add Medication");

                addPanel.add(nameLabel);
                addPanel.add(nameField);
                addPanel.add(dosageLabel);
                addPanel.add(dosageField);
                addPanel.add(freqLabel);
                addPanel.add(freqField);
                addPanel.add(startLabel);
                addPanel.add(startField);
                addPanel.add(endLabel);
                addPanel.add(endField);
                addPanel.add(notesLabel);
                addPanel.add(new JScrollPane(notesArea));
                addPanel.add(submitButton);

                // Action when submitting the form
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String dosage = dosageField.getText();
                        String frequency = freqField.getText();
                        String startDateStr = startField.getText();
                        String endDateStr = endField.getText();
                        String notes = notesArea.getText();

                        try {
                            // Convert start and end dates to java.sql.Date
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date startDate = sdf.parse(startDateStr);
                            java.util.Date endDate = sdf.parse(endDateStr);
                            Date sqlStartDate = new Date(startDate.getTime());
                            Date sqlEndDate = new Date(endDate.getTime());
                            
                            //(int med_id, String med_name, String med_dosage, String frequency, Date start_date, Date end_date, String notes) {
                            // Create a Medicine object
                            Medicine med = new Medicine(name, dosage, frequency, sqlStartDate, sqlEndDate, notes);

                            // Use MedicineDAO to insert the medication
                            MedicineDAO medicineDAO = new MedicineDAO();
                            medicineDAO.addMedication(med);

                            // Close the form after submission
                            addFrame.dispose();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(addFrame, "Error adding medication: " + ex.getMessage());
                        }
                    }
                });

                addFrame.setVisible(true);
            }
        });


        //view ActionListener
        viewButton.addActionListener(e -> {
            // Create a new JFrame for viewing medications
            JFrame viewFrame = new JFrame("View Medications");
            viewFrame.setSize(600, 500);
            viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel viewPanel = new JPanel();
            viewFrame.add(viewPanel);

            //Create a Jtable to display the Medication

            String[] columns = {"ID", "Name", "Dosage", "Frequency", "Start Date", "End Date", "Notes"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);
            JTable table = new JTable(model);

            //Fetch all the medications from the database
            MedicineDAO medicineDAO = new MedicineDAO();
            List<Medicine> medicines = medicineDAO.getAllMedicines();

            //Populate the table with medicine data
            for (Medicine med : medicines) {
                Object[] row = {
                    med.getMed_id(), 
                    med.getMed_name(),
                    med.getMed_dosage(),
                    med.getMed_frequency(), 
                    med.getStart_date(), 
                    med.getEnd_date(), 
                    med.getNotes()
                };
                    model.addRow(row);
            }
        
            JScrollPane scrollPane = new JScrollPane(table);
            viewPanel.add(scrollPane);

            viewFrame.setVisible(true);
        });


        //update ActionListener
        updateButton.addActionListener(e ->new UpdateMedicineForm());
        frame.setVisible(true);
    }
}
