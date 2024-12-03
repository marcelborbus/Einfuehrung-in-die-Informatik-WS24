import java.util.Arrays;
import java.util.Random;

public class PackingStation {
    public static double[] selectPacking(double[][] item_weights, double total_weight) {
        // Knapsack Problem (9 choose 3) = 84 items
        int[] res = null;
        double min = Double.MAX_VALUE;

        int counter = 0;

        // brute force
        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 8; j++) {
                for (int k = j + 1; k < 9; k++) {
                    double a = item_weights[i % 3][i / 3];
                    double b = item_weights[j % 3][j / 3];
                    double c = item_weights[k % 3][k / 3];
                    double weight = a + b + c;

                    if (weight > total_weight && weight < min) {
                        res = new int[] { i, j, k };
                        min = weight;
                    }

                    System.out.println(counter++);
                }
            }
        }

        int i = res[0];
        int j = res[1];
        int k = res[2];

        double a = item_weights[i % 3][i / 3];
        double b = item_weights[j % 3][j / 3];
        double c = item_weights[k % 3][k / 3];

        item_weights[i % 3][i / 3] = -1;
        item_weights[j % 3][j / 3] = -1;
        item_weights[k % 3][k / 3] = -1;

        return new double[] { a, b, c };
    }

    public static void main(String[] args) {
        double[][] gurken = new double[3][3];

        Random rng = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gurken[i][j] = rng.nextInt(200, 300);
            }

            System.out.println("In: " + Arrays.toString(gurken[i]));
        }

        double[] selected = selectPacking(gurken, 750);
        System.out.println("Result: " + Arrays.toString(selected));

        for (int i = 0; i < gurken.length; i++) {
            System.out.println("Out: " + Arrays.toString(gurken[i]));
        }
    }
}
