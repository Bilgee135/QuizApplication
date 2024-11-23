package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import dataModel.*;
import customization.*;

public class RegisterPanel {
    public JPanel registerPanel;
    private UserAuthentication userAuth;  // Instance of dataModel.UserAuthentication

    public RegisterPanel(CardLayout cardLayout, JPanel cardPanel, UserAuthentication userAuth) {
        this.userAuth = userAuth;
        registerPanel = new JPanel(new BorderLayout());

        // Top panel with back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        StyleButton.styleButton(backButton);
        backButton.setMaximumSize(new Dimension(100,30));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "main.Index"));
        topPanel.setBackground(ColorChoice.BACKGROUND);
        topPanel.add(backButton);
        registerPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel for registration components
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(ColorChoice.BACKGROUND);

        // Title label
        JLabel titleLabel = new JLabel("Create a new account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(ColorChoice.TEXT_COLOR);

        // Username field
        JTextField registerUsernameField = new JTextField("Username");
        FieldStyle(registerUsernameField);

        registerUsernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (registerUsernameField.getText().equals("Username")) {
                    registerUsernameField.setText(""); // Remove placeholder text
                    registerUsernameField.setForeground(Color.BLACK); // Reset text color
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (registerUsernameField.getText().isEmpty()) {
                    registerUsernameField.setForeground(Color.LIGHT_GRAY); // Placeholder text color
                    registerUsernameField.setText("Username"); // Reset to placeholder text
                }
            }
        });

        // Full Name field
        JTextField fullNameField = new JTextField("Full Name");
        FieldStyle(fullNameField);

        fullNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (fullNameField.getText().equals("Full Name")) {
                    fullNameField.setText(""); // Remove placeholder text
                    fullNameField.setForeground(Color.BLACK); // Reset text color
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (fullNameField.getText().isEmpty()) {
                    fullNameField.setForeground(Color.LIGHT_GRAY); // Placeholder text color
                    fullNameField.setText("Full Name"); // Reset to placeholder text
                }
            }
        });

        // Password field
        JPasswordField registerPasswordField = new JPasswordField("Password");
        FieldStyle(registerPasswordField);

        registerPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(registerPasswordField.getPassword()).equals("Password")) {
                    registerPasswordField.setText(""); // Remove placeholder text
                    registerPasswordField.setForeground(Color.BLACK); // Reset text color
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(registerPasswordField.getPassword()).isEmpty()) {
                    registerPasswordField.setForeground(Color.LIGHT_GRAY); // Placeholder text color
                    registerPasswordField.setText("Password"); // Reset to placeholder text
                }
            }
        });

        // Confirm Password field
        JPasswordField confirmPasswordField = new JPasswordField("Confirm Password");
        FieldStyle(confirmPasswordField);

        confirmPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(confirmPasswordField.getPassword()).equals("Confirm Password")) {
                    confirmPasswordField.setText(""); // Remove placeholder text
                    confirmPasswordField.setForeground(Color.BLACK); // Reset text color
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(confirmPasswordField.getPassword()).isEmpty()) {
                    confirmPasswordField.setForeground(Color.LIGHT_GRAY); // Placeholder text color
                    confirmPasswordField.setText("Confirm Password"); // Reset to placeholder text
                }
            }
        });

        // Register button
        JButton registerButton = new JButton("Register");
        StyleButton.styleButton(registerButton);
        registerButton.setPreferredSize(new Dimension(200, 40));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add action listener for register button
        registerButton.addActionListener(e -> {
            String username = registerUsernameField.getText().trim(); // Correct field name
            String fullName = fullNameField.getText().trim(); // Get the full name
            String password = new String(registerPasswordField.getPassword()).trim(); // Correct field name
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim(); // Correct field name
            boolean nullCheck = username.equals("") || fullName.equals("")
                    || password.equals("") || confirmPassword.equals("");
            boolean placeHolderCheck = username.equals("Username") || fullName.equals("Full Name") ||
                    password.equals("Password") || confirmPassword.equals("Confirm Password");

            // Password checks
            if(nullCheck || placeHolderCheck) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
                return;
            }

            // Call the register method of dataModel.UserAuthentication
            if (userAuth.register(username, fullName, password)) {
                JOptionPane.showMessageDialog(null, "Registration Successful!");
                cardLayout.show(cardPanel, "main.Index");  // Navigate back to index page
            } else {
                JOptionPane.showMessageDialog(null, "Username already taken. Please try again.");
            }
        });

        // Add components to center panel
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(registerUsernameField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(fullNameField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(registerPasswordField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(confirmPasswordField);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(registerButton);

        // Size and alignment to be at the center of the screen
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerPanel.add(centerPanel, BorderLayout.CENTER);
    }

    public static void FieldStyle(JTextField textField) {
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setMaximumSize(new Dimension(200, 30));
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setForeground(Color.LIGHT_GRAY); // Set placeholder color
    }
    public static void FieldStyle(JPasswordField passwordField) {
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setMaximumSize(new Dimension(200, 30));
        passwordField.setHorizontalAlignment(JTextField.LEFT);
        passwordField.setForeground(Color.LIGHT_GRAY);
    }

    public JPanel getRegisterPanel() {
        return registerPanel;
    }
}
