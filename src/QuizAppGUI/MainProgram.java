package QuizAppGUI;

import javax.swing.*;

public class MainProgram {
    public static void main(String[] args) {
        // Initializing the program and setting the first page to Index.java
        JFrame frame = new JFrame("Quiz Application");
        frame.setContentPane(new Index(frame).getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
