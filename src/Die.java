import java.util.Random;

public class Die
{
  private int dieId;
  private char topLetter;
  private String letters;
  private String imageString;

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
    this.imageString = topLetter + ".png";
  }
}
