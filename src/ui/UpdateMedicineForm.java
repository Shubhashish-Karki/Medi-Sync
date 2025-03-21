package ui;
import javax.swing.*;
import database.MedicineDAO;
import models.Medicine;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateMedicineForm extends JFrame{
    private JComboBox<Integer> medicineDropdown;
    private JTextField nameField, dosageField, frequencyField,StartField,EndField;
    private JTextArea notesField;
    private JButton saveButton;
    private MedicineDAO dao;

    public UpdateMedicineForm() {
        dao = new MedicineDAO();
        setTitle("Update Medicine");
        setSize(400, 300);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Select Medicine ID:"));
        medicineDropdown = new JComboBox<>();
        loadMedicineIDs();
        add(medicineDropdown);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Dosage:"));
        dosageField = new JTextField();
        add(dosageField);

        add(new JLabel("Frequency:"));
        frequencyField = new JTextField();
        add(frequencyField);

        add(new JLabel("Start Date:"));
        StartField = new JTextField();
        add(StartField);

        add(new JLabel("End Date:"));
        EndField = new JTextField();
        add(EndField);

        add(new JLabel("Notes:"));
        notesField = new JTextArea();
        add(notesField);

        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> updateMedicine());
        add(saveButton);

        medicineDropdown.addActionListener(e -> loadMedicineDetails());

        setVisible(true);
    }

    private void loadMedicineIDs(){
        List<Integer> medicineIDs = dao.getAllMedicineIDs();
        for (Integer id : medicineIDs) {
            medicineDropdown.addItem(id);
        }
    }
    private void loadMedicineDetails(){
        int selectedID = (int) medicineDropdown.getSelectedItem();
        Medicine med = dao.getMedicineById(selectedID);

        if (med != null ){
            nameField.setText(med.getMed_name());
            dosageField.setText(med.getMed_dosage());
            frequencyField.setText(med.getMed_frequency());
            StartField.setText(med.getStart_date().toString());
            EndField.setText(med.getEnd_date().toString());
            notesField.setText(med.getNotes());
        }
    }

    private void updateMedicine() {
        int selectedId = (int) medicineDropdown.getSelectedItem();
        Medicine updatedMedicine = new Medicine(
                selectedId,
                nameField.getText(),
                dosageField.getText(),
                frequencyField.getText(),
                StartField.getText().equals("") ? null : java.sql.Date.valueOf(StartField.getText()),
                EndField.getText().equals("") ? null : java.sql.Date.valueOf(EndField.getText()),
                notesField.getText()
        );

        dao.updateMedcation(updatedMedicine);
        JOptionPane.showMessageDialog(this, "Medicine updated successfully");
        dispose();
    }


}
