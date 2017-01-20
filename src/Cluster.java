import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cluster {
    private Color color = new Color(
            (int) (Math.random() * 255),
            (int) (Math.random() * 255),
            (int) (Math.random() * 255)
    );
    private int X;
    private int Y;
    private List<Point> pointList;
    public Cluster(int X, int Y){
        this.X = X;
        this.Y = Y;
        pointList = new ArrayList<>();
    }

    public void addPoint(Point point){
        pointList.add(point);
        point.setColor(color);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    @Override
    public boolean equals(Object clusterForCheck){
        Cluster cluster = (Cluster) clusterForCheck;
        if( Math.abs(cluster.getX() - X) > 5 || Math.abs(cluster.getY() - Y) > 5 ) return false;
        return true;
    }

    public void recalcCenter(){
        double sumX = 0;
        double sumY = 0;
        for(Point point : pointList){
            sumX += point.getX();
            sumY += point.getY();
        }
        double avgX = 0;
        double avgY = 0;
        avgX = sumX / pointList.size();
        avgY = sumY / pointList.size();
        this.X = (int) avgX;
        this.Y = (int) avgY;
    }

    public void print(){
        System.out.println("X: " + X + "Y: " + Y);
    }
}
