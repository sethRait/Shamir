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
        BigDecimal secret = BigDecimal.ZERO;
        while (numIter.hasNext()) {
            numerator = numIter.next().multiply(new BigDecimal(shares[yIndex].gety()));
            secret = secret.add(numerator.divide(denomIter.next()));
            yIndex++;
        }
        return secret.intValue();
        //return secret.byteValueExact();
    }
}
