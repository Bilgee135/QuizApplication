package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import dataModel.*;
import customization.*;
import xjtlu.cpt111.assignment.quiz.model.Question;

public class Dashboard {
    private JPanel dashboardPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel welcomeLabel;
    private JLabel menuLabel;
    private JButton leaderboardButton;
    private JButton logoutButton;
    private UserAuthentication userAuth;

    public Dashboard(CardLayout cardLayout, JPanel cardPanel, UserAuthentication userAuth) {

        // Initialize the main dashboard panel
        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new GridLayout(2, 1));
        dashboardPanel.setBackground(ColorChoice.BACKGROUND);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));

        // This panel contains menu buttons
        topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.setBackground(ColorChoice.BACKGROUND);

        // Welcome message
        welcomeLabel = new JLabel("Welcome User!");
        welcomeLabel.setForeground(ColorChoice.TEXT_COLOR);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        welcomeLabel.setVerticalAlignment(SwingConstants.CENTER);
        topPanel.add(welcomeLabel);

        // Menu label
        menuLabel = new JLabel("Menu");
        menuLabel.setForeground(ColorChoice.TEXT_COLOR);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        menuLabel.setHorizontalAlignment(SwingConstants.LEFT);
        menuLabel.setVerticalAlignment(SwingConstants.CENTER);
        topPanel.add(menuLabel);

        // Buttons for Leaderboard and Logout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(ColorChoice.BACKGROUND);

        leaderboardButton = new JButton("Leaderboard");
        StyleButton.styleButton(leaderboardButton);

        logoutButton = new JButton("Logout");
        StyleButton.styleButton(logoutButton);

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dashboardPanel, "Under development, come back later!");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(dashboardPanel, "You have logged out!");
                cardLayout.show(cardPanel, "main.Index"); // Switches back the index page when logged out
            }
        });

        buttonPanel.add(leaderboardButton);
        buttonPanel.add(logoutButton);
        topPanel.add(buttonPanel);
        dashboardPanel.add(topPanel);

        // Setup the bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(ColorChoice.BACKGROUND);

        JPanel labelPanel = new JPanel(); // Separate panel for text
        labelPanel.setBackground(ColorChoice.BACKGROUND);
        JLabel topicLabel = new JLabel("Choose topic");
        topicLabel.setForeground(ColorChoice.TEXT_COLOR);
        topicLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topicLabel.setHorizontalAlignment(SwingConstants.LEFT);
        labelPanel.add(topicLabel);

        JPanel buttonGridPanel = new JPanel(new GridLayout(2, 3, 30, 35)); // Panel for topic buttons
        buttonGridPanel.setBackground(ColorChoice.BACKGROUND);

        // Renders the available topics dynamically on buttons
        File questionPATH = new File("src/topicQuestions");
        for (File file : questionPATH.listFiles()) {
            String fileName = file.getName();
            String topicName = "";
            if (fileName.startsWith("QUES-") && fileName.endsWith(".xml")) {
                // Get the topic name by removing "QUES-" and ".xml"
                topicName = fileName.substring(5, fileName.lastIndexOf("."));
                if(topicName.contains("_")){
                    topicName = topicName.replace("_", " ");
                }

                JButton topicButton = new JButton(topicName); // Use the topic name
                StyleButton.styleButton(topicButton);

                // When clicked on the button, it loads the questions and quiz
                String finalTopicName = topicName;
                topicButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        File topicFile = new File("src/topicQuestions/QUES-" + finalTopicName.replace(" ", "_") + ".xml");
                        List<Question> topicQuestions = QuizLoader.loadQuestions(topicFile);

                        if (topicQuestions.isEmpty()) {
                            JOptionPane.showMessageDialog(dashboardPanel, "No questions available for this topic.");
                            return;
                        }

                        QuizPanel quizPanel = new QuizPanel(topicQuestions);
                        cardPanel.add(quizPanel, "panels.QuizPanel");
                        cardLayout.show(cardPanel, "panels.QuizPanel");
                    }
                });


                buttonGridPanel.add(topicButton);
            }
        }

        // Integrate all panels to the outer one
        bottomPanel.add(labelPanel, BorderLayout.NORTH);
        bottomPanel.add(buttonGridPanel, BorderLayout.CENTER);
        dashboardPanel.add(bottomPanel);
    }

    public JPanel getDashboardPanel() {
        return dashboardPanel;
    }
}
