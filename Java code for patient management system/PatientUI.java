package patientmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Patient {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String medicalHistory;

    public Patient(String id, String name, int age, String gender, String contact, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.medicalHistory = medicalHistory;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getContact() { return contact; }
    public String getMedicalHistory() { return medicalHistory; }
}

class PatientManager {
    private List<Patient> patients;
    private int nextId = 1;

    public PatientManager() {
        patients = new ArrayList<>();
    }

    public void addPatient(String name, int age, String gender, String contact, String medicalHistory) {
        String id = "P" + nextId++;
        Patient patient = new Patient(id, name, age, gender, contact, medicalHistory);
        patients.add(patient);
    }

    public List<Patient> searchPatients(String query) {
        List<Patient> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            return results;
        }
        for (Patient patient : patients) {
            if (patient.getName().toLowerCase().contains(query.toLowerCase()) ||
                patient.getId().toLowerCase().contains(query.toLowerCase()) ||
                patient.getMedicalHistory().toLowerCase().contains(query.toLowerCase())) {
                results.add(patient);
            }
        }
        return results;
    }

    // Method to get the last added patient
    public Patient getLastPatient() {
        if (patients.isEmpty()) return null;
        return patients.get(patients.size() - 1);
    }
}

public class PatientUI extends JFrame {
    private PatientManager manager;
    private JTextField nameField, ageField, contactField, searchField, conditionField;
    private JComboBox<String> genderCombo;
    private JLabel dateLabel;
    private JTextArea resultArea;

    public PatientUI() {
        manager = new PatientManager();
        setTitle("Patient Management System");
        setSize(600, 650); // Increased height for new button
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Date Label at the Top
        dateLabel = new JLabel();
        updateDate();
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(dateLabel, BorderLayout.NORTH);

        // Add Patient Panel
        JPanel addPanel = new JPanel(new GridLayout(8, 2)); // Increased rows for new button
        addPanel.setBorder(BorderFactory.createTitledBorder("Add Patient"));
        addPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        addPanel.add(nameField);
        addPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        addPanel.add(ageField);
        addPanel.add(new JLabel("Gender:"));
        genderCombo = new JComboBox<>(new String[]{"", "Female", "Male", "Other"});
        addPanel.add(genderCombo);
        addPanel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        addPanel.add(contactField);
        addPanel.add(new JLabel("Condition:"));
        conditionField = new JTextField();
        addPanel.add(conditionField);

        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> addPatient());
        addPanel.add(addButton);

        JButton appointmentButton = new JButton("Doctor Appointment");
        appointmentButton.addActionListener(e -> openAppointment());
        addPanel.add(appointmentButton);

        JButton billingButton = new JButton("Billing");
        billingButton.addActionListener(e -> openBilling());
        addPanel.add(billingButton);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search Patient"));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchPatients());
        searchPanel.add(new JLabel("Search by Name/ID/Condition:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Result Area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScroll = new JScrollPane(resultArea);

        // Add to frame
        add(addPanel, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
        add(resultScroll, BorderLayout.EAST);

        setVisible(true);
    }

    private void updateDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy hh:mm a z");
        String currentDate = dateFormat.format(new Date());
        dateLabel.setText("Current Date and Time: " + currentDate);
    }

    private void addPatient() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = (String) genderCombo.getSelectedItem();
            if (gender == null || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a gender!");
                return;
            }
            String contact = contactField.getText().trim();
            if (!contact.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(this, "Invalid number! Please enter a 10-digit phone number.");
                return;
            }
            String condition = conditionField.getText().trim();
            if (condition.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a condition!");
                return;
            }
            String medicalHistory = condition + " on " + new SimpleDateFormat("MMMM dd, yyyy").format(new Date());
            manager.addPatient(name, age, gender, contact, medicalHistory);
            JOptionPane.showMessageDialog(this, "Patient added successfully!");
            clearAddFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid age format!");
        }
    }

    private void openAppointment() {
        new Appointment().setVisible(true);
    }

    private void openBilling() {
        Patient patient = manager.getLastPatient();
        if (patient != null) {
            new Billing(patient).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No patient added yet!", "Billing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchPatients() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            new Search(query, manager).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a search query!");
        }
    }

    private void clearAddFields() {
        nameField.setText("");
        ageField.setText("");
        genderCombo.setSelectedIndex(0);
        contactField.setText("");
        conditionField.setText("");
    }

    private static void main(String[] args) {
        // This main method is not used; login handles the launch
    }
}