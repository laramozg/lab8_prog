package client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GraphicsPanel extends JPanel {
    private HashMap<String, Smiley> elementsClient = new HashMap();
    private HashMap<String, Color> colors = new HashMap<>();
    private Random random = new Random();
    private GUI gui;

    public HashMap<String, Color> getColors() {
        return colors;
    }

    public HashMap<String, Smiley> getElementsClient() {
        return elementsClient;
    }

    public GraphicsPanel(GUI gui) {
        this.gui = gui;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (Map.Entry<String, Smiley> element : elementsClient.entrySet()) {
                    if (element.getValue().getMouth().contains(e.getX(), e.getY()) || element.getValue().getHad().contains(e.getX(),e.getY())
                            || element.getValue().getEye1().contains(e.getX(),e.getY())||element.getValue().getEye2().contains(e.getX(),e.getY())) {
                        gui.getResult().setText("key: " + element.getValue().getKeys() + "\n"
                                + "id: " + element.getValue().getId() + "\n"
                                + "name" + " " + element.getValue().getName() + "\n"
                                + "x " + element.getValue().getX() + "\n"
                                + "y " + element.getValue().getY() + "\n"
                                + "creationDate" + " " + element.getValue().getCreationDate() + "\n"
                                + "salary" + " " + element.getValue().getSalary() + "\n"
                                + "startDate" + " " + element.getValue().getStartDate() + "\n"
                                + "position" + " " + element.getValue().getPosition() + "\n"
                                + "status" + " " + element.getValue().getStatus() + "\n"
                                + "employeesCount" + " " + element.getValue().getEmployeesCount() + "\n"
                                + "type" + " " + element.getValue().getType() + "\n"
                                + "postalAddress" + " " + element.getValue().getPostalAddress() + "\n"
                                + "creator" + " " + element.getValue().getCreator() + "\n"
                        );
                    }
                }
            }
        });
    }

    /**
     * Метод добавляет элементы в зону визуализации
     *
     */
    public void updateElement(HashMap<String, Smiley> elementsServer) {
        try {
            for (Map.Entry<String, Smiley> elementServer : elementsServer.entrySet()) {
                if (!elementsClient.containsKey(elementServer.getKey())) {
                    elementsClient.put(elementServer.getKey(), elementServer.getValue());
                    new Thread(new AnimationAdd(elementServer.getValue(), gui)).start();
                }
            }
            for (Map.Entry<String, Smiley> elementClient : elementsClient.entrySet()) {
                if (!elementsServer.containsKey(elementClient.getKey())) {
                    new Thread(new AnimationDelete(elementClient.getValue(), gui, elementsClient, elementClient.getKey())).start();
                }
            }
            for (Map.Entry<String, Smiley> elementServer : elementsServer.entrySet()) {
                if (!elementsClient.get(elementServer.getKey()).getName().equals(elementServer.getValue().getName()) ||
                        !elementsClient.get(elementServer.getKey()).getX().equals(elementServer.getValue().getX()) ||
                        !elementsClient.get(elementServer.getKey()).getY().equals(elementServer.getValue().getY()) ||
                        !elementsClient.get(elementServer.getKey()).getKeys().equals(elementServer.getValue().getKeys()) ||
                        !elementsClient.get(elementServer.getKey()).getSalary().equals(elementServer.getValue().getSalary()) ||
                        !elementsClient.get(elementServer.getKey()).getStartDate().equals(elementServer.getValue().getStartDate()) ||
                        !elementsClient.get(elementServer.getKey()).getPosition().equals(elementServer.getValue().getPosition()) ||
                        !elementsClient.get(elementServer.getKey()).getStatus().equals(elementServer.getValue().getStatus()) ||
                        !elementsClient.get(elementServer.getKey()).getEmployeesCount().equals(elementServer.getValue().getEmployeesCount()) ||
                        !elementsClient.get(elementServer.getKey()).getType().equals(elementServer.getValue().getType()) ||
                        !elementsClient.get(elementServer.getKey()).getPostalAddress().equals(elementServer.getValue().getPostalAddress())){
                    new Thread(new AnimationAdd(elementServer.getValue(), gui)).start();
               //     new Thread(new AnimationUpdate(elementsClient.get(elementServer.getKey()), elementServer.getValue(), gui, elementsClient, elementServer.getKey())).start();
                }
                repaint();
            }
        } catch (ConcurrentModificationException e) {
            // Исключение не мешает логике исполнения программы
        }
    }

    /**
     * Метод генерирует цвет элементам
     *
     * @return
     */
    public float[] setColor() {
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new float[]{r, g, b};
    }

    /**
     * Метод рисует фигуры на фрейме
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        try {
            super.paintComponent(g);
            this.setBackground(Color.WHITE);
            Graphics2D g2 = (Graphics2D) g;
            for (Map.Entry<String, Smiley> element : elementsClient.entrySet()) {
                g2.setBackground(Color.WHITE);
                element.getValue().drawHat(g2, colors.get(element.getValue().getCreator()));
            }
        } catch (ConcurrentModificationException e) {
            // Исключение не мешает логике исполнения программы
        }
    }
}