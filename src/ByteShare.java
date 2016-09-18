import java.math.BigInteger;
import java.util.Random;

/**
 * Created by sethr on 9/12/2016.
 **/
public class ByteShare {

    public static final BigInteger PRIME = BigInteger.valueOf(1021);
    // bit-length of 8 for the prime is sufficient, since the secret cannot be greater than 2^8.
    private final int PRIME_LENGTH = 10;
    private int threshold;
    private int numshares;
    private byte secret;
    private int[] coefficients;

    public ByteShare(byte secret, int threshold, int numshares) {
        if(numshares < threshold) {
            throw new IllegalArgumentException("Threshold cannot be larger than the total number of shares.");
        }
        this.secret = secret;
        this.threshold = threshold;
        this.numshares = numshares;
        //PRIME = BigInteger.probablePrime(PRIME_LENGTH, new Random());
        CreatePolynomial();
    }

    // Returns an array of coefficients where the highest order coefficient is last.
    protected void CreatePolynomial() {
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
    public Share[] CreateShares(int numCurrentShares, int numRequestedShares) {
        Share shares[] = new Share[numRequestedShares];
        int xCoord;
        BigInteger yCoord;
        BigInteger product;
        for (xCoord = numCurrentShares + 1; xCoord <= numRequestedShares; xCoord++) {
            yCoord = BigInteger.ZERO;
            for (int index = 1; index < coefficients.length; index++) {
                product = BigInteger.valueOf((coefficients[index])).multiply(BigInteger.valueOf(xCoord).pow(index));
                yCoord = yCoord.add(product);
            }
            yCoord = yCoord.add(BigInteger.valueOf(coefficients[0]));
            shares[xCoord - 1] = new Share(xCoord, yCoord.mod(PRIME));
        }
        return shares;
    }

    public Share[] CreateShares() {
        return CreateShares(0, numshares);
    }
}
