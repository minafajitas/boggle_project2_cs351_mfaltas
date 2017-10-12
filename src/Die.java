import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Die {
    private int dieId;
    private char topLetter;
    private String letters;
    private String imageText;
    private String imageTextEgyptian;

    /**
     * A Die instructor to create realistic dice with several six faces that are represented by a string of characters.
     * The die is meant to be rolled and the rolled letter would be stored. Also the image text is stored to allow
     * easy retrieval of image file.
     * @param dieId
     * @param letters
     */
    Die(int dieId, String letters) {
        this.dieId = dieId;
        this.letters = letters;
    }

    /**
     * Obtains a random character from the letters string.
     */
    void rollDie() {
        Random rand = new Random();
        int randomInt = rand.nextInt(6) + 0; //(max number) + min number
        this.topLetter = letters.charAt(randomInt);
        this.imageText = "image_" + topLetter + ".jpg";
        this.imageTextEgyptian = "Egyptian_" + "image_" + topLetter + ".jpg";
    }

    /**
     * gets the rolled letter.
     * @return
     */
    char getTopLetter() {
        return topLetter;
    }

    /**
     * returns an ImageView object of the letter rolled using the imageText variable in the die.
     * @return
     */
    public ImageView getImageView() {
        System.out.println(imageText);
        Image tileImage = new Image(imageText);
        ImageView tileImageView = new ImageView();
        tileImageView.setImage(tileImage);
        tileImageView.setX(40);
        tileImageView.setY(40);

        return tileImageView;
    }

    public ImageView getEgyptianImageView()
    {
        System.out.println("Egyptian " + imageTextEgyptian);
        Image tileImage = new Image(imageTextEgyptian);
        ImageView tileImageView = new ImageView();
        tileImageView.setImage(tileImage);
        tileImageView.setX(40);
        tileImageView.setY(40);

        return tileImageView;
    }
}
