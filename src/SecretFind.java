import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sethr on 9/12/2016.
 */
public class SecretFind {
    public static int combine(Share[] shares, BigInteger prime) {
        BigDecimal numerator = BigDecimal.ONE;
        BigDecimal denominator = BigDecimal.ONE;
        // Place the numerators and denominators in arrays and calculate the final value at the end to avoid
        // non-terminating decimal expansions.
        // The number of terms in the LaGrange equation is equal to n * (n-1), where n = shares.length
        List<BigDecimal> numerators = new LinkedList<>();
        List<BigDecimal> denominators = new LinkedList<>();
        for (int i = 0; i < shares.length; i++) {
            for (int j = 0; j < shares.length; j++) {
                if (j == i) continue;
                denominator = denominator.multiply(new BigDecimal(shares[j].getx().subtract(shares[i].getx())));
                numerator = numerator.multiply(new BigDecimal(shares[j].getx()));
            }
            numerators.add(numerator);
            denominators.add(denominator);
            denominator = BigDecimal.ONE;
            numerator = BigDecimal.ONE;
        }

        Iterator<BigDecimal> numIter = numerators.iterator();
        Iterator<BigDecimal> denomIter = denominators.iterator();
        int yIndex = 0;
        BigInteger secret = BigInteger.ZERO;
        while (numIter.hasNext()) {
            numerator = numIter.next().multiply(new BigDecimal(shares[yIndex].gety()));
            secret = secret.add(numerator.toBigInteger().modInverse(prime).multiply(denomIter.next().toBigInteger()));
            yIndex++;
        }
        secret = secret.mod(prime);
        return secret.intValue();
        //return secret.byteValueExact();
    }

    public static int combine2(Share[] shares, BigInteger prime) {
        BigInteger secret = BigInteger.ONE;
        BigInteger finalSecret = BigInteger.ZERO;
        BigInteger temp = BigInteger.ONE;
        List<BigDecimal> temps = new LinkedList<>();
        for (int i = 0; i < shares.length; i++) {
            secret = BigInteger.ONE;
            for (int j = 0; j < shares.length; j++) {
                if (j == i) continue;
                temp = shares[j].getx().subtract(shares[i].getx());
                temp = temp.modInverse(prime);
                System.out.print(shares[j].getx()+" ");
                System.out.println(temp.toString() + " ");
                secret = secret.multiply(shares[j].getx().multiply(temp));
                System.out.println(secret);
            }
            System.out.println(shares[i].gety());
            secret=secret.multiply(shares[i].gety());
            System.out.println(secret.toString() + " ");
            finalSecret = finalSecret.add(secret);
            System.out.println(finalSecret.toString());
            System.out.println();
        }
        System.out.println();
        finalSecret = finalSecret.mod(prime);
        return finalSecret.intValue();
    }

    public static int combine3(Share[] shares, BigInteger prime) {
        BigInteger numerator = BigInteger.ONE;
        BigInteger denominator = BigInteger.ONE;
        BigInteger quotient = BigInteger.ZERO;
        BigInteger sum = BigInteger.ZERO;
        for (int j = 0; j < shares.length; j++) {
            for (int m = 0; m < shares.length; m++) {
                if (m == j) continue;
                numerator = numerator.multiply(shares[m].getx());
                denominator = denominator.multiply(shares[m].getx().subtract(shares[j].getx()));
                denominator = denominator.modInverse(prime);
                quotient = denominator.multiply(numerator);
            }
            sum = sum.add(quotient).multiply(shares[j].gety());
        }
        return sum.mod(prime).intValue();
    }
}
