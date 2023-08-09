package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class hangman extends Application {

    private Stage primaryStage;
    private Stage guessStage;
    private String hint;
    private String word;
    private String guessedLetters;
    private int guessesRemaining;
    private TextField hintTextField;
    private TextField wordTextField;
    private Label wordLabel;
    private TextField guessTextField;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupFirstScreen();
        primaryStage.show();
    }

    private void setupFirstScreen() {
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Big big fool");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label wordInputLabel = new Label("Enter a word:");
        wordTextField = new TextField();

        Label hintInputLabel = new Label("Enter a hint:");
       hintTextField = new TextField();

        
        Button startButton = new Button("Start");
        startButton.setOnAction(event -> openGuessScreen());

        root.getChildren().addAll(titleLabel, wordInputLabel, wordTextField,hintInputLabel, hintTextField, startButton);

        primaryStage.setScene(new Scene(root, 600, 500));
    }

    private void openGuessScreen() {
        word = wordTextField.getText();
        hint = hintTextField.getText();
        guessedLetters = "";
        guessesRemaining = 6;

        guessStage = new Stage();
        guessStage.setTitle("Guess the Word");

        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Guess the Word");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        
        Label hintLabel = new Label("Hint: "+hint);
        hintLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        wordLabel = new Label();
        updateWordLabel();

        Label guessLabel = new Label("Enter a letter:");
        guessTextField = new TextField();

        Button guessButton = new Button("Guess");
        guessButton.setOnAction(event -> checkGuess());

        resultLabel = new Label();

        root.getChildren().addAll(titleLabel,hintLabel,  wordLabel, guessLabel,guessTextField, guessButton, resultLabel);

        guessStage.setScene(new Scene(root, 600, 500));
        guessStage.show();
    }

    private void checkGuess() {
        String guess = guessTextField.getText();

        if (guess.length() != 1) {
            resultLabel.setText("Invalid input! Please enter a single letter.");
            guessTextField.clear();
            return;
        }

        if (guessedLetters.contains(guess)) {
            resultLabel.setText("You already guessed that letter.");
            guessTextField.clear();
            return;
        }

        if (word.contains(guess)) {
            guessedLetters += guess;
            updateWordLabel();

            if (word.equals(guessedLetters)) {
                resultLabel.setText("You won! Congratulations!");
                guessTextField.setDisable(true);
                return;
            } 
            
            
            else {
                resultLabel.setText("Correct guess! Keep going.");
            }
        } else {
            guessesRemaining--;

            if (guessesRemaining == 0) {
                resultLabel.setText("You lost! The word was: " + word);
                guessTextField.setDisable(true);
            } else {
                resultLabel.setText("Incorrect guess! You have " + guessesRemaining + " guesses remaining.");
            }
        }

        guessTextField.clear();
    }

    private void updateWordLabel() {
        StringBuilder maskedWord = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (guessedLetters.contains(String.valueOf(c))) {
                maskedWord.append(c).append(" ");
           
            }
            
            
            else {
                maskedWord.append("_ ");
            }
        }

        wordLabel.setText(maskedWord.toString());
    }
}