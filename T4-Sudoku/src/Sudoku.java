public class Sudoku {

  public static boolean checkMatrix(int[][] sudoku) {
    //prüfe ob Zeilenanzahl = 9 ist
    if (sudoku.length != 9) {
      return false;
    }

    //prüfe ob Spaltenanzahl = 9 ist
    for (int row = 0; row < 9; row++) {
      if (sudoku[row].length != 9){
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
        if (numbersSeen[currentNumber - 1]) return false;
        numbersSeen[currentNumber - 1] = true;

                /* oder so
                for (int column2 = column + 1; column2 < 9; column2++) {
                    if (sudoku[row][column] == sudoku[row][column2]) {
                        return false;
                    }
                } */
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
        if (numbersSeen[currentNumber - 1]) return false;
        numbersSeen[currentNumber - 1] = true;

               /* oder so
               for (int row2 = row + 1; row2 < 9; row2++) {
                    if (sudoku[row][column] == sudoku[row2][column]) {
                        return false;
                    }
                } */
      }
    }

    return true;
  }


  public static boolean checkSubmatrices(int[][] sudoku) {
    //------
    for (int rowStart = 0; rowStart < 9; rowStart += 3) {
      for (int columnStart = 0; columnStart < 9; columnStart += 3) {
        // System.out.printf("Block %s %s ab Reihe %s ab Spalte %s\n", rowStart , columnStart, rowStart*3, columnStart*3); // 0 3 6

        boolean[] numbersSeen = new boolean[9];

        for (int row = 0; row < 3; row++) {
          for (int column = 0; column < 3; column++) {
            int currentNumber = sudoku[rowStart + row][columnStart + column];

            if (numbersSeen[currentNumber - 1]) {
              // System.out.printf("%s @ %s %s\n", currentNumber, rowStart + row, columnStart + column);
              // System.out.println(Arrays.toString(numbersSeen));
              return false;
            }

            numbersSeen[currentNumber - 1] = true;

          }
        }
      }
    }

    return true;
  }

  public static boolean isValidSudokuSolution(int[][] sudoku){
    if (checkMatrix(sudoku)) {
      System.out.println("Gültiges Spielfeld");
      if (checkDigits(sudoku)) {
        System.out.println("Gültige Zahlen");
        if (checkRows(sudoku)) {
          System.out.println("Alle Zeilen korrekt");
        } else {
          System.out.println("Nicht alle Zeilen korrekt");
          return false;
        }
        if (checkColumns(sudoku)) {
          System.out.println("Alle Spalten korrekt");
        } else {
          System.out.println("Nicht alle Spalten korrekt");
          return false;
        }

        if(checkSubmatrices(sudoku)) {
          System.out.println("Alle Untermatrizen sind korrekt");
        }
      } else if (!checkDigits(sudoku)) {
        System.out.println("Nicht alle Zahlen gültig");
        return false;
      }
    } else if (!checkMatrix(sudoku)) {
      System.out.println("Kein gültiges Spielfeld");
      return false;
    }
    return true;
  }

  public static void main(String[] args) {

    // Initialisierung der beiden Sudokus
    int[][] sudoku1 = {
        {1,2,3,4,5,6,7,8,9},
        {7,8,9,1,2,3,4,5,6},
        {4,5,6,7,8,9,1,2,3},
        {3,1,2,8,4,5,9,6,7},
        {6,9,7,3,1,2,8,4,5},
        {8,4,5,6,9,7,3,1,2},
        {2,3,1,5,7,4,6,9,8},
        {9,6,8,2,3,1,5,7,4},
        {5,7,4,9,6,8,2,3,1}};

    int[][] sudoku2 = {
        {1,2,3,4,5,6,7,8,9},
        {7,8,9,1,6,2,4,5,3},
        {4,5,6,7,8,9,1,2,3},
        {1,2,8,4,5,9,6,7,3},
        {6,9,7,3,1,2,8,4,5},
        {6,4,5,8,9,7,3,1,2},
        {1,3,2,5,7,4,6,9,8},
        {1,6,8,2,3,1,5,7,9},
        {5,7,4,9,6,8,2,3,1}};

    isValidSudokuSolution(sudoku1);
  }
}

