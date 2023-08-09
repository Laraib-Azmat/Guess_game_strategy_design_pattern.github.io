package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class GuessNumberGame implements guessStrategy {
	private int guessNumber;
    private String hint;
    private Stage primaryStage;

    public GuessNumberGame(int guessNumber, String hint, Stage primaryStage) {
        this.guessNumber = guessNumber;
        this.hint = hint;
        this.primaryStage = primaryStage;
    }

    @Override
    public String getGameType() {
        return "Number";
    }

    @Override
    public String getHint() {
        return hint;
    }

    @Override
    public boolean checkGuess(String guess) {
        try {
            int guessedNumber = Integer.parseInt(guess);
            return guessedNumber == guessNumber;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void openGuessScreen() {
        Stage guessStage = new Stage();
        guessStage.setTitle("Guess the " + getGameType() + ":");

        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Guess the " + getGameType() + ":");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label hintLabel = new Label("Hint: " + getHint());
        TextField guessTextField = new TextField();
        Button guessButton = new Button("Guess");
        Label resultLabel = new Label();

        guessButton.setOnAction(event -> {
            String guess = guessTextField.getText();
            boolean isCorrect = checkGuess(guess);
            if (isCorrect) {
                resultLabel.setText("Congratulations! You guessed it right!");
                guessTextField.setDisable(true);
            } else {
            	if(Integer.parseInt(guess)>guessNumber)
            	{                resultLabel.setText("Incorrect guess. You number is greater!!!..Try again!");
            	
            	}
            	else
            	    resultLabel.setText("Incorrect guess. You number is small!!!..Try again!");
            	}
                guessTextField.clear();
            
        });

        root.getChildren().addAll(titleLabel, hintLabel, guessTextField, guessButton, resultLabel);
        guessStage.setScene(new Scene(root, 800, 600));
        guessStage.setResizable(false);
        guessStage.show();
    }
}