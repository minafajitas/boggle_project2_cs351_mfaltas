import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
class Dictionary {
    private ArrayList<String> wordList = new ArrayList<String>();

    private FileReader reader;
    private BufferedReader buffReader;

    /**
     * Constructor to set the dictionary path in assets and create new instance of the reader and buff reader
     */
    Dictionary() {
        try {
            reader = new FileReader("Assets/OpenEnglishWordList.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        buffReader = new BufferedReader(reader);

        String next = "";

        while (true) {
            try {
                next = buffReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("error while reading");
                System.exit(1);
            }

            if (next != null) {
                wordList.add(next);
                continue;
            }
            break;
        }
    }

    /**
     * Called in by other classes to look a word up in a dictionary.
     * @param word
     * @return
     */
    boolean inDictionary(String word) {
        word = word.toLowerCase();
        return wordList.contains(word);
    }
}
