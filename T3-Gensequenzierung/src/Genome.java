import java.io.File;
import java.util.Scanner;

public class Genome {

  // printe Gen von Start bis Ende
  public static void ausgabe(String s, int begin, int end) {
    System.out.println("Start bei " + begin + " tag Ende bei " + end);

    for (int i = begin; i <= end; i++) {
      System.out.print(s.charAt(i));
    }

    System.out.println();
  }

  // Finde das Startkodon
  public static void findStart(String genome) {
    for (int i = 0; i < genome.length() - 2; i++) {
      if (genome.charAt(i) == 'a') { // Start mit ATG
        if (genome.charAt(i + 1) == 't') {
          if (genome.charAt(i + 2) == 'g') {
            int begin = i;
            int end = findEnd(genome, i); // suche weiter, wo das letzte Gen aufhört
            ausgabe(genome, begin, end);
            i = end;
          }
        }
      }
    }
  }

  // finde ein mögliches Endkodon
  public static int findEnd(String genome, int begin) {
    for (int i = begin; i < genome.length() - 2; i += 3) { // Kodons haben immer Länge 3k
      if (genome.charAt(i) == 't') { // tag, tga oder taa
        if (genome.charAt(i + 1) == 'a') {
          if (genome.charAt(i + 2) == 'g') {
            return i + 2;

          }
        }

        if (genome.charAt(i + 1) == 'g') { // Tga
          if (genome.charAt(i + 2) == 'a') {
            return i + 2;
          }
        }

        if (genome.charAt(i + 1) == 'a') { // Taa
          if (genome.charAt(i + 2) == 'a') {
            return i + 2;
          }
        }
      }
    }

    return -1; // return falls es kein Ende gibt
  }


  public static void main(String[] args) { // args[0] = dateiname
    if (args.length == 0) {
      System.out.println("Bitte gib eine Datei an");
      return;
    }

    String genome;

    try {
      File ecoli = new File(args[0]);
      Scanner sc = new Scanner(ecoli);
      genome = sc.useDelimiter("\\Z").next();
      sc.close();
    } catch (Exception e) {
      System.out.println("Datei existiert nicht oder ist besetzt.");
      return;
    }

    findStart(genome);
  }
}
