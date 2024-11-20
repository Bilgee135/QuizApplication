import javax.swing.*;
import java.awt.*;

public class Index extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private UserAuthentication userAuth;

    public Index(CardLayout cardLayout, JPanel cardPanel, UserAuthentication userAuth) {
        // Set up the JFrame
        this.userAuth = userAuth;  // Set userAuth
        setTitle("Quiz Application");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create the CardLayout panel to hold different screens
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        // Initialize the main panel (Index), login panel, and register panel
        cardPanel.add(createMainPanel(), "Index");
        cardPanel.add(new LoginPanel(cardLayout, cardPanel, userAuth).getLoginPanel(), "LoginPage");
        cardPanel.add(new RegisterPanel(cardLayout, cardPanel, userAuth).getRegisterPanel(), "RegisterPage");

        MainDashboard mainDashboard = new MainDashboard(cardLayout, cardPanel);
        cardPanel.add(mainDashboard.getDashboardPanel(), "MainDashboard");

        cardLayout.show(cardPanel, "Index"); // sets the first page to be Index.java

        add(cardPanel);
        setVisible(true);
    }

    // Create the main panel with buttons for Login and Signup
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.decode("#EBF2FA"));

        // Title label
        JLabel titleLabel = new JLabel("Quiz Application");
        titleLabel.setFont(new Font("Calibre", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.decode("#427AA1"));

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setMaximumSize(new Dimension(200, 40));
        loginButton.setBackground(Color.decode("#064789"));
        loginButton.setForeground(Color.decode("#EBF2FA"));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFocusable(false);
        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "LoginPage"));

        // Signup button
        JButton signupButton = new JButton("Signup");
        signupButton.setPreferredSize(new Dimension(200, 40));
        signupButton.setMaximumSize(new Dimension(200, 40));
        signupButton.setBackground(Color.decode("#064789"));
        signupButton.setForeground(Color.decode("#EBF2FA"));
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setFocusable(false);
        signupButton.addActionListener(e -> cardLayout.show(cardPanel, "RegisterPage"));

        // Add components to the main panel with spacing
        mainPanel.add(Box.createVerticalStrut(100));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(signupButton);

        return mainPanel;
    }

    // Main method to initialize the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserAuthentication userAuth = new UserAuthentication();  // Create an instance of UserAuthentication

            // Create the CardLayout and cardPanel to hold different panels
            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            // Create and add Index and RegisterPanel
            new Index(cardLayout, cardPanel, userAuth);
        });
    }
}
