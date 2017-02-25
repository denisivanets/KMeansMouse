import java.util.ArrayList;
import java.util.List;
//Класс - хранилище всех точек. По паттерну singleton
public class PointStorage {
    private static PointStorage ourInstance = new PointStorage();
    private List<Point> pointList = new ArrayList<Point>();
    //Этот метод и создаает объект хранилища
    public static PointStorage getInstance() {
        return ourInstance;
    }
    //По этому шаблону - конструктор приватный и напрямую создать объект нельзя
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
    //Сбрасывыает для всех точек флаг, определяющий добавленна ли точка в кластер или нет
    public void dropFlags(){
        pointList.forEach(
                point -> point.setIsAlreadyAdded(false)
        );
    }
}
