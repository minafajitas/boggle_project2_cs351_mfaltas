import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Board
{
  private ArrayList<Die> board;
  private final int directionArray[] = {-5, -4, -3, -1, 1, 3, 4, 5};

  Board()
  {
    Die die0 = new Die(0, "RIFOBX");
    Die die1 = new Die(1, "IFEHEY");
    Die die2 = new Die(2, "DENOWS");
    Die die3 = new Die(3, "UTOKND");
    Die die4 = new Die(4, "HMSRAO");
    Die die5 = new Die(5, "LUPETS");
    Die die6 = new Die(6, "ACITOA");
    Die die7 = new Die(7, "YLGKUE");
    Die die8 = new Die(8, "QBMJOA");
    Die die9 = new Die(9, "EHISPN");
    Die die10 = new Die(10, "VETIGN");
    Die die11 = new Die(11, "BALIYT");
    Die die12 = new Die(12, "EZAVND");
    Die die13 = new Die(13, "RALESC");
    Die die14 = new Die(14, "UWILRG");
    Die die15 = new Die(15, "PACEMD");

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

    for (Die dice: board)
    {
      dice.rollDie();
    }

    Collections.shuffle(board);

  }


  boolean findWord (String word, int charInd, int boardInd)
  {
    if (charInd == word.length()-1)
    {
      return true;
    }
    if (charInd != 0)
    {
      if (word.charAt(charInd) == board.get(boardInd + 1).getTopLetter())
      {
        findWord(word, charInd++, boardInd + 1);
      }
    }
    for (Die dice : board)
    {
      if (word.charAt(charInd) == dice.getTopLetter())
      {
        boardInd = board.indexOf(dice);
        findWord(word, charInd++, boardInd);
      }
      else
      {
        break;
      }
    }
    return false;
  }


}
