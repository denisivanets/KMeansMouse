import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// ласс, предсавл€ющий из себ€ кластер
public class Cluster {
    //” кластера есть цвет, которым он отображаетс€. “ут этот цвет выбираетс€ случайным. ћодель RGB -
    // соответственно просто генеритс€ три случайных числа, описывающих цвет R - G - B
    private Color color = new Color(
            (int) (Math.random() * 255),
            (int) (Math.random() * 255),
            (int) (Math.random() * 255)
    );
    //координаты кластера
    private int X;
    private int Y;
    //список точек в кластере
    private List<Point> pointList;
    //просто конструктор класса
    public Cluster(int X, int Y){
        this.X = X;
        this.Y = Y;
        pointList = new ArrayList<>();
    }
    //метод добавлени€ точки в
    public void addPoint(Point point){
        pointList.add(point);
        point.setColor(color);
    }
    //геттеры и сеттеры дл€ каждого пол€ класа
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
    //метод, который сравнивает равны ли два кластера
    @Override
    public boolean equals(Object clusterForCheck){
        Cluster cluster = (Cluster) clusterForCheck;
        if( Math.abs(cluster.getX() - X) > 5 || Math.abs(cluster.getY() - Y) > 5 ) return false;
        return true;
    }
    //метод, который пересчитывает координаты центра кластера,такое требуетс€ в самом алгоритме K-means
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
    //просто метод, вывод€щий на экран координаты кластера
    public void print(){
        System.out.println("X: " + X + "Y: " + Y);
    }
}
