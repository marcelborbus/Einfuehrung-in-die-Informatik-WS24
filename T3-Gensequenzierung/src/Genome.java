import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Genome {


    public static void ausgabe(String s, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            System.out.print(s.charAt(i));
        }

        System.out.println();
    }

    // atg
    public static void findStart(String genome){
        for (int i = 0; i < genome.length() - 2; i++) {
            if (genome.charAt(i) == 'a') {
                if (genome.charAt(i+1) == 't') {
                    if (genome.charAt(i+2) == 'g') {
                        int begin = i;
                        int end = findEnd(genome, i);
                        ausgabe(genome, begin, end);
                        i = end;
                    }
                }
            }
        }
    }

    // tag, tga oder taa
    public static int findEnd(String genome, int begin){
        for (int i = begin; i < genome.length() - 2; i += 3) {
            if (genome.charAt(i) == 't') {
                if (genome.charAt(i+1) == 'a') {
                    if (genome.charAt(i+2) == 'g') {
                        System.out.println("Start bei " + begin + " tag Ende bei " + i);

                        return i+2;

                    }
                }

                if (genome.charAt(i+1)== 'g'){
                    if (genome.charAt(i+2) == 'a') {
                        System.out.println("Start bei " + begin + " tga Ende bei " + i);
                        return i+2;
                    }
                }

                if (genome.charAt(i+1)== 'a') {
                    if (genome.charAt(i + 2) == 'a') {
                        System.out.println("Start bei " + begin + " taa Ende bei " + i);
                        return i+2;
                    }
                }
            }


        }

        return -1;
    }


    public static void main(String[] args) throws IOException { // args[0] = dateiname
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

        System.out.println();
    }

}
