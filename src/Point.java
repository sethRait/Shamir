import java.util.Arrays;

/**
 * Created by sethr on 9/16/2016.
 */
public class Point {
    private String x;
    private String y;

    public Point() {
        this("", "");
    }

    public Point(String x, String y) {
        this.x = x;
        this.y = y;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String toString() {
        return x + " " + y;
    }

    public static Share[] toShare(Point[] points) {
        Share[] shares = new Share[points.length];
        for (int i = 0; i < points.length; i++) {
            shares[i] = new Share(points[i].getX(), points[i].getY());
        }
        return shares;
    }

    public String prettyPrint() {
        return "(" + x + ", " + y + ")";
    }

}
