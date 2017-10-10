import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;


public class Controller extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Board newboard = new Board();

    private BorderPane mainGamePane = new BorderPane();
    private int score = 0;
    private Text scoreText = new Text("Score: " + score);
    private TextField wordField = new TextField();

    private Text timerText = new Text();

    private boolean drawLine = false;

    Point lastClicked = new Point(0,0);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Dictionary newDictionary = new Dictionary();

      Group boardGroup = new Group();
        GridPane boardPane = new GridPane();
        boardGroup.getChildren().add(boardPane);
        Group secondBoardGroup = new Group();
        boardGroup.getChildren().add(secondBoardGroup);

        int col = 0;
        int row = 0;
        for (Die Dice : newboard.getBoard()) {
            Button newLetterButton = new Button();
            newLetterButton.setGraphic(Dice.getImageView());

            newLetterButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    wordField.setText(wordField.getCharacters() + "" + Dice.getTopLetter());

                    int x = (int) (((Button)(event.getSource())).getLayoutX() + 100);
                    int y = (int) (((Button)(event.getSource())).getLayoutY() + 100);

                  System.out.println("x is " + x + "y is " + y);
                    if (drawLine == true)
                    {
                      Line arrow = new Line(lastClicked.getX(), lastClicked.getY(), x, y);
                      secondBoardGroup.getChildren().add(arrow);
                    }
                    else
                    {
                      drawLine = true;
                    }
                    lastClicked.setLocation(x,y);
                }
            });
            boardPane.add(newLetterButton, col, row);
            if (col > 3) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }

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

        gameTimer newTimer = new gameTimer();
        newTimer.start();

        primaryStage.setScene(new Scene(mainGamePane, 1500, 1000));
        primaryStage.show();

        enterWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String enteredText;
                enteredText = wordField.getText();
                wordField.setText("");
                secondBoardGroup.getChildren().clear();
                drawLine = false;
                if (!enteredText.equals("")) {
                    Text newWord = new Text(enteredText);
                    newWord.setFont(Font.font(20));
                    if (newboard.findWord(enteredText, 0, 0) && newDictionary.inDictionary(enteredText)) {
                        rightNumbers.getChildren().add(newWord);
                        score += enteredText.length() - 2;
                        scoreText.setText("Score: " + score);
                    } else {
                        wrongNumbers.getChildren().add(newWord);
                    }
                    System.out.println(newboard.findWord(enteredText, 0, 0));
                    System.out.println(newDictionary.inDictionary(enteredText));
                }
            }
        });
    }

    class gameTimer extends AnimationTimer {
        boolean gameStarted = false;
        double conversionFactorFromNanoToSeconds = Math.pow(10, -9);
        double currentTime;
        double startupTime;
        final private double totalTime = 180;

        @Override
        public void handle(long now) {
            if (gameStarted == true) {
                currentTime = (now * conversionFactorFromNanoToSeconds) - startupTime;
            } else {
                startupTime = now * conversionFactorFromNanoToSeconds;
                gameStarted = true;
            }
            double timeRemaining = totalTime - currentTime;
            timeRemaining = ((int) (timeRemaining * 10.0)) / 10.0;

            timerText.setText("Time Remaining: " + timeRemaining + "         ");
//      System.out.println(timeRemaining);
            if (timeRemaining <= 0) {
                timerText.setText("Game Over                     ");
                wordField.setDisable(true);
                stop();
//        System.exit(0);
            }
        }
    }
}
