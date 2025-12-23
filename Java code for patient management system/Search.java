package patientmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Search extends JFrame {
    public Search(String query, PatientManager manager) {
        setTitle("Search Results for: " + query);
        setSize(550, 450); // Slightly larger window to accommodate bigger fields
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Title label
        JLabel titleLabel = new JLabel("Search Results for: " + query);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Larger title font
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Center panel for results with GridBagLayout
        JPanel resultsPanel = new JPanel(new GridBagLayout());
        resultsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Increased spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Get search results
        List<Patient> results = manager.searchPatients(query);
        if (results.isEmpty()) {
            JLabel noResults = new JLabel("No patients found matching the query: " + query);
            noResults.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
            noResults.setHorizontalAlignment(JLabel.CENTER);
            mainPanel.add(noResults, BorderLayout.CENTER);
        } else {
            int row = 0;
            for (Patient p : results) {
                // Panel for each patient
                JPanel patientPanel = new JPanel(new GridBagLayout());
                patientPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Patient"));

                // ID
                gbc.gridx = 0;
                gbc.gridy = 0;
                JLabel idLabel = new JLabel("ID:");
                idLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                patientPanel.add(idLabel, gbc);
                gbc.gridx = 1;
                JTextField idField = new JTextField(p.getId());
                idField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                idField.setColumns(20); // Bigger field
                idField.setEditable(false); // Read-only
                patientPanel.add(idField, gbc);

                // Name
                gbc.gridx = 0;
                gbc.gridy = 1;
                JLabel nameLabel = new JLabel("Name:");
                nameLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                patientPanel.add(nameLabel, gbc);
                gbc.gridx = 1;
                JTextField nameField = new JTextField(p.getName());
                nameField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                nameField.setColumns(20); // Bigger field
                nameField.setEditable(false); // Read-only
                patientPanel.add(nameField, gbc);

                // Age
                gbc.gridx = 0;
                gbc.gridy = 2;
                JLabel ageLabel = new JLabel("Age:");
                ageLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                patientPanel.add(ageLabel, gbc);
                gbc.gridx = 1;
                JTextField ageField = new JTextField(String.valueOf(p.getAge()));
                ageField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                ageField.setColumns(20); // Bigger field
                ageField.setEditable(false); // Read-only
                patientPanel.add(ageField, gbc);

                // Gender
                gbc.gridx = 0;
                gbc.gridy = 3;
                JLabel genderLabel = new JLabel("Gender:");
                genderLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                patientPanel.add(genderLabel, gbc);
                gbc.gridx = 1;
                JTextField genderField = new JTextField(p.getGender());
                genderField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                genderField.setColumns(20); // Bigger field
                genderField.setEditable(false); // Read-only
                patientPanel.add(genderField, gbc);

                // Contact
                gbc.gridx = 0;
                gbc.gridy = 4;
                JLabel contactLabel = new JLabel("Contact:");
                contactLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                patientPanel.add(contactLabel, gbc);
                gbc.gridx = 1;
                JTextField contactField = new JTextField(p.getContact());
                contactField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                contactField.setColumns(20); // Bigger field
                contactField.setEditable(false); // Read-only
                patientPanel.add(contactField, gbc);

                // Medical History
                gbc.gridx = 0;
                gbc.gridy = 5;
                JLabel historyLabel = new JLabel("Medical History:");
                historyLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                patientPanel.add(historyLabel, gbc);
                gbc.gridx = 1;
                JTextField historyField = new JTextField(p.getMedicalHistory());
                historyField.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font
                historyField.setColumns(20); // Bigger field
                historyField.setEditable(false); // Read-only
                patientPanel.add(historyField, gbc);

                // Add patient panel to results panel
                gbc.gridx = 0;
                gbc.gridy = row++;
                gbc.gridwidth = 2;
                resultsPanel.add(patientPanel, gbc);
            }

            // Add results panel to main panel
            JScrollPane scrollPane = new JScrollPane(resultsPanel);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
        }

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16)); // Larger font
        closeButton.addActionListener(e -> dispose());
        mainPanel.add(closeButton, BorderLayout.SOUTH);

        add(mainPanel);
    }
}