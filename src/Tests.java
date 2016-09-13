import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by sethr on 9/12/2016.
 */
// TODO(srait): Use JUnit or Google for tests
public class Tests {
    public static void main(String[] args) {
        byte secret = 87;
        int threshold = 3;
        SecretShare shamir = new SecretShare(secret, threshold, 5);
        shamir.CreatePolynomial();
        BigInteger prime = shamir.getPrime();
        int[] coef = shamir.getCoefficients();
        Share[] shares = shamir.CreateShares();
        System.out.print("f(x) = " + coef[0]);
        for(int i = 1; i < coef.length; i++) {
            System.out.print(" + " + coef[i] + "x^" + i);
        }
        System.out.println();
        System.out.println(Arrays.toString(shares));
        Share[] reconShares = new Share[threshold];
        for (int i = 0; i < threshold; i++) {
            reconShares[i] = shares[i];
        }
        System.out.println(SecretFind.combine(reconShares, prime));
    }

}
