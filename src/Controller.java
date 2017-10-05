import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Controller extends Application
{

  public static void main(String[] args)
  {
    launch(args);
  }

  Board newboard = new Board();

  private int score = 0;
  Text timerText = new Text();

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    Dictionary newDictionary = new Dictionary();

    GridPane boardPane = new GridPane();


    int col = 0;
    int row = 0;
    for (Die Dice : newboard.getBoard())
    {
      boardPane.add(Dice.getImageView(), col, row);
      if (col > 2)
      {
        col = 0;
        row++;
      } else
      {
        col++;
      }
    }

    BorderPane mainGamePane = new BorderPane();
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

    Text scoreText = new Text("Score: " + score);
    topHbox.getChildren().add(scoreText);
    topHbox.getChildren().add(timerText);
    topHbox.setAlignment(Pos.CENTER);
    scoreText.setFont(Font.font(60));
    TextField wordField = new TextField();
    Button enterWord = new Button("enter");
    gameVbox.getChildren().add(boardPane);
    boardPane.setAlignment(Pos.CENTER);
    gameTextHbox.getChildren().addAll(wordField, enterWord);
    gameVbox.getChildren().add(gameTextHbox);
    gameTextHbox.setAlignment(Pos.BASELINE_CENTER);
    gameVbox.setAlignment(Pos.CENTER);
    mainGamePane.setCenter(gameVbox);
    mainGamePane.setTop(topHbox);
    mainGamePane.setRight(rightNumbersScroll);
    mainGamePane.setLeft(wrongNumbersScroll);

    gameTimer newTimer = new gameTimer();
    newTimer.start();

    primaryStage.setScene(new Scene(mainGamePane, 1300, 800));
    primaryStage.show();


    enterWord.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        String enteredText;
        enteredText = wordField.getText();
        if (!enteredText.equals(""))
        {
          Text newWord = new Text(enteredText);
          newWord.setFont(Font.font(20));
          if (newboard.findWord(enteredText, 0, 0) && newDictionary.inDictionary(enteredText))
          {
            rightNumbers.getChildren().add(newWord);
            score += enteredText.length() - 2;
            scoreText.setText("Score: " + score);
          } else
          {
            wrongNumbers.getChildren().add(newWord);
          }
          System.out.println(newboard.findWord(enteredText, 0, 0));
          System.out.println(newDictionary.inDictionary(enteredText));
        }
      }
    });
  }

  class gameTimer extends AnimationTimer
  {
    boolean gameStarted = false;
    double conversionFactorFromNanoToSeconds = Math.pow(10,-9);
    double currentTime;
    double startupTime;
    final private double totalTime = 5;

    @Override
    public void handle(long now)
    {
      if (gameStarted == true)
      {
        currentTime = (now * conversionFactorFromNanoToSeconds) - startupTime;
      }
      else
      {
        startupTime = now * conversionFactorFromNanoToSeconds;
        gameStarted = true;
      }
      double timeRemaining = totalTime - currentTime;
      timeRemaining = ((int) (timeRemaining * 10.0)) / 10.0;

      timerText.setText("Time Remaining: " + timeRemaining);
      System.out.println(timeRemaining);
      if (timeRemaining <= 0)
      {
        System.exit(0);
      }
    }
  }
}
