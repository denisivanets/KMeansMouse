import java.util.ArrayList;
import java.util.List;

public class PointStorage {
    private static PointStorage ourInstance = new PointStorage();
    private List<Point> pointList = new ArrayList<Point>();

    public static PointStorage getInstance() {
        return ourInstance;
    }

    private PointStorage() {
    }

    public static PointStorage getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(PointStorage ourInstance) {
        PointStorage.ourInstance = ourInstance;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(List<Point> pointList) {
        this.pointList = pointList;
    }

    public void dropFlags(){
        pointList.forEach(
                point -> point.setIsAlreadyAdded(false)
        );
    }
}
