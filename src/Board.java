import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Board {
    private ArrayList<Die> board;
    private final int directionArray[] = {-5, -4, -3, -1, 1, 3, 4, 5};

    Board() {
        Die die0 = new Die(0, "AAAFRS");
        Die die1 = new Die(1, "AAEEEE");
        Die die2 = new Die(2, "AAFIRS");
        Die die3 = new Die(3, "ADENNN");
        Die die4 = new Die(4, "AEEEEM");
        Die die5 = new Die(5, "AEEGMU");
        Die die6 = new Die(6, "AEGMNN");
        Die die7 = new Die(7, "AFIRSY");
        Die die8 = new Die(8, "BJKQXZ");
        Die die9 = new Die(9, "CCNSTW");
        Die die10 = new Die(10, "CEIILT");
        Die die11 = new Die(11, "CEILPT");
        Die die12 = new Die(12, "CEIPST");
        Die die13 = new Die(13, "DHHNOT");
        Die die14 = new Die(14, "DHHLOR");
        Die die15 = new Die(15, "DHLNOR");
        Die die16 = new Die(15, "DDLNOR");
        Die die17 = new Die(15, "EIIITT");
        Die die18 = new Die(15, "EMOTTT");
        Die die19 = new Die(15, "ENSSSU");
        Die die20 = new Die(15, "FIPRSY");
        Die die21 = new Die(15, "GORRVW");
        Die die22 = new Die(15, "HIPRRY");
        Die die23 = new Die(15, "NOOTUW");
        Die die24 = new Die(15, "OOOTTU");


        board = new ArrayList<>();
        board.add(die0);
        board.add(die1);
        board.add(die2);
        board.add(die3);
        board.add(die4);
        board.add(die5);
        board.add(die6);
        board.add(die7);
        board.add(die8);
        board.add(die9);
        board.add(die10);
        board.add(die11);
        board.add(die12);
        board.add(die13);
        board.add(die14);
        board.add(die15);
        board.add(die16);
        board.add(die17);
        board.add(die18);
        board.add(die19);
        board.add(die20);
        board.add(die21);
        board.add(die22);
        board.add(die23);
        board.add(die24);


        for (Die dice : board) {
            dice.rollDie();
        }

        Collections.shuffle(board);

    }


    boolean findWord(String word, int charInd, int boardInd) {
        word = word.toUpperCase();
        System.out.println("function called");
        if (charInd == word.length() - 1) {
            System.out.println("reached end of word\n");
            return true;
        } else if (charInd == 0) {
            System.out.println("enterd first letter\n");
            for (Die dice : board) {
                System.out.println("top letter is " + dice.getTopLetter());
                System.out.println("current char is " + word.charAt(charInd));
                if (word.charAt(charInd) == dice.getTopLetter()) {
                    charInd = 1;
                    return findWord(word, charInd, board.indexOf(dice));
                }
            }
        } else if (charInd > 0) {
            System.out.println("enterd second letter\n");
            int tempBoardInd;
            for (int i = 0; i < directionArray.length; i++) {
                tempBoardInd = boardInd + directionArray[i];
                if (tempBoardInd > 15 || tempBoardInd < 0) {
                    continue;
                }
                if (word.charAt(charInd) == board.get(tempBoardInd).getTopLetter()) {
                    charInd++;
                    return findWord(word, charInd, tempBoardInd);
                }
            }
        }
        return false;
    }

    ArrayList<Die> getBoard() {
        return board;
    }
}
