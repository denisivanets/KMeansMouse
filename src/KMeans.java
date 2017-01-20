import java.util.*;

public class KMeans {

    private int K = 1;
    private List<Cluster> currentClusterState = new ArrayList<>();
    private List<Cluster> previousClusterState = new ArrayList<>();
    private boolean isClustersCreated = false;


    public void runAlgorithm() {
        if (isClustersCreated == true) return;
        PointStorage.getInstance().dropFlags();
        Point randomPoint = getRandomPoint();
        Cluster cluster = new Cluster(
                randomPoint.getX(), randomPoint.getY()
        );
        if (K < PointStorage.getInstance().getPointList().size()) {
            currentClusterState.add(cluster);
            makeClusters();
        } else {
            PointStorage.getInstance().getPointList().forEach(
                    (point) ->  currentClusterState.add(new Cluster(point.getX(),point.getY()))
            );
        }
        copyLists();
        makeDependecies();
        changeCenters();
        makeIters();
    }

    private void makeIters() {
        while (isStateChanged() == true) {
            copyLists();
            changeCenters();
            makeDependecies();
            changeCenters();
        }
        isClustersCreated = true;
    }

    private double countDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    private boolean isStateChanged() {
        if(currentClusterState.size() != previousClusterState.size()) return true;
        for (int i = 0; i < currentClusterState.size(); i++) {
            if (!previousClusterState.get(i).equals(currentClusterState.get(i))) return true;
        }
        return false;
    }

    public int getK() {
        return K;
    }

    public void setK(int k) {
        K = k;
    }

    public List<Cluster> getCurrentClusterState() {
        return currentClusterState;
    }

    public List<Cluster> getPreviousClusterState() {
        return previousClusterState;
    }

    public void changeCenters() {
        currentClusterState.forEach(
                (cluster) -> cluster.recalcCenter()
        );
    }

    public void makeDependecies() {
        PointStorage.getInstance().dropFlags();
        for (Point point : PointStorage.getInstance().getPointList()) {
            double min = countDistance(point.getX(), point.getY(), currentClusterState.get(0).getX(), currentClusterState.get(0).getY());
            Cluster targetCluster = currentClusterState.get(0);
            for (Cluster cluster : currentClusterState) {
                double distance = countDistance(point.getX(), point.getY(), cluster.getX(), cluster.getY());
                if (distance < min) {
                    min = distance;
                    targetCluster = cluster;
                }
            }
            point.setColor(targetCluster.getColor());
            if (!point.isAlreadyAdded()) {
                targetCluster.addPoint(point);
                point.setIsAlreadyAdded(true);
            }
        }
    }

    private void copyLists() {
        for(Cluster cluster : currentClusterState){
            previousClusterState.add(cluster);
        }
    }

    private void makeClusters() {
        for (int i = 1; i < K; i++) {
            double sunSqrDists = 0;
            double RND = 0;
            for (Point point : PointStorage.getInstance().getPointList()) {
                double min = countMinDistance(point);
                sunSqrDists += (min * min);
            }
            RND = sunSqrDists * Math.random();
            Point pointForNewCluster = findPoint(RND);
            Cluster cluster = new Cluster(pointForNewCluster.getX(), pointForNewCluster.getY());
            currentClusterState.add(i,cluster);
        }
    }

    public int countMinDistance(Point point) {
        double min = countDistance(point.getX(), point.getY(), currentClusterState.get(0).getX(), currentClusterState.get(0).getY());
        for (Cluster cluster : currentClusterState) {
            double distance = countDistance(point.getX(), point.getY(), cluster.getX(), cluster.getY());
            if (distance < min) {
                min = distance;
            }
        }
        return (int) min;
    }

    public Point findPoint(double rnd){
        Point point =  getRandomPoint();
        double sum = 0;
        for (Point onePoint : PointStorage.getInstance().getPointList()){
            if(sum > rnd){
                point = onePoint;
                break;
            }
            double min = countMinDistance(onePoint);
            sum += (min * min);
        }
        return point;
    }

    private boolean checkExistCluster(Point point){
        for(Cluster cluster : currentClusterState){
            if(point.getX() == cluster.getX() && point.getY() == cluster.getY()) return true;
        }
        return false;
    }

    private Point getRandomPoint(){
        Point rndPoint = PointStorage.getInstance().getPointList().get((int) (Math.random() * PointStorage.getInstance().getPointList().size()));
        return rndPoint;
    }

    public boolean isClustersCreated() {
        return isClustersCreated;
    }

    public void setIsClustersCreated(boolean isClustersCreated) {
        this.isClustersCreated = isClustersCreated;
    }

}
