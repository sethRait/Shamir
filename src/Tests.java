/**
 * Created by sethr on 9/12/2016.
 */
public class Tests {
    public static void main(String[] args) {
        byte secret = 8;
        Shamir shamir = new Shamir(secret, 4, 3);
        shamir.CreatePolynomial();
    }

}
