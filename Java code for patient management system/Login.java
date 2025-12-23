package patientmanagementsystem;

import javax.swing.*;
import java.awt.*;

public class Billing extends JFrame {
    public Billing(Patient patient) {
        setTitle("Billing Details");
        setSize(500, 400); // Larger window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Header panel for title
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Billing Details for " + patient.getName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Larger title font
        headerPanel.add(titleLabel);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Center panel for billing details
        JPanel detailsPanel = new JPanel(new GridLayout(6, 2, 15, 15)); // 6 rows, with spacing
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Patient ID
        JLabel label = new JLabel("Patient ID:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label);
        JTextField idField = new JTextField(patient.getId());
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        idField.setEditable(false); // Read-only
        idField.setColumns(20);
        detailsPanel.add(idField);

        // Patient Name
        JLabel label_1 = new JLabel("Patient Name:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_1);
        JTextField nameField = new JTextField(patient.getName());
        nameField.setFont(new Font("Arial", Font.PLAIN, 16));
        nameField.setEditable(false); // Read-only
        nameField.setColumns(20);
        detailsPanel.add(nameField);

        // Condition
        JLabel label_2 = new JLabel("Condition:");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_2);
        JTextField conditionField = new JTextField(patient.getMedicalHistory());
        conditionField.setFont(new Font("Arial", Font.PLAIN, 16));
        conditionField.setEditable(false); // Read-only
        conditionField.setColumns(20);
        detailsPanel.add(conditionField);

        // Consultation Fee
        JLabel label_3 = new JLabel("Consultation Fee:");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_3);
        JTextField consultationFeeField = new JTextField("Rs5000"); // Fixed fee in Rupees
        consultationFeeField.setFont(new Font("Arial", Font.PLAIN, 16));
        consultationFeeField.setEditable(false); // Read-only
        consultationFeeField.setColumns(20);
        detailsPanel.add(consultationFeeField);

        // Additional Charges (based on condition)
        String condition = patient.getMedicalHistory().split(" on ")[0]; // Extract condition
        String additionalCharges = getAdditionalCharges(condition);
        JLabel label_4 = new JLabel("Additional Charges:");
        label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_4);
        JTextField additionalChargesField = new JTextField(additionalCharges);
        additionalChargesField.setFont(new Font("Arial", Font.PLAIN, 16));
        additionalChargesField.setEditable(false); // Read-only
        additionalChargesField.setColumns(20);
        detailsPanel.add(additionalChargesField);

        // Total Amount
        double consultationFee = 5000.00; // Updated to Rupees
        double additionalFee = Double.parseDouble(additionalCharges.replace("Rs", "").replace(".00", "")); // Parse Rs values
        double total = consultationFee + additionalFee;
        JLabel label_5 = new JLabel("Total Amount:");
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        detailsPanel.add(label_5);
        JTextField totalField = new JTextField("Rs" + String.format("%.2f", total));
        totalField.setFont(new Font("Arial", Font.PLAIN, 16));
        totalField.setEditable(false); // Read-only
        totalField.setColumns(20);
        detailsPanel.add(totalField);

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

    private String getAdditionalCharges(String condition) {
        switch (condition.toLowerCase()) {
            case "fever":
                return "Rs1000"; // Example charge in Rupees
            case "headache":
                return "Rs500"; // Example charge in Rupees
            case "cold":
                return "Rs800"; // Example charge in Rupees
            default:
                return "Rs0"; // Default no additional charge in Rupees
        }
    }
}