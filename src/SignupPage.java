import javax.swing.*;
import java.awt.*;

public class SignupPage extends JFrame {

    public SignupPage() {
        // Set up the JFrame
        setTitle("Create a new account");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout()); // Use BorderLayout for the main frame

        // Back button at the top-left corner
        JButton backButton = new JButton("Back");
        backButton.setFocusable(false);
        backButton.addActionListener(e -> {
            new IndexPage();
            dispose(); // Close current LoginPage
        });

        // Add the back button to the top-left corner
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        // Create a main panel to hold the login components in the center
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // Title label
        JLabel titleLabel = new JLabel("Signup");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username field
        JTextField usernameField = new JTextField("Username");
        usernameField.setPreferredSize(new Dimension(200, 30));
        usernameField.setMaximumSize(new Dimension(200, 30));
        usernameField.setHorizontalAlignment(JTextField.LEFT);

        // Password field
        JPasswordField passwordField = new JPasswordField("Password");
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setMaximumSize(new Dimension(200, 30));
        passwordField.setHorizontalAlignment(JPasswordField.LEFT);

        // Error message label (hidden initially)
        JLabel errorLabel = new JLabel("Invalid password");
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setVisible(false); // Hide initially

        // Login button
        JButton signupButton = new JButton("Signup");
        signupButton.setPreferredSize(new Dimension(200, 40));
        signupButton.setMaximumSize(new Dimension(200, 40));
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setFocusable(false);

        // Add action listener for login button
        signupButton.addActionListener(e -> {
            // Actions to be added
        });

        // Add components to the center panel with spacing
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(usernameField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(errorLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(signupButton);

        // Add the center panel to the frame
        add(centerPanel, BorderLayout.CENTER);

        // Set the frame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // This method is used to ensure the UI behaves correct
        SwingUtilities.invokeLater(() -> new SignupPage());
    }
}
