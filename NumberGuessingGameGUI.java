import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI {
    private static int randomNumber;
    private static int attempts;
    private static int maxAttempts;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0x2c3e50));
        headerPanel.setPreferredSize(new Dimension(500, 80));
        JLabel headerLabel = new JLabel("Number Guessing Game");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(0xecf0f1));
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel rangeLabel = new JLabel("Set the range of numbers:");
        rangeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JLabel lowerBoundLabel = new JLabel("Lower Bound:");
        lowerBoundLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField lowerBoundField = new JTextField();
        JLabel upperBoundLabel = new JLabel("Upper Bound:");
        upperBoundLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField upperBoundField = new JTextField();

        JLabel attemptsLabel = new JLabel("Set the number of attempts:");
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField attemptsField = new JTextField();

        JLabel guessLabel = new JLabel("Enter your guess:");
        guessLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField guessField = new JTextField();
        JLabel feedbackLabel = new JLabel("");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 18));

        inputPanel.add(rangeLabel);
        inputPanel.add(new JLabel(""));
        inputPanel.add(lowerBoundLabel);
        inputPanel.add(lowerBoundField);
        inputPanel.add(upperBoundLabel);
        inputPanel.add(upperBoundField);
        inputPanel.add(attemptsLabel);
        inputPanel.add(attemptsField);
        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(feedbackLabel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xecf0f1));
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(new Color(0x27ae60));
        startButton.setForeground(Color.WHITE);
        JButton guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 16));
        guessButton.setBackground(new Color(0xe67e22));
        guessButton.setForeground(Color.WHITE);

        buttonPanel.add(startButton);
        buttonPanel.add(guessButton);

        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int lowerBound = Integer.parseInt(lowerBoundField.getText());
                    int upperBound = Integer.parseInt(upperBoundField.getText());
                    maxAttempts = Integer.parseInt(attemptsField.getText());
                    Random random = new Random();
                    randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                    attempts = 0;
                    feedbackLabel.setText("Game started! Make your guess.");
                    feedbackLabel.setForeground(Color.BLACK);
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter valid numbers.");
                    feedbackLabel.setForeground(Color.RED);
                }
            }
        });

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(guessField.getText());
                    attempts++;
                    if (guess < randomNumber) {
                        feedbackLabel.setText("Too low! Attempts left: " + (maxAttempts - attempts));
                        feedbackLabel.setForeground(Color.RED);
                    } else if (guess > randomNumber) {
                        feedbackLabel.setText("Too high! Attempts left: " + (maxAttempts - attempts));
                        feedbackLabel.setForeground(Color.RED);
                    } else {
                        feedbackLabel.setText("Correct! You've guessed the number in " + attempts + " attempts.");
                        feedbackLabel.setForeground(new Color(0x27ae60));
                    }

                    if (attempts >= maxAttempts && guess != randomNumber) {
                        feedbackLabel.setText("Sorry, you've used all " + maxAttempts + " attempts. The correct number was " + randomNumber + ".");
                        feedbackLabel.setForeground(Color.RED);
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid guess.");
                    feedbackLabel.setForeground(Color.RED);
                }
            }
        });

        frame.setVisible(true);
    }
}
