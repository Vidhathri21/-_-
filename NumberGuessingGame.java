import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {
    private JLabel instructionLabel;
    private JTextField guessTextField;
    private JButton submitButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;

    private int targetNumber;
    private int attempts;
    private int score;

    public NumberGuessingGame() {
        super("Number Guessing Game");
        setLayout(new FlowLayout());

        instructionLabel = new JLabel("Enter your guess (1-100):");
        add(instructionLabel);

        guessTextField = new JTextField(10);
        add(guessTextField);

        submitButton = new JButton("Submit");
        add(submitButton);

        resultLabel = new JLabel();
        add(resultLabel);

        scoreLabel = new JLabel("Score: 0");
        add(scoreLabel);

        // Generate a random number from 1 to 100
        targetNumber = generateRandomNumber();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void processGuess() {
        String guessString = guessTextField.getText();
        try {
            int guess = Integer.parseInt(guessString);

            if (guess < 1 || guess > 100) {
                JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 100.");
            } else {
                attempts++;
                if (guess == targetNumber) {
                    resultLabel.setText("Congratulations! You guessed it right.");
                    score += 10 - attempts;
                    scoreLabel.setText("Score: " + score);
                    int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        resetGame();
                    } else {
                        System.exit(0);
                    }
                } else if (guess < targetNumber) {
                    resultLabel.setText("Too low! Try again.");
                } else {
                    resultLabel.setText("Too high! Try again.");
                }
                guessTextField.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
        }
    }

    private void resetGame() {
        targetNumber = generateRandomNumber();
        attempts = 0;
        resultLabel.setText("");
        guessTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGuessingGame game = new NumberGuessingGame();
                game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                game.setSize(300, 150);
                game.setVisible(true);
            }
        });
    }
}
