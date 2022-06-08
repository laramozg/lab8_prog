package client.GUI;

import java.awt.*;
import java.awt.geom.*;

/**
 * Класс отражающий объекты в зоне визуализации
 */
public class Smiley {
    private String key;
    private String id;
    private String name;
    private String x;
    private String y;
    private String creationDate;
    private String salary;
    private String startDate;
    private String position;
    private String status;
    private String employeesCount;
    private String type;
    private String postalAddress;
    private String creator;
    private Head had;
    private Mouth mouth;
    private Eyes eye1;
    private Eyes eye2;
    int hatX;
    int hatY;
    int size;
    int countE;

    public Smiley(String key, String id, String name, String x, String y, String creationDate, String salary, String startDate,
                  String position, String status, String employeesCount, String type, String postalAddress,
                  String creator) {
        this.key = key;
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.creationDate = creationDate;
        this.salary = salary;
        this.startDate = startDate;
        this.position = position;
        this.status = status;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
        this.creator = creator;
        countE = Integer.parseInt(employeesCount)%100;
        hatX = (int)Double.parseDouble(x)*100+countE;
        if(hatX>800){
            hatX=1000-99*countE%10;
        }
        hatY = (int)Double.parseDouble(y)*39+countE;
        if(hatY>450 ){
            hatY=500-47*countE%10;
        }
        try {
            size = Integer.parseInt(employeesCount)+50;
            if (size> 500){
                size=150;
            }
        }catch (NumberFormatException e){
            size = 50;
            // значения по умолчанию
        }
        had = new Head(new Point(hatX, hatY), size, size);
        mouth = new Mouth(new Point(hatX+size/3, hatY+size*3/5), size*0.3, size*0.3, 170, 200);
        eye1 = new Eyes(new Point(hatX+size/5, hatY+size/4), size/6, size/3);
        eye2 = new Eyes(new Point(hatX+size*3/5, hatY+size/4), size/6, size/3);

    }

    /**
     * Метод рисует элементы
     *
     * @param g2
     * @param color
     */
    public void drawHat(Graphics2D g2, Color color) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.draw(had);
        g2.fill(had);

        g2.setColor(Color.BLACK);
        g2.draw(eye1);
        g2.fill(eye1);

        g2.setColor(Color.BLACK);
        g2.draw(eye2);
        g2.fill(eye2);

        g2.setColor(Color.red);
        g2.fill(mouth);
        g2.draw(mouth);

    }

    public String getId() {
        return id;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public String getKeys() {
        return key;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getSalary() {
        return salary;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }

    public String getEmployeesCount() {
        return employeesCount;
    }

    public String getType() {
        return type;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public String getCreator() {
        return creator;
    }

    public Eyes getEye1() {
        return eye1;
    }

    public Eyes getEye2() {
        return eye2;
    }

    public Head getHad() {
        return had;
    }

    public Mouth getMouth() {
        return mouth;
    }



    /**
     * Метод увеличивает размер объекта
     */
    public void increase() {
        eye1.increase();
        eye2.increase();
        mouth.increase();
        had.increase();

    }

    /**
     * Метод уменьшает размер объекта
     */
    public void decrease() {
        eye1.decrease();
        eye2.decrease();
        mouth.decrease();
        had.decrease();
    }

    /**
     * Метод поднимает шляпу
     */


    /**
     * Метод опускает шляпу
     */
    public void hatDown() {

    }

    class Head extends Ellipse2D{
        private Point point;
        private int w;
        private int h;

        public Head(Point point, int w, int h) {
            this.point = point;
            this.w = w;
            this.h = h;
        }

        public double getX() {
            return point.x;
        }

        public double getY() {
            return point.y;
        }

        public double getWidth() {
            return w;
        }

        public double getHeight() {
            return h;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void setFrame(double x, double y, double w, double h) {

        }


        public void increase() {
            w++;
            h++;
        }

        public void decrease() {
            w--;
            h--;
        }


        @Override
        public Rectangle2D getBounds2D() {
            return null;
        }
    }

    class Eyes extends Ellipse2D {
        private Point point;
        private int w;
        private int h;

        public Eyes(Point point, int w, int h) {
            this.point = point;
            this.w = w;
            this.h = h;
        }

        public double getX() {
            return point.x;
        }

        public double getY() {
            return point.y;
        }

        public double getWidth() {
            return w;
        }

        public double getHeight() {
            return h;
        }



        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void setFrame(double x, double y, double w, double h) {

        }

        public void increase() {
            w++;
            h++;
        }

        public void decrease() {
            w--;
            h--;
        }

        public void whirl(double deg) {
            AffineTransform at =
                    AffineTransform.getTranslateInstance(w * 0.5, h * 0.5);
            at.rotate(Math.toRadians(deg));
            at.createTransformedShape(eye1);
            at.createTransformedShape(eye2);

        }

        @Override
        public Rectangle2D getBounds2D() {
            return null;
        }
    }
    class Mouth extends Arc2D {
        private Point point;
        private double w;
        private double h;
        private double start;
        private double extent;

        public Mouth(Point point, double w, double h, double start, double extent) {
            super(Arc2D.CHORD);
            this.point = point;
            this.w = w;
            this.h = h;
            this.start = start;
            this.extent = extent;
        }

        @Override
        public double getAngleStart() {
            return start;
        }

        @Override
        public double getAngleExtent() {
            return extent;
        }

        @Override
        public void setArc(double x, double y, double w, double h, double angSt, double angExt, int closure) {
            point.setLocation(x, y);
            this.w = w;
            this.h = h;
            this.start = angSt;
            this.extent = angExt;
        }

        @Override
        public void setAngleStart(double angSt) {
            this.start = angSt;
        }

        @Override
        public void setAngleExtent(double angExt) {
            this.extent = angExt;
        }

        @Override
        protected Rectangle2D makeBounds(double x, double y, double w, double h) {
            return null;
        }

        @Override
        public double getX() {
            return point.x;
        }

        @Override
        public double getY() {
            return point.y;
        }

        @Override
        public double getWidth() {
            return w;
        }

        @Override
        public double getHeight() {
            return h;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        public void increase() {
            w++;
            h++;
        }

        public void decrease() {
            w--;
            h--;
        }
    }

}