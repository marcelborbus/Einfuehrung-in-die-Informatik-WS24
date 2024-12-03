public class Polynomial {
    private int degree = 0; // highest power of x
    private double[] coeffs; // Array with coefficients, all coefficients [0..degree] are necessary

    public Polynomial(double[] c) {
        if (c == null || c.length == 0) {
            this.degree = 0;
            this.coeffs = new double[] { 0 };
            return;
        }

        for (int i = c.length - 1; i > 0; i--) {
            if (c[i] != 0) {
                this.degree = i;
                break;
            }

        }

        this.coeffs = new double[this.degree + 1];
        for (int i = 0; i < this.degree + 1; i++) {
            this.coeffs[i] = c[i];
        }
    }

    public int getDegree() {
        return this.degree;
    }

    public double[] getAllCoefficients() {
        return this.coeffs;
    }

    public double getCoefficient(int i) {
        if (i > this.getDegree() || i < 0)
            return 0;
        return this.coeffs[i];
    }

    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < this.getDegree() + 1; i++) {
            if (i == 0)
                out.append(this.getCoefficient(0));
            else if (i == 1)
                out.append(" + " + this.getCoefficient(1) + " * x");
            else
                out.append(" + " + this.getCoefficient(i) + " * x^" + i);
        }

        return out.toString();
    }

    public double evaluate(double x) {
        double result = 0;

        for (int i = this.getDegree(); i >= 0; i--) {
            result = result * x + this.getCoefficient(i);
        }

        return result;
    }

    public Polynomial differentiate() {
        if (this.getDegree() == 0)
            return new Polynomial(null);

        double[] diffCoeffs = new double[this.getDegree()];

        for (int i = 0; i < this.getDegree(); i++) {
            diffCoeffs[i] = this.getCoefficient(i + 1) * (i + 1);
        }

        return new Polynomial(diffCoeffs);
    }

    public static void main(String[] args) {
        Polynomial f = new Polynomial(new double[] { 1, 2, 3, 4, 0, 0 });
        System.out.println(f);
        System.out.println(f.getDegree());
        System.out.println(f.evaluate(1));
        System.out.println(f.differentiate());
    }
}