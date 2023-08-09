package application;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class guessScreen {
	private Stage primaryStage;
    private guessStrategy guessStrategy;

    public guessScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setupFirstScreen() {
        VBox root = new VBox();
        root.setPadding(new Insets(30));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        

        Label titleLabel = new Label("Guessing Game");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button wordGameButton = new Button("Guess Word");
        wordGameButton.setStyle("-fx-background-color: RED; -fx-text-fill: white;-fx-font-size: 25px; -fx-font-weight: bold");
        wordGameButton.setOnAction(event -> {
            TextInputDialog wordInputDialog = new TextInputDialog();
            wordInputDialog.setTitle("Enter Word");
            wordInputDialog.setHeaderText("Enter a word to guess:");
            wordInputDialog.showAndWait().ifPresent(word -> {
                TextInputDialog hintInputDialog = new TextInputDialog();
                hintInputDialog.setTitle("Enter Hint");
                hintInputDialog.setHeaderText("Enter a hint:");
                hintInputDialog.showAndWait().ifPresent(hint -> {
                    guessStrategy = new HangmanGame(word, hint, primaryStage);
                    guessStrategy.openGuessScreen();
                });
            });
        });

        Button numberGameButton = new Button("Guess Number");
        numberGameButton.setStyle("-fx-background-color: BLACK; -fx-text-fill: white; -fx-font-size: 25px; -fx-font-weight: bold");
        numberGameButton.setOnAction(event -> {
            TextInputDialog numberInputDialog = new TextInputDialog();
            numberInputDialog.setTitle("Enter Number");
            numberInputDialog.setHeaderText("Enter a number to guess:");
            numberInputDialog.showAndWait().ifPresent(number -> {
                try {
                    int guessNumber = Integer.parseInt(number);
                    TextInputDialog hintInputDialog = new TextInputDialog();
                    hintInputDialog.setTitle("Enter Hint");
                    hintInputDialog.setHeaderText("Enter a hint:");
                    hintInputDialog.showAndWait().ifPresent(hint -> {
                        guessStrategy = new GuessNumberGame(guessNumber, hint, primaryStage);
                        guessStrategy.openGuessScreen();
                    });
                } catch (NumberFormatException e) {
                    showAlert("Invalid Number", "Please enter a valid number.");
                }
            });
        });

        root.getChildren().addAll(titleLabel, wordGameButton, numberGameButton);
        primaryStage.setScene(new Scene(root, 600, 500));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        }}