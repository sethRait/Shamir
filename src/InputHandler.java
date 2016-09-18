import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by sethr on 9/18/2016.
 */
public class InputHandler {
    private List<String> arguments;
    private Map<String, Runnable> func;
    private Scanner console;
    private Point[] shares;
    public int prime;

    public InputHandler(String[] arguments) {
        this.arguments = new ArrayList<>(Arrays.asList(arguments));
        func = new HashMap<>();
        func.put("help", this::help);
        func.put("create", this::createWrapper);
        func.put("combine", this::combine);
        console = new Scanner(System.in);
    }

    private void combine() {
        List<List<Integer>> shareList = new ArrayList<>();
        int prime;
        try {
            prime = Integer.parseInt(arguments.get(1));
            console = new Scanner(new File(arguments.get(2)));
        } catch (NumberFormatException e) {
            System.out.println("Error.  Invalid syntax for 'combine'");
            func.get("help");
            return;
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }
        getListOfShares(console, shareList);
        List<Point[]> points = createPoints(shareList);
        String secret;
        byte[] bytes = new byte[points.size()];
        for (int i = 0; i < points.size(); i++) {
            bytes[i] = SecretFind.combine(Point.toShare(points.get(i)), BigInteger.valueOf(prime));
        }
        try {
            secret = new String(bytes, "US-ASCII");
            System.out.println(secret);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private List<Point[]> createPoints(List<List<Integer>> shareList) {
        List<Point[]> points = new LinkedList<>();//[shareList.get(0).size()];
        Point[] row = new Point[shareList.size()];
        int j;
        int pointCount = 0;
        for (int i = 1; i < shareList.get(0).size() - 1; i++) {
            j = 0;
            for (; j < shareList.size(); j++) {
                Point p = new Point(shareList.get(j).get(0) + "", shareList.get(j).get(i) + "");
                row[j] = p;
            }
            points.add(row);
            pointCount++;
        }
        return points;
    }

    private void getListOfShares(Scanner console, List<List<Integer>> shareList){
        Scanner lineScan;
        List<Integer> row;
        while (console.hasNextLine()) {
            row = new ArrayList<>();
            String line = console.nextLine();
            lineScan = new Scanner(line);
            while (lineScan.hasNextInt()) {
                row.add(lineScan.nextInt());
            }
            shareList.add(row);
        }
    }

    private void createWrapper() {
        try {
            create();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void create() throws UnsupportedEncodingException {
        String secret = readToString(arguments.get(1));
        if (secret == null) {
            func.get("help");
        }
        int threshold, numShares;
        try {
            threshold = Integer.parseInt(arguments.get(2));
            numShares = Integer.parseInt(arguments.get(3));
        }
        catch (NumberFormatException e) {
            System.out.println("Error.  Invalid syntax for 'create'");
            func.get("help");
            return;
        }
        StringShare stringShare = new StringShare(secret, threshold, numShares);
        this.prime = stringShare.getPrime();
        shares = stringShare.getShares();
    }

    private String readToString(String path) {
        String fileContents;
        try {
            fileContents = new Scanner(new File(path)).useDelimiter("\\Z").next();
            return fileContents;
        }
        catch(FileNotFoundException e) {
            System.out.println("File not found.");
            return null;
        }
    }
    public void driver() {
        Runnable cmd;
        String commandPrefix;
        if (arguments.size() <= 1) {
            func.get("help").run();
        } else {
            commandPrefix = arguments.get(0);
            cmd = func.get(commandPrefix);
            if (cmd != null) {
                cmd.run();
            } else {
                System.out.println("Error.  Command " + commandPrefix + " is not valid");
                func.get("help").run();
            }
        }
    }
    private void help() {
        System.out.println("The following commands are available:\n" +
                "help --show this menu\n" +
                "create <path to file containing secret> <threshold> <number of shares> " +
                "-- create new shares from a secret\n" +
                "combine <prime> <path to file containing shares>  -- combine shares into a secret");
    }

    public Point[] getShares() {
        return shares;
    }

}
