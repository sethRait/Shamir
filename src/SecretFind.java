import java.math.BigInteger;
import java.math.BigDecimal;

/**
 * Created by sethr on 9/12/2016.
 */
public class SecretFind {
    public static byte combine(int[][] shares, BigInteger prime) {
        double sum = 0;
        double temp;
        // Sum loop
        for (int i = 0; i < shares.length; i++) {
            temp = 1;
            // Product loop
            for (int j = 0; j < shares.length; j++) {
                if(j == i) continue;
                // TODO(srait): Use BigDecimal and check for rationals to inverse-mod
                temp *= shares[j][0]/((double)shares[j][0] - (double)shares[i][0]);
            }
            //multiply by y_i
            temp *= shares[i][1];
            sum += temp;
        }
        return (byte)sum;
    }
}
