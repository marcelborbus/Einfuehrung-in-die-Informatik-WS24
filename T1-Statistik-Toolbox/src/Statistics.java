public class Statistics {
  public static float variance(int[] x, int n) {
    float sum = sum(x);
    float variance = 0;

    /* Alle Zahlen des Arrays addieren und anschließend durch
    die Anzahl der Werte teilen, um den Mittelwert zu berechnen */
    float mean = sum / n;

    /* Den Mittelwert vom i-ten Wert des Arrays subtrahieren und
    das Ergebnis quadrieren. Summe der Quadrate bilden und anschließend
    durch die Anzahl der Werte teilen. */
    for (int i = 0; i < n; i++) {
      variance += ((x[i] - mean) * (x[i] - mean));
    }

    variance /= n;

    return variance;
  }

  public static int sum(int[] x) {
    int sum = 0;
    for (int i = 0; i < x.length; i++) sum += x[i];
    return sum;
  }

  public static float standardDeviation(int[] x) {
    float variance = variance(x, x.length);

    // Quadratwurzel der Varianz ergibt Standardabweichung
    float stddev = variance;
    float error = 0.0001f; // Fehlertoleranz
    while ((stddev - (variance / stddev)) > error) stddev = (stddev + (variance / stddev)) / 2;

    return stddev;
  }

  public static void main(String[] args) {
    final String invalidArgsMsg =
        "Error: Arguments must be valid integers.\nUsage: java Statistics <integer> <integer> ...";

    // wenn es keinen Input gibt, print Error
    if (args.length == 0) {
      System.out.println(invalidArgsMsg);
      return;
    }

    int[] data = new int[args.length];

    // wenn Input keine Zahl ist, print Error
    for (int i = 0; i < args.length; i++) {
      try { // error handling
        data[i] = Integer.parseInt(args[i]);
      } catch (NumberFormatException e) {
        System.out.println(invalidArgsMsg);
        return;
      }
    }

    // zähle alle Inputs im Format dataSet[x] = {1, 2} := {<InputZahl>, <Anzahl>}
    int[][] dataSet = new int[data.length][2]; // unsorted
    int uniqueCount = 0;

    // wenn dataSet Input enthält ...
    for (int i = 0; i < data.length; i++) {
      boolean found = false;

      // ... erhöhe Anzahl um 1 ...
      for (int j = 0; j < i; j++) {
        if (dataSet[j][0] == data[i]) {
          dataSet[j][1] += 1;
          found = true;
          break;
        }
      }

      // ... sonst füge an letzter Stelle mit Anzahl = 1 hinzu
      if (!found) {
        dataSet[uniqueCount][0] = data[i];
        dataSet[uniqueCount][1] = 1;
        uniqueCount += 1;
      }
    }

    float mean = (float) sum(data) / data.length; // muss ergänzt werden
    int sum = sum(data); // muss ergänzt werden
    float standardDeviation = standardDeviation(data); // Standardabweichung
    float variance = variance(data, data.length); // Varianz

    // Output

    System.out.println("Mittelwert: " + mean);
    System.out.println("Summe: " + sum);
    System.out.println("Standardabweichung: " + standardDeviation);
    System.out.println("Varianz: " + variance);
    System.out.println("Histogram: ");

    for (int i = 0; i < uniqueCount; i++) {
      System.out.print(dataSet[i][0] + ": ");

      for (int j = 0; j < dataSet[i][1]; j++) {
        System.out.print('*');
      }

      System.out.println();
    }
  }
}
