package QuizAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JPanel loginPanel;
    private JButton backButton;
    private JPanel backButtonPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel loginLabel;
    private JLabel passwordLabel;
    private JPanel loginFieldPanel;
    private JLabel errorMessage;

    public LoginPage(JFrame frame) {

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new Index(frame).getPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if(username.equals("admin") && password.equals("admin")){
                    frame.setContentPane(new MainDashboard(frame).getDashboardPanel());
                    frame.revalidate();
                    frame.repaint();
                } else {
                    errorMessage.setVisible(true);
                }
            }
        });
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }


}
