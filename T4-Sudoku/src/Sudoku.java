public class Sudoku {
  /**
   * Prüft, ob alle Reihen und Spalten die Länge 9 haben
   */
  public static boolean checkMatrix(int[][] sudoku) {
    if (sudoku.length != 9) {
      return false;
    }

    for (int row = 0; row < 9; row++) {
      if (sudoku[row].length != 9) {
        return false;
      }
    }

    return true;
  }

  /**
   * Prüft, ob alle Zahlen zwischen 1 und 9 Liegen
   */
  public static boolean checkDigits(int[][] sudoku) {
    for (int row = 0; row < 9; row++) {
      for (int column = 0; column < 9; column++) {
        if ((sudoku[row][column] < 1) || (sudoku[row][column] > 9)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Prüft, ob alle Reihen keine doppelten Zahlen haben
   */
  public static boolean checkRows(int[][] sudoku) {
    for (int row = 0; row < 9; row++) {
      // speicher, ob wir die aktuelle Zahl in der Reihe schon gesehen haben mit
      // Zahl - 1 als Index (Zahlen fangen bei 1 an)
      boolean[] numbersSeen = new boolean[9];

      for (int column = 0; column < 9; column++) {
        int currentNumber = sudoku[row][column];
        if (numbersSeen[currentNumber - 1]) { // Zahl bereits gesehen: Abbruch
          return false;
        }

        numbersSeen[currentNumber - 1] = true; // speicher aktuelle Zahl
      }
    }

    return true;
  }

  /**
   * Prüft, ob alle Spalten keine doppelten Zahlen haben
   */
  public static boolean checkColumns(int[][] sudoku) {
    for (int row = 0; row < 9; row++) {
      // speicher, ob wir die aktuelle Zahl in der Spalte schon gesehen haben mit
      // Zahl - 1 als Index (Zahlen fangen bei 1 an)
      boolean[] numbersSeen = new boolean[9];

      for (int column = 0; column < 9; column++) {
        int currentNumber = sudoku[row][column];
        if (numbersSeen[currentNumber - 1]) { // Zahl bereits gesehen: Abbruch
          return false;
        }

        numbersSeen[currentNumber - 1] = true; // speicher aktuelle Zahl
      }
    }

    return true;
  }

  /**
   * Prüft, ob alle Blöcke keine doppelten Zahlen haben
   */
  public static boolean checkBlocks(int[][] sudoku) {
    // Block 1 beginnt bei (0, 0), Block 2 bei (0, 3) ... Block 9 bei (6, 6)
    for (int rowStart = 0; rowStart < 9; rowStart += 3) {
      for (int columnStart = 0; columnStart < 9; columnStart += 3) {
        // speicher, ob wir die aktuelle Zahl in der Spalte schon gesehen haben mit
        // Zahl - 1 als Index (Zahlen fangen bei 1 an)
        boolean[] numbersSeen = new boolean[9];

        for (int rowOffset = 0; rowOffset < 3; rowOffset++) {
          for (int columnOffset = 0; columnOffset < 3; columnOffset++) {
            int currentNumber = sudoku[rowStart + rowOffset][columnStart + columnOffset];

            if (numbersSeen[currentNumber - 1]) { // Zahl bereits gesehen: Abbruch
              return false;
            }

            numbersSeen[currentNumber - 1] = true; // speicher aktuelle Zahl
          }
        }
      }
    }

    return true;
  }

  public static boolean isValidSudokuSolution(int[][] sudoku) {
    if (!checkMatrix(sudoku)) return false;
    if (!checkDigits(sudoku)) return false;
    if (!checkRows(sudoku)) return false;
    if (!checkColumns(sudoku)) return false;
    if (!checkBlocks(sudoku)) return false;

    return true;
  }

  public static void main(String[] args) {
    int[][] sudoku1 = { // valid
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {7, 8, 9, 1, 2, 3, 4, 5, 6},
        {4, 5, 6, 7, 8, 9, 1, 2, 3},
        {3, 1, 2, 8, 4, 5, 9, 6, 7},
        {6, 9, 7, 3, 1, 2, 8, 4, 5},
        {8, 4, 5, 6, 9, 7, 3, 1, 2},
        {2, 3, 1, 5, 7, 4, 6, 9, 8},
        {9, 6, 8, 2, 3, 1, 5, 7, 4},
        {5, 7, 4, 9, 6, 8, 2, 3, 1}
    };

    int[][] sudoku2 = { // invalid
        {1, 2, 3, 4, 5, 6, 7, 8, 9},
        {7, 8, 9, 1, 6, 2, 4, 5, 3},
        {4, 5, 6, 7, 8, 9, 1, 2, 3},
        {1, 2, 8, 4, 5, 9, 6, 7, 3},
        {6, 9, 7, 3, 1, 2, 8, 4, 5},
        {6, 4, 5, 8, 9, 7, 3, 1, 2},
        {1, 3, 2, 5, 7, 4, 6, 9, 8},
        {1, 6, 8, 2, 3, 1, 5, 7, 9},
        {5, 7, 4, 9, 6, 8, 2, 3, 1}
    };

    boolean s1 = isValidSudokuSolution(sudoku1);
    boolean s2 = isValidSudokuSolution(sudoku2);

    System.out.println(s1);
    System.out.println(s2);
  }
}

