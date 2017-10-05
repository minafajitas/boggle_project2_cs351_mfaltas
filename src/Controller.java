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


public class Controller extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Board newboard = new Board();

    private int score = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Dictionary newDictionary = new Dictionary();

        GridPane boardPane = new GridPane();

        int col = 0;
        int row = 0;
        for (Die Dice : newboard.getBoard()) {
            boardPane.add(Dice.getImageView(), col, row);
            if (col > 2) {
                col = 0;
                row++;
            } else {
                col++;
            }
        }

        BorderPane mainGamePane = new BorderPane();
        VBox gameVbox = new VBox();
        HBox topHbox = new HBox();
        VBox rightNumbers = new VBox();
        VBox wrongNumbers = new VBox();
        ScrollPane rightNumbersScroll = new ScrollPane();
        ScrollPane wrongNumbersScroll = new ScrollPane();
        rightNumbersScroll.setContent(rightNumbers);
        wrongNumbersScroll.setContent(wrongNumbers);
        Text scoreText = new Text("Score: " + score);
        topHbox.getChildren().add(scoreText);
        scoreText.setFont(Font.font(40));
        TextField wordField = new TextField();
        Button enterWord = new Button("enter");
        gameVbox.getChildren().add(boardPane);
        boardPane.setAlignment(Pos.CENTER);
        gameVbox.getChildren().addAll(wordField, enterWord);
        gameVbox.setAlignment(Pos.CENTER);
        mainGamePane.setCenter(gameVbox);
        mainGamePane.setTop(topHbox);
        mainGamePane.setRight(rightNumbersScroll);
        mainGamePane.setLeft(wrongNumbersScroll);
        primaryStage.setScene(new Scene(mainGamePane, 1000, 800));
        primaryStage.show();


        enterWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String enteredText;
                enteredText = wordField.getText();
                if (!enteredText.equals("")) {
                    Text newWord = new Text(enteredText);
                    newWord.setFont(Font.font(60));
                    if (newboard.findWord(enteredText, 0, 0) && newDictionary.inDictionary(enteredText))
                    {
                        rightNumbers.getChildren().add(newWord);
                        score++;
                    }
                    else
                    {
                        wrongNumbers.getChildren().add(newWord);
                    }
                    System.out.println(newboard.findWord(enteredText, 0, 0));
                    System.out.println(newDictionary.inDictionary(enteredText));
                }
            }
        });
    }
}
