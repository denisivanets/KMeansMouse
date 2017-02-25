import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
//Класс, отвечающий за отрисовку
public class DrawingPanel extends JPanel {

    private KMeans kMeans = new KMeans();

    public DrawingPanel(){
        super();
        super.setSize(500,500);
    }
    //Метод отрисовывающий кластеры и точки на панели
    @Override
    public void paint(Graphics g) {
        kMeans.getCurrentClusterState().forEach(
                (cluster) -> {
                    g.setColor(cluster.getColor());
                    g.drawRect(cluster.getX(), cluster.getY(), 7, 7);
                }
        );

        PointStorage.getInstance().getPointList().forEach(
                (point) -> {
                    g.setColor(point.getColor());
                    g.drawOval(point.getX(), point.getY(), 5, 5);
                }
        );

    }
    //метод - удаляет определенную точку с экрана
    public void deletePoint(int x, int y){
        Point deletedPoint = findNearestPoint(x, y);
        if ( null != deletedPoint ){
            PointStorage.getInstance().getPointList().remove(deletedPoint);
        }
    }
    //метод - находил ближайшую точку с тем, куда ты кликнула, чтобы для удаления надо было кликать не именно в центр точки,а хотя бы очень близко с ней
    private Point findNearestPoint(int x, int y){
        double min = this.getWidth() + this.getHeight();
        Point point = null;
        for (Point onePoint : PointStorage.getInstance().getPointList()){
            double distance = Math.sqrt(Math.pow((onePoint.getX() - x), 2) + Math.pow((onePoint.getY() - y), 2));
            if ( distance < min ){
                min = distance;
                if ( min < 10 ){ point = onePoint; }
            }
        }
        return point;
    }

    public KMeans getkMeans() {
        return kMeans;
    }

    public void setkMeans(KMeans kMeans) {
        this.kMeans = kMeans;
    }

    public void clear(Graphics g){
        g.clearRect(0,0,this.getWidth(),this.getHeight());
    }
}

