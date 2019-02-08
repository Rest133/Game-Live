import java.awt.*;

public class Config {
    public static final int SIZE = 10;
    public static final int WIDTH = 180;
    public static final int HEIGHT = 100;
    public static final int SLEEPMS = 10;


    public static Color getColor(Status status) {
        switch (status) {
            default:
            case NONE:
                return Color.gray;
            case BORN:
                return Color.GREEN;
            case LIVE:
                return Color.ORANGE;
            case DIED:
                return Color.darkGray;
        }
    }

}
