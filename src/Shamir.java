import java.math.BigInteger;
import java.util.Random;

/**
 * Created by sethr on 9/12/2016.
 */
public class Shamir {
    private final BigInteger PRIME;
    // bit-length of 10 for the prime is sufficient, since the secret cannot be greater than 2^8.
    private final int PRIME_LENGTH = 10;
    private int threshold;
    private int numshares;
    private byte secret;
    private int[] coefficients;

    // TODO(srait): Add error checking for numshares < threshold || numshares  > secret
    public Shamir(byte secret, int threshold, int numshares) {
        this.secret = secret;
        this.threshold = threshold;
        this.numshares = numshares;
        PRIME = BigInteger.probablePrime(PRIME_LENGTH, new Random());
    }

    // Returns an array of coefficients not including the y-intercept constant,
    // where the highest order coefficient is first.
    public void CreatePolynomial() {
        coefficients = new int[threshold - 1];
        Random random = new Random();
        for (int i = 0; i < coefficients.length; i++) {
            coefficients[i] = random.nextInt(PRIME.intValue());
        }
    }

    // Returns tuples of the form (x, f(x) % p)
    public int[][] CreateShares() {
        int shares[][] = new int[numshares][2];
        int xCoord, yCoord;
        for (xCoord = 0; xCoord <= numshares; xCoord++) {
            yCoord = 0;
            for (int index = 0; index < coefficients.length; index++) {
                yCoord += coefficients[index] * Math.pow(xCoord, (coefficients.length - index));
            }
            yCoord %= PRIME.intValue();
            shares[xCoord][0] = xCoord;
            shares[xCoord][1] = yCoord;
        }
        return shares;

    }
}
