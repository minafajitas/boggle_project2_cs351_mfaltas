import java.util.ArrayList;
import java.util.Collections;

class Board
{
  private ArrayList<Die> board;
  //direction array for 4x4
  private final int directionArrayFour[] = {-5, -4, -3, -1, 1, 3, 4, 5};
  //direction array for 5x5
  private final int directionArrayFive[] = {-6, -5, -4, -1, 1, 4, 5, 6};
  private boolean fourByFour = false;

  /**
   * Creates a realistic boggle tray with actual dice objects. Sixteen dice are created for the 4x4 version and 25 dice
   * objects are created for the 5x5 version. Each dice has 6 faces and the set is obtained from an official boggle set.
   * After the dice are created, the roll method is called to assign the top letter for each dice and adds all of the
   * dice to an arraylist.
   * Special consideration is given to dice with letter U. They are rolled a fixed number times until a U is obtained.
   * This is so that the chances of a U roll. If there is a U roll, if will be placed next to a Q.
   * @param fourByFourBoolean
   */
  Board(boolean fourByFourBoolean)
  {
    board = new ArrayList<>();
    fourByFour = fourByFourBoolean;

    if (fourByFourBoolean == true)
    {
      //4x4 version boggle
      Die die0 = new Die(0, "AAEEGN");
      board.add(die0);
      Die die1 = new Die(1, "ELRTTY");
      board.add(die1);
      Die die2 = new Die(2, "AOOTTW");
      board.add(die2);
      Die die3 = new Die(3, "ABBJOO");
      board.add(die3);
      Die die4 = new Die(4, "EHRTVW");
      board.add(die4);
      Die die5 = new Die(5, "CIMOTU");
      board.add(die5);
      Die die6 = new Die(6, "DISTTY");
      board.add(die6);
      Die die7 = new Die(7, "EIOSST");
      board.add(die7);
      Die die8 = new Die(8, "DELRVY");
      board.add(die8);
      Die die9 = new Die(9, "ACHOPS");
      board.add(die9);
      Die die10 = new Die(10, "HIMNQU");
      board.add(die10);
      Die die11 = new Die(11, "EEINSU");
      board.add(die11);
      for (int i = 0; i < 10; i++)
      {
        if (die11.getTopLetter() != 'U')
        {
          die11.rollDie();
        } else
        {
          break;
        }
      }
      Die die12 = new Die(12, "EEGHNW");
      board.add(die12);
      Die die13 = new Die(13, "AFFKPS");
      board.add(die13);
      Die die14 = new Die(14, "HLNNRZ");
      board.add(die14);
      Die die15 = new Die(15, "DEILRX");
      board.add(die15);
    } else
    {
      //5x5 version boggle
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
      for (int i = 0; i < 5; i++)
      {
        if (die24.getTopLetter() != 'U')
        {
          die24.rollDie();
        } else
        {
          break;
        }
      }

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
    }

    for (Die dice : board)
    {
      if (dice.getTopLetter() != 'U' && dice.getTopLetter() != 'Q')
      {
        dice.rollDie();
      }
    }

    Collections.shuffle(board);

    int qInd = -1;
    int uInd = -1;
    for (Die dice : board)
    {
      if (dice.getTopLetter() == 'Q')
      {
        qInd = board.indexOf(dice);

        for (Die dieU : board)
        {
          if (dieU.getTopLetter() == 'U')
          {
            uInd = board.indexOf(dieU);
          }
        }
      }
    }

    if (qInd != -1 && uInd != -1)
    {
      System.out.println("found q and u");
      System.out.println("q is " + qInd);
      System.out.println("u is " + uInd);
      int tempInd;
      int directionArray[];
      if (fourByFourBoolean)
      {
        directionArray = directionArrayFour.clone();
      } else
      {
        directionArray = directionArrayFive.clone();
      }
      for (int i = 0; i < directionArray.length; i++)
      {
        tempInd = qInd + directionArray[i];

        if (tempInd > board.size() - 1 || tempInd < 0)
        {
          continue;
        } else
        {
          System.out.println("temp ind is " + tempInd);
          Die dieU = board.get(uInd);
          board.set(uInd, board.get(tempInd));
          board.set(tempInd, dieU);
          break;
        }
      }
    }
  }

  /**
   * This method takes a word from the controller and it recursively calls a character by character searching if these
   * characters of the word are all connected and are neighbors on the board. It returns true if that's the case.
   * @param word
   * @param charInd
   * @param boardInd
   * @return
   */
  boolean findWord(String word, int charInd, int boardInd)
  {
    int directionArray[];
    if (fourByFour)
    {
      directionArray = directionArrayFour.clone();
    } else
    {
      directionArray = directionArrayFive.clone();
    }
    System.out.println(word);
    word = word.toUpperCase();
    System.out.println("function called");

    /**
     * checks if all the letters in the word have been looked. If that's the case, it returns true.
     */
    if (charInd == word.length())
    {
      System.out.println("reached end of word\n");
      return true;
    }
    /**
     * Checks if the first letter of the word exists on the board. It calls the next letter of the word recursively to
     * see if it neighbors the first letter.
     */
    else if (charInd == 0)
    {
      System.out.println("enterd first letter\n");
      for (Die dice : board)
      {
        System.out.println("top letter is " + dice.getTopLetter());
        System.out.println("current char is " + word.charAt(charInd));
        if (word.charAt(0) == dice.getTopLetter())
        {
          charInd = 1;
          if (findWord(word, charInd, board.indexOf(dice))) return true;
        }
      }
    }
    /**
     * Checks if the neighbors of the first letter found on the board are next to that first letter. Calls recursively
     * to go into the same if statement to look for other neighbors.
     */
    else if (charInd > 0)
    {
      System.out.println("enterd second letter\n");
      System.out.println("length of direction array is " + directionArray.length);
      int tempBoardInd;
      for (int i = 0; i < directionArray.length - 1; i++)
      {
        tempBoardInd = boardInd + directionArray[i];
        if (tempBoardInd > board.size() - 1 || tempBoardInd < 0)
        {
          System.out.println("tempBoardInd is " + tempBoardInd);
          continue;
        }
        if (fourByFour == false && (((directionArray[i] == -4 || directionArray[i] == 6 || directionArray[i] == 1) && (boardInd == 4 || boardInd == 9 || boardInd == 14 || boardInd == 19 || boardInd == 24))
          || (directionArray[i] == -6 && (boardInd == 0 || boardInd == 5 || boardInd == 10 || boardInd == 15 || boardInd == 20))))
        {
          System.out.println("continue bec 5x5 edge");
          continue;
        }
        if (fourByFour == true && (((directionArray[i] == -3 || directionArray[i] == 1) && (boardInd == 3 || boardInd == 7 || boardInd == 11 || boardInd == 15)
          || ((directionArray[i] == -5 || directionArray[i] == 3) && (boardInd == 0 || boardInd == 4 || boardInd == 8 || boardInd == 12)))))
        {
          System.out.println("continue bec 4x4 edge");
          continue;
        }
        if (word.charAt(charInd) == board.get(tempBoardInd).getTopLetter())
        {
          System.out.println("original board ind is " + boardInd);
          System.out.println("Matched Baord index is " + tempBoardInd + " and Letter is " + board.get(tempBoardInd).getTopLetter());
          System.out.println("charInd is " + charInd + " and letter is " + word.charAt(charInd));
          charInd++;
          if (findWord(word, charInd, tempBoardInd)) return true;
        }
      }
    }
    /**
     * If all the cases above fail then that letter is not on the board or does not neighbor that first letter of the
     * word and false is returned.
     */
    System.out.println("returning false for charInd " + charInd + " and boardInd " + boardInd);
    return false;
  }

  void randomBoard()
  {
    Collections.shuffle(board);
  }
  /**
   * A method to return the ArrayList of the board to be used to build the visual.
   * @return
   */
  ArrayList<Die> getBoard()
  {
    return board;
  }
}