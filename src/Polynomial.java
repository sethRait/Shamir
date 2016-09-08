import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;

/**
 * Created by sethr on 9/4/2016.
 */
public class Polynomial {
    public int degree;
    private int[] coefficients;
    private final int FIELD_EXPONENT;
    private final int MAX_MEMBER;

    // Expects coefficients to be in increasing order. Ie. y = a + bx + cx^2 + dx^3 for values a, b, c, d.
    public Polynomial(int[] coefficients, int exponent) {
        boolean valid = checkValid(coefficients);
        if(valid) {
            this.coefficients = Arrays.copyOf(coefficients, coefficients.length);
            int degree = coefficients.length - 1;
            FIELD_EXPONENT = exponent;
            MAX_MEMBER = (int)Math.pow(2, exponent) -1;
        } else {
            throw new IllegalArgumentException("Coefficients not in Field");
        }
    }

   // Creates a new random monic polynomial in GF(2^exponent) of degree 'degree' which is guaranteed to be irreducible.
   // The constant term of the polynomial is always 0.
    public Polynomial(int degree, int exponent) {
        coefficients = new int[degree];
        FIELD_EXPONENT = exponent;
        MAX_MEMBER = (int)Math.pow(2, exponent) -1;
        for(int i = 1; i < degree -1; i++) {
            Random random = new Random();
            coefficients[i] = random.nextInt(MAX_MEMBER + 1);
        }
        coefficients[degree] = 1;
    }

    // Ensures that all coefficients are in the field specified.
    private boolean checkValid(int[] vals) {
        for(int val : vals) {
            if (val > MAX_MEMBER) {
                return false;
            }
        }
        return true;
    }

    // Determines if the polynomial is irreducible in the given field using a modified Ben-Or Irreducibility Test
//    public boolean isIrreducible() {
//        int g;
//        for (int i = 0; i <= degree/2; i++) {
//            //g = gcd()
//        }
//    }

    public Polynomial divide(Polynomial that) {
        if(this.degree == 0 || that.degree == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        if(this.degree < that.degree) {
            return null;
        }
        int m = this.degree;
        int n = that.degree;
        int[] quotient = new int[m - n];
        int remainder[];
        for(int k = m - n; k >= 0; k--) {
            quotient[k] = coefficients[n + k]/that.coefficients[n];
            // Replace this polynomial with a polynomial of degree < n + k
            for(int j = n + k -1; j >= k; j--) {
                this.coefficients[j] = this.coefficients[j] - (int)Math.pow(quotient[j], that.coefficients[j - k]);
            }
        }
        return null;
    }


    private int gcd(int a, int b) {
        int r;
        if(a == 0) {
            return b;
        }
        if(b == 0) {
            return a;
        }
        if(a > b) {
            r = a % b;
            return gcd(b, r);
        }
        else {
            r = b % a;
            return gcd(a, r);
        }
    }
}
