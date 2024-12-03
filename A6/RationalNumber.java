public class RationalNumber implements Comparable<RationalNumber> {
  // ---------------------------------------------------------------------------//
  private int num = 0;
  private int denom = 1;

  // ---------------------------------------------------------------------------//
  public RationalNumber() {
    throw new Error("No numerator, no denominator.");
  }

  public RationalNumber(int n, int d) {
    if (d == 0)
      throw new ArithmeticException("Denominator can't be zero.");

    // abs(x)
    int a = (n < 0) ? -n : n;
    int b = (d < 0) ? -d : d;

    // ggT
    while (a != b) {
      if (a > b)
        a = a - b;
      else
        b = b - a;
    }

    // normiert
    this.num = ((d < 0) ? -n : n) / a;
    this.denom = ((d < 0) ? -d : d) / a;
  }

  // ---------------------------------------------------------------------------//
  public int getNum() {
    return this.num;
  }

  // ---------------------------------------------------------------------------//
  public int getDenom() {
    return this.denom;
  }

  // ---------------------------------------------------------------------------//
  public int compareTo(RationalNumber n) {
    int num1 = this.getNum() * n.getDenom();
    int num2 = n.getNum() * this.getDenom();
    if (num1 == num2)
      return 0;
    return (num1 < num2) ? -1 : 1;
  }

  // ---------------------------------------------------------------------------//
  public RationalNumber add(RationalNumber r) {
    int numerator = this.getNum() * r.getDenom() + r.getNum() * this.getDenom();
    int denominator = this.getDenom() * r.getDenom();
    return new RationalNumber(numerator, denominator);
  }

  // ---------------------------------------------------------------------------//
  public RationalNumber div(RationalNumber r) {
    int numerator = this.getNum() * r.getDenom();
    int denominator = this.getDenom() * r.getNum();
    return new RationalNumber(numerator, denominator);
  }

  // ---------------------------------------------------------------------------//
  public String toString() {
    return this.num + " / " + this.denom;
  }

  // ---------------------------------------------------------------------------//
  public static void main(String[] args) {
    RationalNumber q1 = new RationalNumber(2, 3);
    RationalNumber q2 = new RationalNumber(4, -2);
    RationalNumber q3 = new RationalNumber(-1, -2);

    System.out.println(q1);
    System.out.println(q2);
    System.out.println(q3);
    System.out.println(q1.add(q3));

    System.out.println(q2.div(q3));
  }
}
