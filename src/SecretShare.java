import java.math.BigInteger;
import java.util.Random;

/**
 * Created by sethr on 9/12/2016.
 **/
public class SecretShare {

    private final BigInteger PRIME;
    // bit-length of 10 for the prime is sufficient, since the secret cannot be greater than 2^8.
    private final int PRIME_LENGTH = 10;
    private int threshold;
    private int numshares;
    private byte secret;
    private int[] coefficients;

    // TODO(srait): Add error checking for numshares < threshold || numshares  > secret
    public SecretShare(byte secret, int threshold, int numshares) {
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
            // Creates uniformly distributed coefficients [1, PRIME)
            coefficients[i] = (1 + random.nextInt(PRIME.intValue() - 1));
        }
        coefficients[0] = secret;
    }

    public int[] getCoefficients() {
        return coefficients;
    }

    public BigInteger getPrime() {
        return PRIME;
    }

    // Returns array of tuples of the form (x, f(x) % p).
    // In order to reconstruct the polynomial using LaGrange Interpolation, all x coordinates
    // need to be co-prime to PRIME so the mod-inverse can be calculated.
    public Share[] CreateShares() {
        Share shares[] = new Share[numshares];
        int xCoord;
        BigInteger yCoord;
        for (xCoord = 1; xCoord <= numshares; xCoord++) {
            yCoord = BigInteger.ZERO;
            for (int index = 1; index < coefficients.length; index++) {
                yCoord = yCoord.add((BigInteger.valueOf(coefficients[index]).multiply((BigInteger.valueOf(xCoord).pow(index)))));
            }
            yCoord = yCoord.add(BigInteger.valueOf(coefficients[0]));
            shares[xCoord - 1] = new Share(xCoord, yCoord.mod(PRIME));
        }
        return shares;
    }
}
