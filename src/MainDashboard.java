import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDashboard {
    private JPanel dashboardPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel welcomeLabel;
    private JLabel menuLabel;
    private JButton leaderboardButton;
    private JButton logoutButton;
    private JButton topicNameButton;
    private JButton topicNameButton1;
    private JButton topicNameButton2;
    private JButton topicNameButton3;

    private UserAuthentication userAuth;

    public MainDashboard(CardLayout cardLayout, JPanel cardPanel) {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Index");
            }
        });
    }

    public JPanel getDashboardPanel() {
        return dashboardPanel;
    }
}
