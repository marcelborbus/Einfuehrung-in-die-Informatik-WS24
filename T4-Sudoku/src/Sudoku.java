public class Sudoku {

  public static boolean checkMatrix(int[][] sudoku) {
    //prüfe ob Zeilenanzahl = 9 ist
    if (sudoku.length != 9) {
      return false;
    }

    //prüfe ob Spaltenanzahl = 9 ist
    for (int row = 0; row < 9; row++) {
      if (sudoku[row].length != 9) {
        return false;
      }
    }

    return true;
  }

  public static boolean checkDigits(int[][] sudoku) {
    //prüfe ob alle Einträge zwischen 1 und 9 liegen
    for (int row = 0; row < 9; row++) {
      for (int column = 0; column < 9; column++) {
        if ((sudoku[row][column] < 1) || (sudoku[row][column] > 9)) {
          return false;
        }
      }
    }

    return true;
  }

  public static boolean checkRows(int[][] sudoku) {
    //prüfe alle Zeilen auf Duplikate
    for (int row = 0; row < 9; row++) {
      boolean[] numbersSeen = new boolean[9];

      for (int column = 0; column < 9; column++) {
        int currentNumber = sudoku[row][column];
        if (numbersSeen[currentNumber - 1]) {
          return false;
        }

        numbersSeen[currentNumber - 1] = true;
      }
    }

    return true;
  }

  public static boolean checkColumns(int[][] sudoku) {
    //prüfe alle Spalten auf Duplikate
    for (int row = 0; row < 9; row++) {
      boolean[] numbersSeen = new boolean[9];

      for (int column = 0; column < 9; column++) {

        int currentNumber = sudoku[row][column];
        if (numbersSeen[currentNumber - 1]) {
          return false;
        }

        numbersSeen[currentNumber - 1] = true;
      }
    }

    return true;
  }


  public static boolean checkSubmatrices(int[][] sudoku) {
    //------
    for (int rowStart = 0; rowStart < 9; rowStart += 3) {
      for (int columnStart = 0; columnStart < 9; columnStart += 3) {
        boolean[] numbersSeen = new boolean[9];

        for (int row = 0; row < 3; row++) {
          for (int column = 0; column < 3; column++) {
            int currentNumber = sudoku[rowStart + row][columnStart + column];

            if (numbersSeen[currentNumber - 1]) {
              return false;
            }

            numbersSeen[currentNumber - 1] = true;

          }
        }
      }
    }

    return true;
  }

  public static boolean isValidSudokuSolution(int[][] sudoku) {
    if (!checkMatrix(sudoku)) {
      return false;
    }
    if (!checkDigits(sudoku)) {
      return false;
    }
    if (!checkRows(sudoku)) {
      return false;
    }
    if (!checkColumns(sudoku)) {
      return false;
    }
    if (!checkSubmatrices(sudoku)) {
      return false;
    }

    return true;
  }

  public static void main(String[] args) {

    // Initialisierung der beiden Sudokus
    int[][] sudoku1 = {
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

    int[][] sudoku2 = {
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

