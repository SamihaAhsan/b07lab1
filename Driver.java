public class Driver {
    public static void main(String[] args) {
        // Test empty polynomial
        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3));

        // Test polynomial 6x^3 + 1x^2 + 3x^1 + 5
        double[] c1 = {6, 1, 3, 5};
        int[] e1 = {3, 2, 1, 0};
        Polynomial p1 = new Polynomial(c1, e1);

        // Test polynomial 1x^4 - 2x^3 + 2x^2 + 3x + 5
        double[] c2 = {1, -2, 2, 3, 5};
        int[] e2 = {4, 3, 2, 1, 0};
        Polynomial p2 = new Polynomial(c2, e2);

        // Add the polynomials
        Polynomial s = p1.add(p2);

        System.out.println("s(0.1) = " + s.evaluate(0.1));

        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        // Multiply the polynomials
        Polynomial m = p1.multiply(p2);
        System.out.println("p1 * p2 evaluated at 1: " + m.evaluate(1));

        // Test saving to file
        s.saveToFile("poly_output.txt");
        System.out.println("Polynomial s saved to poly_output.txt");
    }
}