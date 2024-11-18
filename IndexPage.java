import javax.swing.*;
import java.awt.*;

public class IndexPage extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private UserAuthentication userAuth;

    public IndexPage(CardLayout cardLayout, JPanel cardPanel, UserAuthentication userAuth) {
        // Set up the JFrame
        this.userAuth = userAuth;  // Set userAuth
        setTitle("Quiz Application");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create the CardLayout panel to hold different screens
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        // Initialize the main panel (IndexPage), login panel, and register panel
        cardPanel.add(createMainPanel(), "IndexPage");
        cardPanel.add(new LoginPanel(cardLayout, cardPanel, userAuth).getLoginPanel(), "LoginPage");
        cardPanel.add(new RegisterPanel(cardLayout, cardPanel, userAuth).getRegisterPanel(), "RegisterPage");

        // Set the initial panel to IndexPage
        cardLayout.show(cardPanel, "IndexPage");

        // Add the card panel to the frame
        add(cardPanel);
        setVisible(true);  // Make the frame visible
    }

    // Create the main panel with buttons for Login and Signup
    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Title label
        JLabel titleLabel = new JLabel("Quiz Application");
        titleLabel.setFont(new Font("Calibre", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setMaximumSize(new Dimension(200, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFocusable(false);
        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "LoginPage"));

        // Signup button
        JButton signupButton = new JButton("Signup");
        signupButton.setPreferredSize(new Dimension(200, 40));
        signupButton.setMaximumSize(new Dimension(200, 40));
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setFocusable(false);
        signupButton.addActionListener(e -> cardLayout.show(cardPanel, "RegisterPage"));

        // Add components to the main panel with spacing
        mainPanel.add(Box.createVerticalStrut(100)); // Space above the title
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(50)); // Space between title and buttons
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalStrut(20)); // Space between buttons
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

            // Create and add IndexPage and RegisterPanel
            new IndexPage(cardLayout, cardPanel, userAuth);
        });
    }
}
