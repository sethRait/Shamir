import java.util.Arrays;

/**
 * Created by sethr on 9/12/2016.
 */
public class Tests {
    public static void main(String[] args) {
        byte secret = 8;
        Shamir shamir = new Shamir(secret, 4, 3);
        shamir.CreatePolynomial();
        int[] coef = shamir.getCoefficients();
        int[][] shares = shamir.CreateShares();
        System.out.print("f(x) = " + coef[0] + "x^3");
        for(int i = 1; i < coef.length; i++) {
            System.out.print(" + " + coef[i] + "x^" + (coef.length - i));
        }
        System.out.println();
        for(int i = 0; i < shares.length; i++) {
            System.out.println(Arrays.toString(shares[i]));
        }

    }

}
