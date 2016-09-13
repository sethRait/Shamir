import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by sethr on 9/12/2016.
 */
public class Tests {
    public static void main(String[] args) {
        byte secret = 8;
        Shares shamir = new Shares(secret, 3, 4);
        shamir.CreatePolynomial();
        BigInteger prime = shamir.getPRIME();
        int[] coef = shamir.getCoefficients();
        int[][] shares = shamir.CreateShares();
        System.out.print("f(x) = " + coef[0]);
        for(int i = 1; i < coef.length; i++) {
            System.out.print(" + " + coef[i] + "x^" + i);
        }
        System.out.println();
        for(int i = 0; i < shares.length; i++) {
            System.out.println(Arrays.toString(shares[i]));
        }
        int[][] reconShares = {shares[0], shares[1], shares[2]};
        System.out.println(SecretFind.combine(reconShares, prime));

    }

}
