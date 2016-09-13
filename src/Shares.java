import java.math.BigInteger;
import java.util.Random;

/**
 * Created by sethr on 9/12/2016.
 */
public class Shares {
    private final BigInteger PRIME;
    // bit-length of 10 for the prime is sufficient, since the secret cannot be greater than 2^8.
    private final int PRIME_LENGTH = 10;
    private int threshold;
    private int numshares;
    private byte secret;
    private int[] coefficients;

    // TODO(srait): Add error checking for numshares < threshold || numshares  > secret
    // TODO(srait): Shares need to be BigIntegers to avoid integer overflow
    public Shares(byte secret, int threshold, int numshares) {
        this.secret = secret;
        this.threshold = threshold;
        this.numshares = numshares;
        PRIME = BigInteger.probablePrime(PRIME_LENGTH, new Random());
    }

    // Returns an array of coefficients where the highest order coefficient is last.
    public void CreatePolynomial() {
        coefficients = new int[threshold];
        Random random = new Random();
        for (int i = 1; i < coefficients.length; i++) {
            // Creates a uniformly distributed number [1, PRIME)
            coefficients[i] = random.nextInt(PRIME.intValue());
        }
        coefficients[0] = secret;
    }

    public int[] getCoefficients() {
        return coefficients;
    }

    public BigInteger getPRIME() {
        return PRIME;
    }

    // Returns tuples of the form (x, f(x) % p).
    // In order to reconstruct the polynomial using LaGrange Interpolation, all x coordinates
    // need to be coprime to PRIME so the mod-inverse can be calculated.
    public int[][] CreateShares() {
        int shares[][] = new int[numshares][2];
        int xCoord, yCoord;
        for (xCoord = 1; xCoord <= numshares; xCoord++) {
            yCoord = 0;
            for (int index = 1; index < coefficients.length; index++) {
                yCoord += coefficients[index] * Math.pow(xCoord, index);
            }
            yCoord += coefficients[0];
            shares[xCoord - 1][0] = xCoord;
            shares[xCoord - 1][1] = yCoord;
        }
        return shares;
    }
}
