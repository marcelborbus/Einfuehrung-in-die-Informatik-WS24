class Permutations {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: java Permutations <a> <b> ...");
      return;
    }

    generate(args, new int[]{});
  }

  public static void generate(String[] A, int[] selected) {
    // wenn wir jeden index einmal verwendet, haben ist die permutation komplett
    if (selected.length == A.length) {
      for (int i = 0; i < selected.length; i++) {
        System.out.print(A[selected[i]]);
      }

      System.out.println(); // newline
    }

    // wähle einen möglichen nächsten index und berechne damit eine permutation
    for (int i = 0; i < A.length; i++) {
      // n! möglichkeiten; n für 1. n-1 für 2. ...

      // wenn der aktuelle index schon ausgewählt ist, überspringe
      boolean skip = false;
      for (int j = 0; j < selected.length; j++) {
        if (i == selected[j]) {
          skip = true;
          break;
        }
      }

      if (skip) continue;

      // sonst
      int[] newSelected = new int[selected.length + 1];

      for (int j = 0; j < selected.length; j++) {
        newSelected[j] = selected[j];
      }

      newSelected[selected.length] = i;
      generate(A, newSelected);
    }
  }
}

