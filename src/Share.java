import java.math.BigInteger;

/**
 * Created by sethr on 9/13/2016.
 */
    // Represents individual shares as a tuple (x, y)
public class Share {
    private int xVal;
    private BigInteger yVal;

    public Share(int xVal, int yVal) {
        this.xVal = xVal;
        this.yVal = BigInteger.valueOf(yVal);
    }

    public Share(int xVal, BigInteger yVal) {
        this.xVal = xVal;
        this.yVal = yVal;
    }

    public int getx() {
        return xVal;
    }

    public BigInteger gety() {
        return yVal;
    }
}
