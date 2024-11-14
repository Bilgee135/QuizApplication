import javax.swing.*;
import java.awt.*;

public class IndexPage extends JFrame {

    public IndexPage() {
        // Set up the JFrame
        setTitle("Quiz Application");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create a main panel with a vertical layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE); // Set background to white

        // Title label
        JLabel titleLabel = new JLabel("Quiz Application");
        titleLabel.setFont(new Font("Calibre", Font.BOLD, 30));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the title

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setMaximumSize(new Dimension(200, 40)); // Limit the button size
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFocusable(false);

        // Signup button
        JButton signupButton = new JButton("Signup");
        signupButton.setPreferredSize(new Dimension(200, 40));
        signupButton.setMaximumSize(new Dimension(200, 40)); // Limit the button size
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setFocusable(false);

        loginButton.addActionListener(e -> {
            new LoginPage();
            dispose(); // Close the IndexPage
        });

        signupButton.addActionListener(e -> {
            new SignupPage();
            dispose();
        });

        // Add components to the panel with spacing
        mainPanel.add(Box.createVerticalStrut(100)); // Space above the title
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(50)); // Space between title and buttons
        mainPanel.add(loginButton);
        mainPanel.add(Box.createVerticalStrut(20)); // Space between buttons
        mainPanel.add(signupButton);

        // Add the main panel to the frame
        add(mainPanel);

        // Set the frame to be visible
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the first page
        // This method is used to ensure the UI behaves correct
        SwingUtilities.invokeLater(() -> new IndexPage());
    }
}
