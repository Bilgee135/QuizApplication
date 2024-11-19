import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegisterPanel {
    public JPanel registerPanel;
    private UserAuthentication userAuth;  // Instance of UserAuthentication

    public RegisterPanel(CardLayout cardLayout, JPanel cardPanel, UserAuthentication userAuth) {
        this.userAuth = userAuth;
        registerPanel = new JPanel(new BorderLayout());

        // Top panel with back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.setFocusable(false);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "LoginPage"));
        topPanel.add(backButton);
        registerPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel for registration components
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // Title label
        JLabel titleLabel = new JLabel("Create New Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username field
        JTextField registerUsernameField = new JTextField("Username");
        registerUsernameField.setPreferredSize(new Dimension(200, 30));
        registerUsernameField.setMaximumSize(new Dimension(200, 30));
        registerUsernameField.setHorizontalAlignment(JTextField.LEFT);
        registerUsernameField.setForeground(Color.LIGHT_GRAY); // Set placeholder color

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
        fullNameField.setPreferredSize(new Dimension(200, 30));
        fullNameField.setMaximumSize(new Dimension(200, 30));
        fullNameField.setHorizontalAlignment(JTextField.LEFT);
        fullNameField.setForeground(Color.LIGHT_GRAY); // Set placeholder color

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
        registerPasswordField.setPreferredSize(new Dimension(200, 30));
        registerPasswordField.setMaximumSize(new Dimension(200, 30));
        registerPasswordField.setHorizontalAlignment(JTextField.LEFT);
        registerPasswordField.setForeground(Color.LIGHT_GRAY); // Set placeholder color

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
        confirmPasswordField.setPreferredSize(new Dimension(200, 30));
        confirmPasswordField.setMaximumSize(new Dimension(200, 30));
        confirmPasswordField.setHorizontalAlignment(JTextField.LEFT);
        confirmPasswordField.setForeground(Color.LIGHT_GRAY); // Set placeholder color

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
        registerButton.setPreferredSize(new Dimension(200, 40));
        registerButton.setMaximumSize(new Dimension(200, 40));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setFocusable(false);

        // Add action listener for register button
        registerButton.addActionListener(e -> {
            String username = registerUsernameField.getText().trim(); // Correct field name
            String fullName = fullNameField.getText().trim(); // Get the full name
            String password = new String(registerPasswordField.getPassword()).trim(); // Correct field name
            String confirmPassword = new String(confirmPasswordField.getPassword()).trim(); // Correct field name

            // Check if passwords match
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
                return;
            }

            // Call the register method of UserAuthentication
            if (userAuth.register(username, fullName, password)) { // Register with fullName
                JOptionPane.showMessageDialog(null, "Registration Successful!");
                cardLayout.show(cardPanel, "IndexPage");  // Navigate back to index page
            } else {
                JOptionPane.showMessageDialog(null, "Username already taken. Please try again.");
            }
        });

        // Add components to center panel
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(registerUsernameField); // Correct field name
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(fullNameField); // Add full name field
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(registerPasswordField); // Correct field name
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(confirmPasswordField); // Correct field name
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(registerButton);

        // Adjust the size and alignment
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Ensure center alignment
        registerPanel.add(centerPanel, BorderLayout.CENTER);
    }

    public JPanel getRegisterPanel() {
        return registerPanel;
    }
}
