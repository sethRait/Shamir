public class ShamirSecretSharing {
    public static void main(String[] args) {
        InputHandler handler = new InputHandler(new String[]{"share", "C:\\Users\\sethr\\IdeaProjects\\Shamir\\src\\Tests\\TestSecret.txt", "3", "4"});
        handler.driver();
        Point[] shares = handler.getShares();
        int prime = handler.prime;
        handler = new InputHandler(new String[]{"combine", prime + "", "C:\\Users\\sethr\\IdeaProjects\\Shamir\\src\\Tests\\TestShares.txt"});
        handler.driver();
    }
}