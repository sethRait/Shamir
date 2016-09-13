import java.math.BigInteger;
import java.math.BigDecimal;

/**
 * Created by sethr on 9/12/2016.
 */
public class SecretFind {
    public static byte combine(Share[] shares, BigInteger prime) {
        double sum = 0;
        BigDecimal temp = BigDecimal.ONE;
        // Sum loop
        for (int i = 0; i < shares.length; i++) {
            temp = BigDecimal.ONE;
            // Product loop
            for (int j = 0; j < shares.length; j++) {
                if(j == i) continue;
                BigDecimal denominator = new BigDecimal(shares[j].getx().subtract(shares[i].getx()));
                temp = temp.multiply(new BigDecimal(shares[j].getx()).divide(denominator));
            }
            //multiply by y_i
            temp = temp.multiply(new BigDecimal(shares[i].gety()));
            sum += temp.intValue();
        }
        return (byte)sum;
    }
}
