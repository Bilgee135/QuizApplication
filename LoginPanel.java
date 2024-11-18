import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel {
    private JPanel loginPanel;
    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private UserAuthentication userAuth;
    private JLabel errorLabel;  // Move error label here to manage visibility

    // Modify constructor to accept userAuth
    public LoginPanel(CardLayout cardLayout, JPanel cardPanel, UserAuthentication userAuth) {
        this.userAuth = userAuth;  // Initialize userAuth here
        loginPanel = new JPanel(new BorderLayout());

        // Top panel with back button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.setFocusable(false);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "IndexPage"));
        topPanel.add(backButton);
        loginPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel for login components
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);

        // Title label
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Username field
        loginUsernameField = new JTextField(20);  // Set preferred size
        loginUsernameField.setMaximumSize(new Dimension(200, 30));
        loginUsernameField.setHorizontalAlignment(JTextField.LEFT);

        // Password field
        loginPasswordField = new JPasswordField(20);  // Set preferred size
        loginPasswordField.setMaximumSize(new Dimension(200, 30));
        loginPasswordField.setHorizontalAlignment(JPasswordField.LEFT);

        // Error message label (Initially hidden)
        errorLabel = new JLabel("Incorrect username or password");
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setVisible(false);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setMaximumSize(new Dimension(200, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFocusable(false);

        loginButton.addActionListener(e -> {
            String username = loginUsernameField.getText().trim();  // Trim username input
            String password = new String(loginPasswordField.getPassword()).trim();  // Trim password input
            if (userAuth.login(username, password)) {  // Use the login method from UserAuthentication
                JOptionPane.showMessageDialog(this.getLoginPanel(), "Login successful!");
                errorLabel.setVisible(false);  // Hide error label on successful login
            } else {
                JOptionPane.showMessageDialog(this.getLoginPanel(), "Invalid username or password.");
                errorLabel.setVisible(true);  // Show error label on failed login
            }
        });

        JLabel forgotPasswordLabel = new JLabel("<html>Forgot Password?</html>", SwingConstants.CENTER);
        forgotPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the password retrieval dialog
                openForgotPasswordDialog();
            }
        });

        // Register link label below login button
        JLabel registerLinkLabel = new JLabel("<html>Don't have an account? <a href=''>Register Now!</a></html>", SwingConstants.CENTER);
        registerLinkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerLinkLabel.setForeground(Color.BLACK);
        registerLinkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        registerLinkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(cardPanel, "RegisterPage");
            }
        });

        // Add components to the center panel
        centerPanel.add(Box.createVerticalStrut(50));
        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(loginUsernameField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(loginPasswordField);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(errorLabel);  // Error label added to the panel
        centerPanel.add(forgotPasswordLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(loginButton);
        centerPanel.add(Box.createVerticalStrut(10)); // Spacer between login button and register link
        centerPanel.add(registerLinkLabel);

        // Add the center panel to the main panel
        loginPanel.add(centerPanel, BorderLayout.CENTER);
    }

    // Method to open the "Forgot Password" dialog
    private void openForgotPasswordDialog() {
        // Create a dialog for password retrieval
        JTextField fullNameField = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter your full name:"));
        panel.add(fullNameField);

        int option = JOptionPane.showConfirmDialog(null, panel, "Forgot Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String fullName = fullNameField.getText().trim();
            String password = userAuth.getPasswordByFullName(fullName);

            if (password != null) {
                JOptionPane.showMessageDialog(null, "Your password is: " + password);
            } else {
                JOptionPane.showMessageDialog(null, "No user found with that full name.");
            }
        }
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }
}
