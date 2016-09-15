import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by sethr on 9/12/2016.
 */
// TODO(srait): Use JUnit or Google for tests
public class Tests {
    public static void main(String[] args) {
        byte secret = 17;
        int threshold = 3;
        //SecretShare shamir = new SecretShare(secret, threshold, 4);
        //shamir.CreatePolynomial();
        //BigInteger prime = shamir.getPrime();
        //System.out.println("prime: " + prime.toString());
        //int[] coef = shamir.getCoefficients();
        //Share[] shares = shamir.CreateShares();
        //System.out.print("f(x) = " + coef[0]);
        //for(int i = 1; i < coef.length; i++) {
        //    System.out.print(" + " + coef[i] + "x^" + i);
       // }
        //System.out.println();
        //System.out.println(Arrays.toString(shares));
        Share[] reconShares = {new Share(14, 22), new Share(2, 8), new Share(21, 5)};
        System.out.println(SecretFind.combine2(reconShares, BigInteger.valueOf(23)));
    }
}
