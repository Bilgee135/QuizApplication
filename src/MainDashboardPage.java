import javax.swing.*;
import java.awt.*;

public class MainDashboardPage extends JFrame {

    public MainDashboardPage(String username) {
        // Set up the JFrame
        setTitle("Quiz Application - Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel setup
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Hello " + username + "!");
        welcomeLabel.setFont(new Font("Calibre", Font.BOLD, 30));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Menu label
        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setFont(new Font("Calibre", Font.BOLD, 18));
        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button panel for menu buttons
        JPanel menuButtonPanel = new JPanel();
        menuButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        menuButtonPanel.setBackground(Color.WHITE);

        JButton leaderboardButton = new JButton("See leaderboard");
        leaderboardButton.setPreferredSize(new Dimension(150, 40));
        leaderboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardButton.setFocusable(false);

        JButton logoutButton = new JButton("Log out");
        logoutButton.setPreferredSize(new Dimension(150, 40));
        logoutButton.setFocusable(false);

        menuButtonPanel.add(leaderboardButton);
        menuButtonPanel.add(logoutButton);

        // Quiz topics label
        JLabel topicsLabel = new JLabel("Quiz Topics");
        topicsLabel.setFont(new Font("Calibre", Font.BOLD, 18));
        topicsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel for quiz topics buttons
        JPanel topicsPanel = new JPanel();
        topicsPanel.setLayout(new GridLayout(2, 3, 30, 30)); // 2 rows, 3 columns
        topicsPanel.setBackground(Color.WHITE);

        // Add static topic buttons as placeholders
        for (int i = 1; i <= 6; i++) {
            JButton topicButton = new JButton("Topic " + i);
            topicButton.setPreferredSize(new Dimension(130, 40));
            topicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            topicButton.setFocusable(false);
            topicsPanel.add(topicButton);
        }

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(MainDashboardPage.this, "You have logged out!");
            new IndexPage();
            dispose();
        });

        // Add components to main panel
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(welcomeLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(menuLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(menuButtonPanel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(topicsLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(topicsPanel);

        // Add main panel to frame
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainDashboardPage("username")); // Test with a sample username
    }
}
