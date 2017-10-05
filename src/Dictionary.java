import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
    ArrayList<String> wordList = new ArrayList<String>();

    FileReader reader;
    BufferedReader buffReader;

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

    public boolean inDictionary(String word) {
        word = word.toLowerCase();
        return wordList.contains(word);
    }
}
