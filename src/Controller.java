import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Controller extends Application
{
  public static void main(String[] args)
  {
    launch(args);
  }

  Board newboard = new Board();


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
      }
      else
      {
        col++;
      }
    }

    VBox mainMenu = new VBox();
    TextField wordField = new TextField();
    Button enterWord = new Button("enter");
    mainMenu.getChildren().add(boardPane);
    mainMenu.getChildren().addAll(wordField, enterWord);
    primaryStage.setScene(new Scene(mainMenu, 300, 300));
    primaryStage.show();


    enterWord.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        String enteredText;
        enteredText = wordField.getText();
        System.out.println(newDictionary.inDictionary(enteredText));
      }
    });

  }
}
