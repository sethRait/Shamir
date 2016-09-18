import java.math.BigInteger;

/**
 * Created by sethr on 9/12/2016.
 */
public class SecretFind {
    public static byte combine(Share[] shares, BigInteger prime) {
        BigInteger secret;
        BigInteger finalSecret = BigInteger.ZERO;
        BigInteger temp;
        for (int i = 0; i < shares.length; i++) {
            secret = BigInteger.ONE;
            for (int j = 0; j < shares.length; j++) {
                if (j == i) continue;
                temp = shares[j].getx().subtract(shares[i].getx());
                temp = temp.modInverse(prime);
                secret = secret.multiply(shares[j].getx().multiply(temp));
            }
            secret=secret.multiply(shares[i].gety());
            finalSecret = finalSecret.add(secret);
        }
        finalSecret = finalSecret.mod(prime);
        return finalSecret.byteValueExact();
    }
}
