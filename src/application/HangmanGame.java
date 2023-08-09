package application;
import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.*;

class HangmanGame implements guessStrategy {
	 private String word;
	    private String hint;
	    private Stage primaryStage;
	    private Label resultLabel;
	    private Label wordLabel;
	    private TextField guessTextField;
	    private String guessedLetters ;
	    private int guessesRemaining;
	    int count= 0;
	    
	    public HangmanGame(String word, String hint, Stage primaryStage) {
	        this.word = word;
	        this.hint = hint;
	        this.primaryStage = primaryStage;
	    }

	    @Override
	  public String getGameType() {
	        return "Word";
	    }

	    @Override
	    public String getHint() {
	        return hint;
	    }

	    @Override
	    public  boolean checkGuess(String guessedLetters) {
	    	
	    	char[] charArray1 = word.toCharArray();
	        char[] charArray2 = guessedLetters.toCharArray();

	        // Sort the character arrays
	        Arrays.sort(charArray1);
	        Arrays.sort(charArray2);
	        
	        

	        // Compare the sorted character arrays
	       if(  Arrays.equals(charArray1, charArray2)) {
	    	   return true;
	    	   
	       }
	    	
	    	
	    	
	    	else {
	    		return false;
	    		
	    	 }
	    	 
	    }
	    	

	    @Override
	    public void openGuessScreen() {
	    
	    	
	    	
	         guessedLetters = "";
	         guessesRemaining = 6;

	        
	         primaryStage.setTitle("Guess the Word");

	         VBox root = new VBox();
	         root.setPadding(new Insets(20));
	         root.setSpacing(10);
	         root.setAlignment(Pos.CENTER);
	         root.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));

	         Label titleLabel = new Label("Guess the "+ getGameType());
	         titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
	         
	         Label hintLabel = new Label("Hint: "+ getHint());
	         hintLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

	         wordLabel = new Label();
	         updateWordLabel();

	         Label guessLabel = new Label("Enter a letter:");
	         guessTextField = new TextField();

	         Button guessButton = new Button("Guess");
	         
	         guessButton.setOnAction(event -> findGuess());

	         resultLabel = new Label();

	         root.getChildren().addAll(titleLabel,hintLabel,  wordLabel, guessLabel,guessTextField, guessButton, resultLabel);

	         primaryStage.setScene(new Scene(root, 600, 500));
	         primaryStage.show();
	     }

	     private void findGuess() {
	         String guess = guessTextField.getText();
	           
	          if (guess.length() != 1) {
	             resultLabel.setText("Invalid input! Please enter a single letter.");
	             guessTextField.clear();
	             return;
	         }
	          if(checkGuess(guessedLetters)) {
		        	 resultLabel.setText("You won! Congratulations!");
	            
		                guessTextField.setDisable(true);
		        	
		        	
		         }
	          
	         if (guessedLetters.contains(guess)) {
	             resultLabel.setText("You already guessed that letter.");
	             guessTextField.clear();
	             return;
	         }

	         if (word.contains(guess)) {
	             guessedLetters += guess;
	             updateWordLabel();
	      
	             if (!wordLabel.getText().contains("_")) {
	                 resultLabel.setText("You won! Congratulations!");
	                 guessTextField.setDisable(true);
	                 return;
	             }
	             else
	                 resultLabel.setText("Correct guess! Keep going.");
	                 resultLabel.setStyle("-fx-text-fill: green;");
	             guessTextField.clear();
	             return;
	         } 
	         
	       
	         else {
	             guessesRemaining--;

	             if (guessesRemaining == 0) {
	                 resultLabel.setText("You lost! The word was: " + word);
	                 resultLabel.setStyle("-fx-text-fill: red;");
	                 guessTextField.setDisable(true);
	             } else {
	                 resultLabel.setText("Incorrect guess! You have " + guessesRemaining + " guesses remaining.");
	             }
	             guessTextField.clear();
	             return;
	         }

	       
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
	         wordLabel.setStyle("-fx-fo t-size: 20px; -fx-font-weight: bold;");

	     }
	     }
	  
	    