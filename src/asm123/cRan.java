package asm123;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import static java.lang.System.in;
import java.util.Random;
import static javax.management.Query.in;
import javax.swing.JOptionPane;

/**
 *
 * @author Pham Trung Hieu
 */
public class cRan {

    int doDai = 3;
    int[] x;
    int[] y;

    public static int GO_UP = 0;
    public static int GO_DOWN = 1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = 3;

    int vt = cRan.GO_DOWN;

    long time = 0;

    int speed = 250;

    int maxL = 8;

    boolean upd = true;

    public cRan() {
        x = new int[100];
        y = new int[100];

        x[0] = 5;
        y[0] = 4;

        x[1] = 5;
        y[1] = 3;

        x[2] = 5;
        y[2] = 2;
    }

    public void resetG() {
        x = new int[100];
        y = new int[100];

        x[0] = 5;
        y[0] = 4;

        x[1] = 5;
        y[1] = 3;

        x[2] = 5;
        y[2] = 2;

        doDai = 3;
        vt = cRan.GO_DOWN;
    }

    public void setvt(int v) {
        if (vt != -v && upd) {
            vt = v;
            upd = false;
        }
    }

    public boolean tDr(int x1, int y1) {
        for (int i = 0; i < doDai; i++) {
            if (x[i] == x1 && y[i] == y1) {
                return true;
            }
        }
        return false;
    }

    public Point tD() {
        Random r = new Random();
        int x;
        int y;
        do {
            x = r.nextInt(19);
            y = r.nextInt(19);
        } while (tDr(x, y));

        return new Point(x, y);
    }

    public int getCSpeed() {
        int speed = 250;
        for (int i = 0; i < GameS.cLevel; i++) {
            speed *= 0.8;
        }
        return speed;
    }

    public void update() {

        if (doDai == maxL) {
            GameS.isPlaying = false;
            resetG();
            GameS.cLevel++;
            maxL += 2;
            speed = getCSpeed();
        }

        for (int i = 1; i < doDai; i++) {
            if (x[0] == x[i] && y[0] == y[i]) {

                String name = JOptionPane.showInputDialog("moi ban nhap ten : ");
                Game.users.add(new user(name, String.valueOf(GameS.diem)));

                GameS.isPlaying = false;
                GameS.isGameover = true;

                GameS.diem = 0;
                GameS.cLevel = 1;

            }
        }

        if (System.currentTimeMillis() - time > speed) {

            upd = true;

            if (GameS.ag[x[0]][y[0]] == 2) {
                doDai++;
                GameS.ag[x[0]][y[0]] = 0;
                GameS.ag[tD().x][tD().y] = 2;

                GameS.diem += 1;
            }

            for (int i = doDai - 1; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            if (vt == cRan.GO_UP) {
                y[0]--;
            }
            if (vt == cRan.GO_DOWN) {
                y[0]++;
            }
            if (vt == cRan.GO_LEFT) {
                x[0]--;
            }
            if (vt == cRan.GO_RIGHT) {
                x[0]++;
            }

            if (x[0] < 0) {
                x[0] = 20;
            }
            if (x[0] > 20) {
                x[0] = 0;
            }
            if (y[0] < 0) {
                y[0] = 20;
            }
            if (y[0] > 20) {
                y[0] = 0;
            }

            time = System.currentTimeMillis();
        }

    }

    public void veRan(Graphics g) {
        g.setColor(Color.red);
        for (int i = 0; i < doDai; i++) {
            g.fillRect(x[i] * 20 + 1, y[i] * 20 + 1, 18, 18);
        }
    }
}
