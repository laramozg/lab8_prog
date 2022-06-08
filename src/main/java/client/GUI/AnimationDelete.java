package client.GUI;

import java.util.HashMap;

public class AnimationDelete implements Runnable {
    private Smiley smiley;
    private GUI gui;
    private HashMap<String, Smiley> elementsClient;
    private String arg;

    public AnimationDelete(Smiley smiley, GUI gui, HashMap<String, Smiley> elementsClient, String arg) {
        this.smiley = smiley;
        this.gui = gui;
        this.elementsClient = elementsClient;
        this.arg = arg;
    }

    /**
     * Метод создает анимацию при удалении элемента
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < 25; i++) {
                smiley.increase();
                gui.getGraphicsPanel().repaint();
                Thread.sleep(30);
            }
            while (smiley.getHad().getWidth()>0){
                smiley.decrease();
                gui.getGraphicsPanel().repaint();
                Thread.sleep(30);
            }
            elementsClient.remove(arg);
            gui.getGraphicsPanel().repaint();

        } catch (InterruptedException e) {
            // Исключение не мешает логике исполнения программы
        }
    }
}