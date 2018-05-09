package asm123;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Pham Trung Hieu
 */
public class Game extends JFrame {

    GameS gameF;

    public static ArrayList<user> users;

    public Game() {
        setSize(750, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        users = new ArrayList<user>();
        ReadData();

        gameF = new GameS();
        add(gameF);

        this.addKeyListener(new hL());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UpdateData();
            }

        });

        setVisible(true);

    }

    public static void main(String[] args) {
        Game f = new Game();
    }

    private class hL implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                GameS.isPlaying = !GameS.isPlaying;
                if (GameS.isGameover) {
                    GameS.isGameover = false;
                    gameF.ran.resetG();
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gameF.ran.setvt(cRan.GO_UP);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gameF.ran.setvt(cRan.GO_DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gameF.ran.setvt(cRan.GO_LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gameF.ran.setvt(cRan.GO_RIGHT);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {       }

    }

    public static void UpdateData() {

        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            bw = new BufferedWriter(fw);

            for (user u : users) {
                bw.write(u.getName() + " " + u.getDiem());
                bw.newLine();
            }

        } catch (IOException ex) {
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
            }
        }
    }

    public static void ReadData() {
        try {
            FileReader fr = new FileReader("data/data.txt");
            BufferedReader br = new BufferedReader(fr);

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(" ");
                users.add(new user(str[0], str[1]));
            }

            br.close();
        } catch (IOException ex) {
        }
    }

}
