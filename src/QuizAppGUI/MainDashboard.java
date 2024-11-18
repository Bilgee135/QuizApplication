package QuizAppGUI;

import javax.swing.*;
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

    public MainDashboard(JFrame frame) {

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "You have logged out");
                frame.setContentPane(new Index(frame).getPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public JPanel getDashboardPanel() {
        return dashboardPanel;
    }
}
