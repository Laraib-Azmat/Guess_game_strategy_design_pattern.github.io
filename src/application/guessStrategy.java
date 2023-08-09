package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

interface guessStrategy {
	String getGameType();
    String getHint();
    boolean checkGuess(String guess);
    void openGuessScreen();
	}