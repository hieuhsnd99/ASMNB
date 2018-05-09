package asm123;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Pham Trung Hieu
 */
public class GameS extends JPanel implements Runnable {

    static int[][] ag = new int[21][21];

    static int padding = 10;
    static int WIDTH = 400;
    static int HETGHT = 400;

    static boolean isPlaying = false;
    static boolean enable = true;

    cRan ran;

    Thread thread;

    static int cLevel = 1;
    static int diem = 0;

    static boolean isGameover = false;

    public GameS() {

        ran = new cRan();

        ag[10][10] = 2;

        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        long t = 0;
        long t1 = 0;
        while (true) {

            if (System.currentTimeMillis() - t1 > 500) {
                enable = !enable;
                t1 = System.currentTimeMillis();
            }

            if (isPlaying) {
                if (System.currentTimeMillis() - t > 200) {
                    t = System.currentTimeMillis();
                }

                ran.update();
            }

            repaint();
            try {
                sleep(20);
            } catch (InterruptedException ex) {

            }
        }
    }

    public void paintAg(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, WIDTH + padding * 2 + 300, HETGHT + padding * 2);
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH + padding * 2, HETGHT + padding * 2);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (ag[i][j] == 2) {
                    g.setColor(Color.green);
                    g.fillRect(i * 20 + 1, j * 20 + 1, 18, 18);
                }
            }
        }
    }

    public void paint(Graphics g) {
        paintAg(g);
        ran.veRan(g);

        if (!isPlaying) {
            if (enable) {
                g.setColor(Color.orange);
                g.setFont(g.getFont().deriveFont(22.0f));
                g.drawString("Press space to play game", 80, 200);
            }

        }
        if (isGameover) {
            g.setColor(Color.orange);
            g.setFont(g.getFont().deriveFont(28.0f));
            g.drawString("Game Over", 130, 150);
        }
        g.setColor(Color.orange);
        g.setFont(g.getFont().deriveFont(28.0f));
        g.drawString("Level: " + cLevel, 450, 50);

        g.setColor(Color.orange);
        g.setFont(g.getFont().deriveFont(28.0f));
        g.drawString("Diem: " + diem, 600, 50);

        for (int i = 0; i < Game.users.size(); i++) {
            g.drawString(Game.users.get(i).toString(), 450, i * 50 + 120);
        }

    }
}
