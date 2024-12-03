import java.util.Arrays;
import java.util.Random;

public class PackingStation {
    public static double[] selectPacking(double[][] item_weights, double total_weight) {
        // Knapsack Problem (9 choose 3) = 84 items

        return null;
    }

    public static void main(String[] args) {
        double[][] gurken = new double[3][3];

        Random rng = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gurken[i][j] = rng.nextInt(200, 300);
            }

            System.out.println(Arrays.toString(gurken[i]));
        }

        double[] selected = selectPacking(gurken, 750);
        System.out.println(Arrays.toString(selected));
    }
}
