package coursera.xujinqi.cousera1.week1;

/**
 * @author 许 劲淇
 * @date 2022/1/20 10:38
 */
public class TotalPerim {

    public static void main(String[] args) {
        Point[] points = new Point[3];
        points[0] = new Point(-3, 4);
        points[1] = new Point(-3, -4);
        points[2] = new Point(3, -4);
        System.out.println(getTotalPerim(points));
    }

    public static int getTotalPerim(Point[] points) {
        int totalP = 0;
        Point prevPt = points[points.length - 1];
        Point curPt = null;
        for (Point point : points) {
            curPt = point;
            totalP += Math
                    .sqrt(Math.pow(curPt.yRadios - prevPt.yRadios, 2) + Math.pow(curPt.xRadios - prevPt.xRadios, 2));
            prevPt = point;
        }
        return totalP;
    }
}

class Point {
    public int xRadios;
    public int yRadios;

    public Point(int xRadios, int yRadios) {
        this.xRadios = xRadios;
        this.yRadios = yRadios;
    }
}