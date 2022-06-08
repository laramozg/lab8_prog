package client.GUI;

public class AnimationAdd implements Runnable {
    private Smiley smiley;
    private GUI gui;
    public AnimationAdd(Smiley smiley, GUI gui) {
        this.smiley = smiley;
        this.gui = gui;
    }

    /**
     * Метод создает анимацию при добавлении элемента
     */
    @Override
    public void run() {
        try {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 25; i++) {
                    smiley.increase();
                    gui.getGraphicsPanel().repaint();
                    Thread.sleep(30);
                }
                for (int i = 0; i < 25; i++) {
                    smiley.decrease();
                    gui.getGraphicsPanel().repaint();
                    Thread.sleep(30);
                }
            }
        } catch (InterruptedException e) {
            // Исключение не мешает логике исполнения программы
        }
    }
}