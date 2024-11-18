package QuizAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPage {
    private JPanel signupPanel;
    private JButton backButton;
    private JPanel backButtonPanel;
    private JPanel signupFieldPanel;
    private JLabel loginLabel;
    private JTextField textField1;
    private JTextField passwordTextField;
    private JButton signupButton;

    public SignupPage(JFrame frame) {

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Index(frame).getPanel());
                frame.setTitle("Create a new account");
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public JPanel getSignupPanel() {
        return signupPanel;
    }
}
