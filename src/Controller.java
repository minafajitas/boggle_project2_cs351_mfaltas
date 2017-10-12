import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;


public class Controller extends Application
{
  public static void main(String[] args)
  {
    launch(args);
  }

  /**
   * Initializes global variables to be used in both the controller class and the inner timer class. This includes the
   * board and the timer text and its elements.
   */
  Board newBoardFour = new Board(true);
  Board newBoardFive = new Board(false);
  ArrayList<String> registeredWords = new ArrayList<String>();

  private BorderPane mainGamePane = new BorderPane();
  private int score = 0;
  private Text scoreText = new Text("Score: " + score);
  private TextField wordField = new TextField();
  private Text timerText = new Text();
  private boolean drawLine = false;
  private double timeRemaining;
  //  private GridPane boardPane = new GridPane();
  Point lastClicked = new Point(0, 0);
  boolean fourByFourBoolean = true;
  boolean gameModeSelected = false;

  Board newBoard;

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    /**
     * Initializes a new dictionary, board, and several panes. The Primary stage consists of the main menu and the
     * drop down menu to select stage and start game button.
     */
    Dictionary newDictionary = new Dictionary();

    //First scene: main menu
    BorderPane mainMenuPane = new BorderPane();
    VBox mainMenuBox = new VBox();
    Button startGame = new Button("Start Game");
    ComboBox gameMode = new ComboBox();
    gameMode.getItems().addAll("4x4 mode", "5x5 mode");
    gameMode.getSelectionModel().select(0);
    mainMenuBox.getChildren().addAll(startGame, gameMode);
    mainMenuPane.setCenter(mainMenuBox);
    mainMenuBox.setAlignment(Pos.CENTER);
    primaryStage.setScene(new Scene(mainMenuPane, 400, 500));
    primaryStage.show();

//    System.out.println("four by four is " + fourByFourBoolean);

    Group boardGroup = new Group();
    GridPane boardPane = new GridPane();
    boardGroup.getChildren().add(boardPane);
    Group secondBoardGroup = new Group();
    boardGroup.getChildren().add(secondBoardGroup);


    /**
     * Creates a new event handler named eh to be used whenever a letter button is called. What it does is put the
     * letter clicked on a text field so that it could be checked if it is on the board and in the dictionary. It also
     * handles drawing the line from the center of the button clicked to the next by making use of the event.getSource
     * to get the position of the click.
     * Last it also calculates the distance between the buttons and prevents the user from clicking a button that is not
     * one tile away from the previously clicked button.
     */
    EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        /**
         * gets x and y coordinates of button clicked.
         */
        Button buttonClicked = (Button) (event.getSource());
        int x = (int) (((Button) (event.getSource())).getLayoutX() + 100);
        int y = (int) (((Button) (event.getSource())).getLayoutY() + 100);
        System.out.println("x is " + x + "y is " + y);
        /**
         * sets a flag to start drawing a line between the buttons and and calculates the distances. The flag is set to
         * false when the word is finally entered.
         * The buttons and the lines are both added to a group so that the lines could appear on top of the buttons
         * without preventing the buttons from being clicked.
         * The button clicked is also disabled so that game rules cannot be violated.
         */
        if (drawLine == true)
        {
          int xDist = (int) (lastClicked.getX() - x);
          int yDist = (int) (lastClicked.getY() - y);
          if (Math.abs(xDist) < 189 && Math.abs(yDist) < 189)
          {
            Line arrow = new Line(lastClicked.getX(), lastClicked.getY(), x, y);
            secondBoardGroup.getChildren().add(arrow);
            lastClicked.setLocation(x, y);
            System.out.println("CLose enough");
            buttonClicked.setDisable(true);
            if (timeRemaining > 0)
            {
              wordField.setText(wordField.getCharacters() + "" + buttonClicked.getId());
            }
          }
        } else
        {
          drawLine = true;
          lastClicked.setLocation(x, y);
          buttonClicked.setDisable(true);
          if (timeRemaining > 0)
          {
            wordField.setText(wordField.getCharacters() + "" + buttonClicked.getId());
          }
        }
      }
    };

    /**
     * The start game sets the flags for the selected game mode. Based on that flag, Buttons are created to with image
     * Views of the dice selected based on the game mode.
     * After the board is setup, the scene is changed to show the main game board and hide the main menu.
     */
    startGame.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        if (gameMode.getValue() == "4x4 mode")
        {
          fourByFourBoolean = true;
        } else
        {
          fourByFourBoolean = false;
        }
        gameModeSelected = true;

        if (fourByFourBoolean)
        {
          newBoard = newBoardFour;
        } else
        {
          newBoard = newBoardFive;
        }

        if (gameModeSelected)
        {
          int col = 0;
          int row = 0;
          for (Die Dice : newBoard.getBoard())
          {
            Button newLetterButton = new Button();
            newLetterButton.setId(Dice.getTopLetter() + "");
            newLetterButton.setGraphic(Dice.getImageView());
            newLetterButton.setOnAction(eh);

            boardPane.add(newLetterButton, col, row);
            if ((fourByFourBoolean == false && col > 3) || (fourByFourBoolean && col > 2))
            {
              col = 0;
              row++;
            } else
            {
              col++;
            }
          }
        }
        primaryStage.setScene(new Scene(mainGamePane, 1500, 1000));
        gameTimer newTimer = new gameTimer();
        newTimer.start();
      }
    });


    /**
     * Local variables creates to contain the GUI elements of the main game board that the user sees while playing the
     * game.
     */

    VBox gameVbox = new VBox();
    HBox gameTextHbox = new HBox();
    HBox topHbox = new HBox();

    VBox rightNumbers = new VBox();
    VBox wrongNumbers = new VBox();
    ScrollPane rightNumbersScroll = new ScrollPane();
    ScrollPane wrongNumbersScroll = new ScrollPane();
    Text rightNumbersText = new Text("Right Numbers: ");
    rightNumbersText.setFont(Font.font(30));
    Text wrongNumbersText = new Text("Wrong Numbers: ");
    wrongNumbersText.setFont(Font.font(30));
    rightNumbers.getChildren().add(rightNumbersText);
    wrongNumbers.getChildren().add(wrongNumbersText);
    rightNumbersScroll.setContent(rightNumbers);
    wrongNumbersScroll.setContent(wrongNumbers);

    HBox timerBox = new HBox();
    timerBox.getChildren().add(timerText);
    timerText.setFont(Font.font(40));
    topHbox.getChildren().add(timerBox);
    topHbox.getChildren().add(scoreText);
    topHbox.setAlignment(Pos.CENTER_LEFT);
    scoreText.setFont(Font.font(60));
    Button enterWord = new Button("enter");
    gameVbox.getChildren().add(boardGroup);
    boardPane.setAlignment(Pos.CENTER);
    gameTextHbox.getChildren().addAll(wordField, enterWord);
    gameVbox.getChildren().add(gameTextHbox);
    gameTextHbox.setAlignment(Pos.BASELINE_CENTER);
    gameVbox.setAlignment(Pos.CENTER);
    mainGamePane.setCenter(gameVbox);
    mainGamePane.setTop(topHbox);
    mainGamePane.setRight(rightNumbersScroll);
    mainGamePane.setLeft(wrongNumbersScroll);


//    primaryStage.setScene(new Scene(mainGamePane, 1500, 1000));
//    primaryStage.show();

    /**
     * The enter word button is clicked after the word is entered into the text field. It calls the findword method on
     * board class to check if the word is on the board and the calls the dictionary method to check if the word is also
     * in the dictionary. If both return true and the word has not been previously entered, the word is added to the
     * right words list, score is updated, and the word is added to the Arraylist of registered words. Otherwise, the
     * word is added to the VBox containing the wrong words.
     */
    enterWord.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
//        if (timeRemaining <= 0)
//        {
//          enterWord.setDisable(true);
//        }
        for (int i = 0; i < boardPane.getChildren().size(); i++)
        {
          boardPane.getChildren().get(i).setDisable(false);

          if (timeRemaining <= 0)
          {
            boardPane.getChildren().get(i).setDisable(true);
            enterWord.setDisable(true);
          }
        }
        String enteredText;
        enteredText = wordField.getText();
        wordField.setText("");
        secondBoardGroup.getChildren().clear();
        drawLine = false;
        if (!enteredText.equals(""))
        {
          Text newWord = new Text(enteredText);
          newWord.setFont(Font.font(20));
          if (newBoard.findWord(enteredText, 0, 0) && newDictionary.inDictionary(enteredText) && !registeredWords.contains(enteredText))
          {
            registeredWords.add(enteredText);
            rightNumbers.getChildren().add(newWord);
            score += enteredText.length() - 2;
            scoreText.setText("Score: " + score);
          } else
          {
            wrongNumbers.getChildren().add(newWord);
          }
          System.out.println(newBoard.findWord(enteredText, 0, 0));
          System.out.println(newDictionary.inDictionary(enteredText));
        }
      }
    });
  }

  /**
   * Extends the Animation timer class. It registers the start time and subtracts the current time from start time to
   * obtain the time remaining. The time fields are added to the GUI elements. The text changes color using a modulus
   * when time remaining is less than 10. When time is zero or less, the game is stopped by disabling all the buttons
   * and the score is displayed in a new window.
   */
  class gameTimer extends AnimationTimer
  {
    boolean gameStarted = false;
    double conversionFactorFromNanoToSeconds = Math.pow(10, -9);
    double currentTime;
    double startupTime;
    final private double totalTime = 180;

    @Override
    public void handle(long now)
    {
      if (gameStarted == true)
      {
        currentTime = (now * conversionFactorFromNanoToSeconds) - startupTime;
      } else
      {
        startupTime = now * conversionFactorFromNanoToSeconds;
        gameStarted = true;
      }
      timeRemaining = totalTime - currentTime;
      timeRemaining = ((int) (timeRemaining * 10.0)) / 10.0;

      timerText.setText("Time Remaining: " + timeRemaining + "         ");
//      System.out.println(timeRemaining);
      if (timeRemaining <= 10)
      {
        int k = (int) (timeRemaining % 2);
        if (k == 0)
        {
          timerText.setFill(Color.GREEN);
        } else
        {
          timerText.setFill((Color.RED));
        }
      }
      if (timeRemaining <= 0)
      {
        timerText.setText("Game Over                     ");
        wordField.setDisable(true);
        stop();

        BorderPane scorePane = new BorderPane();
        Text finalScoreText = new Text("Game Over \n" + scoreText.getText());
        finalScoreText.setFont(Font.font(60));
        scorePane.setCenter(finalScoreText);
        Stage scoreStage = new Stage();
        scoreStage.setScene(new Scene(scorePane, 1000, 200));
        scoreStage.show();

//        System.exit(0);
      }
    }
  }
}
