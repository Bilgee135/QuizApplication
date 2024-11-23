package panels;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Collections;
import java.util.List;
import dataModel.*;
import customization.*;
import xjtlu.cpt111.assignment.quiz.model.Question;
import xjtlu.cpt111.assignment.quiz.model.Option;

public class QuizPanel extends JPanel {
    private final List<Question> quizQuestions;
    private int currentQuestionIndex = 0;
    private int correctAnswerCount = 0;

    private JTextArea questionArea;
    private JLabel topicLabel;
    private JLabel difficultyLabel;
    private JRadioButton[] optionButtons;
    private JButton nextButton;
    private ButtonGroup optionGroup;

    public QuizPanel(List<Question> selectedQuestions) {
        // Shuffle and select questions
        Collections.shuffle(selectedQuestions);
        this.quizQuestions = selectedQuestions;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        setBackground(ColorChoice.BACKGROUND);

        // Top panel for question info
        JPanel questionInfoPanel = new JPanel(new GridLayout(4, 1));
        questionInfoPanel.setBackground(ColorChoice.BACKGROUND);
        topicLabel = new JLabel();
        difficultyLabel = new JLabel();
        questionArea = new JTextArea();
        questionArea.setBackground(ColorChoice.BACKGROUND);
        questionArea.setFont(new Font("Arial", Font.BOLD, 18));
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(false);

        questionInfoPanel.add(topicLabel);
        questionInfoPanel.add(difficultyLabel);
        questionInfoPanel.add(questionArea);

        // Center panel for options
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setForeground(ColorChoice.TEXT_COLOR);
            optionButtons[i].setBackground(ColorChoice.BACKGROUND);
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionsPanel.setBackground(ColorChoice.BACKGROUND);
            optionsPanel.add(optionButtons[i]);
            optionGroup.add(optionButtons[i]);

            // Add action listener for options
            int optionIndex = i; // Preserve index for lambda
            optionButtons[i].addActionListener(e -> handleOptionSelection(optionIndex));
        }

        // Bottom panel for navigation
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(ColorChoice.BACKGROUND);
        nextButton = new JButton("Next");
        StyleButton.styleButton(nextButton);
        nextButton.setEnabled(false); // Disabled until an answer is selected
        nextButton.addActionListener(e -> loadNextQuestion());
        navigationPanel.add(nextButton);

        // Add panels to main layout
        add(questionInfoPanel, BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        add(navigationPanel, BorderLayout.SOUTH);

        // Load the first question
        loadQuestion();
    }

    private void loadQuestion() {
        if (currentQuestionIndex >= quizQuestions.size()) {
            showFinalScore();
            return;
        }

        // Get current question
        Question question = quizQuestions.get(currentQuestionIndex);
        topicLabel.setText("Topic: " + question.getTopic());
        topicLabel.setForeground(ColorChoice.TEXT_COLOR);
        topicLabel.setFont(new Font("Arial", Font.BOLD, 18));
        difficultyLabel.setText("Difficulty: " + question.getDifficulty());
        difficultyLabel.setForeground(ColorChoice.TEXT_COLOR);
        difficultyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionArea.setText("Question: " + question.getQuestionStatement());
        questionArea.setForeground(ColorChoice.TEXT_COLOR);
        questionArea.setFont(new Font("Arial", Font.BOLD, 18));

        // Set options
        for (int i = 0; i < question.getOptions().length; i++) {
            optionButtons[i].setText(question.getOptions()[i].getAnswer());
            optionButtons[i].setEnabled(true);
        }
        for (int i = question.getOptions().length; i < optionButtons.length; i++) {
            optionButtons[i].setText("");
            optionButtons[i].setEnabled(false);
        }

        optionGroup.clearSelection(); // clears selection for the next question
        nextButton.setEnabled(false); // disabled until an option is selected
    }

    private void handleOptionSelection(int selectedIndex) {
        // Get current question and selected option
        Question question = quizQuestions.get(currentQuestionIndex);
        Option selectedOption = question.getOptions()[selectedIndex];

        // Check if the answer is correct
        if (selectedOption.isCorrectAnswer()) {
            correctAnswerCount++;
            JOptionPane.showMessageDialog(this, "Correct answer!", "Feedback", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Wrong answer!", "Feedback", JOptionPane.ERROR_MESSAGE);
        }

        // Disable all option buttons after selection
        for (JRadioButton button : optionButtons) {
            button.setEnabled(false);
        }

        // Enable "Next" button
        nextButton.setEnabled(true);
    }

    private void loadNextQuestion() {
        currentQuestionIndex++;
        loadQuestion();
    }

    private void showFinalScore() {
        JOptionPane.showMessageDialog(this, "Quiz completed! Your score: " + correctAnswerCount, "Quiz Finished", JOptionPane.INFORMATION_MESSAGE);

        // Save score to file
        saveScoreToFile(correctAnswerCount);

        // Optionally, notify parent component to switch back to dashboard
        Container parent = getParent();
        if (parent instanceof JFrame) {
            ((JFrame) parent).dispose();
        }
    }

    private void saveScoreToFile(int score) {
        String filename = "scores.txt";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("Score: " + score + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving score to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
