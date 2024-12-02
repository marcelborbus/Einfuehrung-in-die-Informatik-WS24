public class Quaternion {
    public double a, b, c, d;

    Quaternion(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public Quaternion add(Quaternion val) {
        this.a += val.a;
        this.b += val.b;
        this.c += val.c;
        this.d += val.d;
        return this;
    }

    public Quaternion subtract(Quaternion val) {
        this.a -= val.a;
        this.b -= val.b;
        this.c -= val.c;
        this.d -= val.d;
        return this;
    }

    public Quaternion multiply(Quaternion val) {
        // s. Wikipedia
        this.a = (this.a * val.a) - (this.b * val.b) - (this.c * val.c) - (this.d * val.d);
        this.b = (this.a * val.b) + (this.b * val.a) + (this.c * val.d) - (this.d * val.c);
        this.c = (this.a * val.c) - (this.b * val.d) + (this.c * val.a) + (this.d * val.a);
        this.d = (this.a * val.d) + (this.b * val.c) - (this.c * val.d) + (this.d * val.a);
        return this;
    }

    public Quaternion divide(Quaternion val) {
        // s. Wikipedia (engl.)
        // multiply with multiplicative inverse
        double norm = (val.a * val.a) + (val.b * val.b) + (val.c * val.c) + (val.d * val.d);

        if (norm == 0) {
            throw new ArithmeticException("Division by zero.");
        }

        // multiplicative inverse
        double a = val.a / norm;
        double b = -val.b / norm;
        double c = -val.c / norm;
        double d = -val.d / norm;

        // inline multiplication so we don't have to create a new Quaternion(...) for
        // .multiply(...)
        this.a = (this.a * a) - (this.b * b) - (this.c * c) - (this.d * d);
        this.b = (this.a * b) + (this.b * a) + (this.c * d) - (this.d * c);
        this.c = (this.a * c) - (this.b * d) + (this.c * a) + (this.d * a);
        this.d = (this.a * d) + (this.b * c) - (this.c * d) + (this.d * a);
        return this;
    }

    public String toString() {
        return "%f + %fi + %fj + %fk".formatted(this.a, this.b, this.c, this.d);
    }

    public static void main(String[] args) {
        Quaternion q1 = new Quaternion(0, 0, 0, 0); // entspricht der reellen 0
        Quaternion q2 = new Quaternion(0, 1, 1, 1); // 0 + i + j + k
        Quaternion q3 = new Quaternion(0, 0, 1, 1); // 0 + j + k
        Quaternion q4 = new Quaternion(0, 0, 0, 1); // 0 + k
        q1.add(q2).add(q3).add(q4); // q1 hat jetzt den Wert (0, 1, 2, 3)
                                    // der Wert aller anderen Variablen ist unverändert.

        q1.divide(q4);
        System.out.println(q1);

    }
}
