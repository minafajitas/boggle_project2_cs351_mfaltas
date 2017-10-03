import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Die
{
  private int dieId;
  private char topLetter;
  private String letters;
  private String imageText;

  Die(int dieId, String letters)
  {
    this.dieId = dieId;
    this.letters = letters;
  }

  void rollDie ()
  {
    Random rand = new Random();
    int randomInt = rand.nextInt(6) + 0; //(max number) + min number
    this.topLetter = letters.charAt(randomInt);
    this.imageText = "image_" + topLetter + ".jpg";
  }

  char getTopLetter()
  {
    return topLetter;
  }

  public ImageView getImageView ()
  {
    System.out.println(imageText);
    Image tileImage = new Image(imageText);
    ImageView tileImageView = new ImageView();
    tileImageView.setImage(tileImage);
    tileImageView.setX(40);
    tileImageView.setY(40);

    return tileImageView;
  }
}
