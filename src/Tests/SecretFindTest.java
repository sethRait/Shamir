import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by sethr on 9/15/2016.
 */
public class SecretFindTest {
    @Test
    // Integration test
    public void combine() throws Exception {
        byte secret = 42;
        int threshold = 9;
        int numShares = 35;
        ByteShare sharer = new ByteShare(secret, threshold, numShares);
        BigInteger prime = sharer.getPrime();
        assert (SecretFind.combine(sharer.CreateShares(), prime) == (int)secret);
    }
}