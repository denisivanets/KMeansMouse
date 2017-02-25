import java.awt.*;
//�����, ����������� �����. �� ����������, ����
public class Point {

    private int X;
    private int Y;
    private Color color = Color.BLACK;
    //���������� �� ������ � ������ ��� ��� ���
    private boolean isAlreadyAdded = false;

    public Point(int X, int Y){
        this.X = X;
        this.Y = Y;
    }
    //������� � ������� ��� �����
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isAlreadyAdded() {
        return isAlreadyAdded;
    }

    public void setIsAlreadyAdded(boolean isAlreadyAdded) {
        this.isAlreadyAdded = isAlreadyAdded;
    }
}
