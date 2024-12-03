import java.util.Arrays;

public class Food implements Comparable<Food> {
    private TYPE type;
    private int calories;

    private static enum TYPE {
        Apple, Pear, Cookie, Grape
    };

    public Food(TYPE type, int calories) {
        this.type = type;
        this.calories = calories;
    }

    public Food(String type, int calories) {
        this.type = Food.TYPE.valueOf(type);
        this.calories = calories;
    }

    public String toString() {
        return this.getType() + " " + this.getCalories() + " kcal";
    }

    public int compareTo(Food o) {
        int a = this.getType().length() * this.getCalories();
        int b = o.getType().length() * o.getCalories();
        if (a == b)
            return 0;
        return (a > b) ? 1 : -1;
    }

    public String getType() {
        return this.type.name();

    }

    public int getCalories() {
        return this.calories;
    }

    public static Food[] createSortedRandomList(int n) {
        Food[] arr = new Food[n];

        for (int i = 0; i < n; i++) {
            int calories = (int) (Math.random() * 1_000);
            int type = (int) (Math.random() * Food.TYPE.values().length);
            arr[i] = new Food(Food.TYPE.values()[type], calories);
        }

        Arrays.sort(arr);

        return arr;
    }

    public static void main(String[] args) {
        Food apple = new Food(Food.TYPE.Apple, 300);
        Food pear = new Food(Food.TYPE.Pear, 300);
        System.out.println(apple);
        System.out.println(pear);

        Food[] plate = { apple, pear };
        System.out.println(Arrays.toString(plate));
        Arrays.sort(plate);
        System.out.println(Arrays.toString(plate));

        Food[] obstKorb = Food.createSortedRandomList(10);
        System.out.println(Arrays.toString(obstKorb));
        Arrays.sort(obstKorb);
        System.out.println(Arrays.toString(obstKorb));
    }
}