package patientmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Appointment extends JFrame {
    private JTextField doctorNameField, availableTimeField, dateField, contactField;
    private JComboBox<String> specializationCombo;
    private Doctor[] doctors = {
        new Doctor("Dr. Preeti Sapkota", "General Medicine", "2:00 PM", "September 03, 2025", "9876543210"),
        new Doctor("Dr. Aditi Adhikari", "Cardiology", "3:00 PM", "September 04, 2025", "9876543211"),
        new Doctor("Dr. Bhawana karki", "Orthopedics", "10:00 AM", "September 05, 2025", "9876543212")
    };

    public Appointment() {
        setTitle("Doctor Appointment");
        setSize(500, 450); // Increased size for bigger fields
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Header panel for title
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Doctor Appointment Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Larger title font
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center panel for doctor details
        JPanel detailsPanel = new JPanel(new GridLayout(6, 2, 15, 15)); // Increased spacing
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Specialization ComboBox
        JLabel label = new JLabel("Specialization:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label);
        specializationCombo = new JComboBox<>(new String[]{"Select Specialization", "General Medicine", "Cardiology", "Orthopedics"});
        specializationCombo.setFont(new Font("Arial", Font.PLAIN, 16));
        specializationCombo.setSelectedIndex(0); // Start with empty selection
        specializationCombo.addActionListener(e -> updateDoctorDetails());
        detailsPanel.add(specializationCombo);

        // Doctor Name
        JLabel label_1 = new JLabel("Doctor Name:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_1);
        doctorNameField = new JTextField(20);
        doctorNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        doctorNameField.setEditable(false); // Read-only
        detailsPanel.add(doctorNameField);

        // Available Time
        JLabel label_2 = new JLabel("Available Time:");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_2);
        availableTimeField = new JTextField(20);
        availableTimeField.setFont(new Font("Arial", Font.PLAIN, 16));
        availableTimeField.setEditable(false); // Read-only
        detailsPanel.add(availableTimeField);

        // Date
        JLabel label_3 = new JLabel("Date:");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_3);
        dateField = new JTextField(20);
        dateField.setFont(new Font("Arial", Font.PLAIN, 16));
        dateField.setEditable(false); // Read-only
        detailsPanel.add(dateField);

        // Contact
        JLabel label_4 = new JLabel("Contact:");
        label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_4);
        contactField = new JTextField(20);
        contactField.setFont(new Font("Arial", Font.PLAIN, 16));
        contactField.setEditable(false); // Read-only
        detailsPanel.add(contactField);

        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        // Close button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private void updateDoctorDetails() {
        int selectedIndex = specializationCombo.getSelectedIndex();
        if (selectedIndex == 0) { // "Select Specialization" option
            doctorNameField.setText("");
            availableTimeField.setText("");
            dateField.setText("");
            contactField.setText("");
        } else {
            selectedIndex--; // Adjust index to match doctors array (skip "Select Specialization")
            Doctor doctor = doctors[selectedIndex];
            doctorNameField.setText(doctor.getName());
            availableTimeField.setText(doctor.getAvailableTime());
            dateField.setText(doctor.getDate());
            contactField.setText(doctor.getContact());
        }
    }

    // Inner class to hold doctor details
    private class Doctor {
        private String name, specialization, availableTime, date, contact;

        public Doctor(String name, String specialization, String availableTime, String date, String contact) {
            this.name = name;
            this.specialization = specialization;
            this.availableTime = availableTime;
            this.date = date;
            this.contact = contact;
        }

        public String getName() { return name; }
        public String getSpecialization() { return specialization; }
        public String getAvailableTime() { return availableTime; }
        public String getDate() { return date; }
        public String getContact() { return contact; }
    }
}