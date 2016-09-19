import java.util.Scanner;

/**
 * Main entry point of program.
 */
public class ShamirSecretSharing {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        InputHandler handler = new InputHandler(args);
        handler.driver();
    }
}