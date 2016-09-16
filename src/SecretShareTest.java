import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by sethr on 9/15/2016.
 */
public class SecretShareTest {
    private byte secret;
    private int threshold;
    private int numShares;
    private int[] coefficients;
    private BigInteger prime;
    SecretShare sharer;

    @Before
    public void setUp() throws Exception {
        secret = 42;
        threshold = 27;
        numShares = 1700;
        sharer = new SecretShare(secret, threshold, numShares);
        prime = sharer.getPrime();
    }

    @Test
    public void createPolynomial() throws Exception {
        int[] coefficients = sharer.getCoefficients();
        assert coefficients.length == threshold;
        for (int i : coefficients) {
            assert i < prime.intValue() && i > 0;
        }
    }

    @Test
    public void createShares() throws Exception {
        Share[] shares = sharer.CreateShares();
        assert shares.length == numShares;
        for (Share s : shares) {
            assert s.gety().intValue() == s.gety().mod(prime).intValue();
        }
    }
}