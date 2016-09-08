/**
 * Created by sethr on 9/1/2016.
 */

import java.nio.charset.StandardCharsets;
import java.util.*;

public class Algorithm {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.print("Secret: ");
        String secret = console.next();
        byte[] bytes = secret.getBytes(StandardCharsets.US_ASCII);
        System.out.print("\nTotal number of shares: ");
        int totalShares = console.nextInt();
        System.out.print("\n Threshold: ");
        int threshold = console.nextInt();
        checkValid(secret, totalShares, threshold);
        createShares(bytes, totalShares, threshold);
    }

    private static void createShares(byte[] bytes, int totalShares, int threshold) {
        for(byte b : bytes){
            createShares(b, totalShares, threshold);
        }
    }

    private static void createShares(byte b, int totalShares, int threshold) {
        int[] coefficients = new int[threshold - 1];
        // Set the coefficients to be random numbers [0, 255]
        for(int i = 0; i < coefficients.length; i++) {
            // Create and destroy the Random object each time to ensure a new seed for every entry.
            Random random = new Random();
            coefficients[i] = random.nextInt(256);
        }
    }


    private static void checkValid(String secret, int totalShares, int threshold)throws Exception{
        boolean valid = true;
        if(secret.length() < 1)
            valid = false;
        if(secret.length() < totalShares)
            valid = false;
        if(threshold > totalShares)
            valid = false;
        if(!valid)
            throw new Exception("Invalid Parameters");
    }

    public static void PrintShares(byte[] bytes, String totalShares, String threshold) {
        for(byte b : bytes)
            System.out.println(b);
    }


}
