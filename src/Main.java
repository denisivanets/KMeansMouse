import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
// ласс, с которого запускаетс€ само приложение
public class Main {
    private JFrame frame;
    private DrawingPanel pointsPanel = new DrawingPanel();

    public static void main(String[] args) {
        new Main().start();
    }

    private void start(){
        //“ут создаетс€ окно и сет€тс€ его свойства(длина,ширина и т.д)
        //Ќу и расположение панели отрисовки, кнопки, и т.д
        frame = new JFrame("Points and mouse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.getContentPane().add(pointsPanel);
        Box box = new Box(BoxLayout.Y_AXIS);
        JButton clusterButton = new JButton("Cluster" );
        box.add(clusterButton);
        JLabel clustersLabel = new JLabel("Amount");
        JTextField amountField = new JTextField("1",3);
        amountField.setSize(20,10);
        box.add(clustersLabel);
        box.add(amountField);
        //Ёто дл€ кнопки добавл€етс€ слушатель, который будет срабатывать, когда на кнопку нажимают
        clusterButton.addActionListener(
                (event) -> {
                    final String fieldText = amountField.getText();
                    final int amountOfClusters = Integer.parseInt(fieldText);
                    if (PointStorage.getInstance().getPointList().size() == 0 || amountOfClusters <= 0) {
                    } else {
                        pointsPanel.setkMeans(new KMeans());
                        pointsPanel.getkMeans().setK(amountOfClusters);
                        pointsPanel.getkMeans().runAlgorithm();
                        pointsPanel.update(pointsPanel.getGraphics());
                        pointsPanel.setVisible(false);
                        pointsPanel.setVisible(true);
                    }
                }
        );
        //ƒобавл€етс€ аналогичный слушатель, только дл€ мышки
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Ёто происходит, когда нажимают на левую
                if (e.getButton() == MouseEvent.BUTTON1){
                    PointStorage.getInstance().getPointList().add(new Point(e.getX(), e.getY()));
                    pointsPanel.update(pointsPanel.getGraphics());
                }
                //ј это если на правую кнопку мыши
                else if(e.getButton() == MouseEvent.BUTTON3){
                    pointsPanel.deletePoint( e.getX(), e.getY());
                    frame.update(frame.getGraphics());
                }
            }
        };
        pointsPanel.addMouseListener(listener);
        frame.getContentPane().add(BorderLayout.EAST,box);
        frame.setVisible(true);
    }
}
