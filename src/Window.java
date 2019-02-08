import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window implements Runnable {
    JFrame jFrame;
    Box[][] boxes;

    void initFrame() {
        jFrame = new JFrame();
        jFrame.getContentPane().setLayout(null);
        jFrame.setSize(Config.WIDTH * Config.SIZE + 30, Config.HEIGHT * Config.SIZE + 50);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setTitle("Life Game");
    }

    void initBoxes() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                jFrame.add(boxes[x][y]);
            }
        }
//-------------------------------------------------------Добавление соседей для клетки
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                for (int sx = -1; sx <= +1; sx++) {
                    for (int sy = -1; sy <= +1; sy++) {
                        if (!(sx == 0 && sy == 0)) boxes[x][y].cell.addNear(boxes
                                [(x + sx + Config.WIDTH) % Config.WIDTH]
                                [(y + sy + Config.HEIGHT) % Config.HEIGHT].cell);
                    }
                }
            }
        }
        //------------------------------------------------
        for (int x = 10; x < 35; x++) {
            boxes[x][10].cell.status = Status.LIVE;
            boxes[x][10].setColor();
        }
    }

    @Override
    public void run() {
        initFrame();
        initBoxes();
        initTimer();
    }

    private void initTimer() {
        TimerListener t1 = new TimerListener();
        Timer timer = new Timer(Config.SLEEPMS, t1);
        timer.start();

    }

    private class TimerListener implements ActionListener {
        boolean flop = false;

        @Override
        public void actionPerformed(ActionEvent e) {
            flop = !flop;
            for (int x = 0; x < Config.WIDTH; x++) {
                for (int y = 0; y < Config.HEIGHT; y++) {
                    if (flop) {
                        boxes[x][y].step1();
                    } else
                        boxes[x][y].step2();
                }
            }
        }
    }
}
