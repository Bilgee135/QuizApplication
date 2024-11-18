package QuizAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index {
    private JLabel quizTitle;
    private JButton loginButton;
    private JButton signupButton;
    private JPanel mainPanel;


    public Index(JFrame frame) {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new LoginPage(frame).getLoginPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new SignupPage(frame).getSignupPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
