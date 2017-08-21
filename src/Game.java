
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class Game implements Runnable {

    private HashSet<String> dictionary = new HashSet<String>();
    private HashSet<String> madeWords = new HashSet<String>();
    private int score = 0;
    private int prevScore;
    JFrame frame = new JFrame("Word Find");
    private int time = 90;

    @Override
    public void run() {

        Reader r = null;
        BufferedReader r1 = null;
        try {
            r = new FileReader("dictionary.txt");
            r1 = new BufferedReader(r);
            while (r1.ready()) {
                String line = r1.readLine();
                dictionary.add(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                r.close();
                r1.close();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        frame.setResizable(false);
        doDis();

    }

    public void doDis() {
        time = 90;
        score = 0;
        madeWords = new HashSet<String>();
        Reader r3 = null;
        BufferedReader r2 = null;
        try {

            r3 = new FileReader("saved.txt");
            r2 = new BufferedReader(r3);
            while (r2.ready()) {
                prevScore = Integer.parseInt(r2.readLine());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                r2.close();
                r3.close();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        char[][] random = new char[4][4];
        boolean keepGoing = true;
        while (keepGoing) {
            for (int i = 0; i < 16; i++) {
                int temp = (int) (Math.random() * 26) + 65;
                if (temp == 81 || temp == 74 || temp == 90 || temp == 88 || temp == 86
                        || temp == 75 || temp != 65 || temp != 69 || temp != 73 || temp != 79
                        || temp != 85 || temp != 89) {
                    temp = (int) (Math.random() * 26) + 65;
                }
                if (temp == 81 || temp == 74 || temp == 90 || temp == 88 || temp == 86
                        || temp == 75 || temp != 65 || temp != 69 || temp != 73 || temp != 79
                        || temp != 85 || temp != 89) {
                    temp = (int) (Math.random() * 26) + 65;
                }
                random[i / 4][i % 4] = (char) temp;
            }
            keepGoing = false;
            if (!isVowel(random[0][0]) && !isVowel(random[0][1]) && !isVowel(random[1][0])
                    && !isVowel(random[1][1])) {
                keepGoing = true;
            }
            if (!isVowel(random[2][0]) && !isVowel(random[2][1]) && !isVowel(random[3][0])
                    && !isVowel(random[3][1])) {
                keepGoing = true;
            }
            if (!isVowel(random[0][2]) && !isVowel(random[0][3]) && !isVowel(random[1][2])
                    && !isVowel(random[1][3])) {
                keepGoing = true;
            }
            if (!isVowel(random[2][2]) && !isVowel(random[2][3]) && !isVowel(random[3][2])
                    && !isVowel(random[3][3])) {
                keepGoing = true;
            }
        }

        frame.dispose();
        frame = new JFrame("Word Find");
        frame.setResizable(false);
        frame.setBackground(Color.WHITE);
        GameCourt court = new GameCourt(random);

        frame.add(court, BorderLayout.CENTER);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.NORTH);
        status_panel.setLayout(new GridLayout(1, 2));

        final JTextPane scorePane = new JTextPane();
        scorePane.setText(" Score: " + score);
        scorePane.setFont(new Font("Calibri", Font.PLAIN, 20));
        scorePane.setOpaque(true);
        scorePane.setEditable(false);
        status_panel.add(scorePane);

        final JTextPane hscorePane = new JTextPane();
        hscorePane.setText("High: " + prevScore);
        hscorePane.setFont(new Font("Calibri", Font.PLAIN, 20));
        hscorePane.setOpaque(true);
        hscorePane.setEditable(false);
        status_panel.add(hscorePane);
        status_panel.setBackground(Color.WHITE);

        final JPanel bstatus_panel = new JPanel();
        frame.add(bstatus_panel, BorderLayout.SOUTH);
        bstatus_panel.setLayout(new GridLayout(1, 4));
        bstatus_panel.setLayout(new GridBagLayout());
        bstatus_panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();

        final JTextPane timePane = new JTextPane();
        timePane.setText(" 1:30");
        timePane.setFont(new Font("Calibri", Font.PLAIN, 20));
        timePane.setOpaque(true);
        timePane.setEditable(false);
        bstatus_panel.add(timePane);

        bstatus_panel.add(Box.createRigidArea(new Dimension(5, 0)), gbc);

        final JButton help = new JButton();
        help.setText("Help");
        help.setFont(new Font("Calibri", Font.PLAIN, 18));
        help.setOpaque(true);
        help.setBackground(Color.WHITE);
        bstatus_panel.add(help, gbc);
        help.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "You don't need help... JK dis is coming soon!", "Help",
                        JOptionPane.INFORMATION_MESSAGE);

            }

        });

        final JButton reset = new JButton();
        reset.setText("New Game");
        reset.setFont(new Font("Calibri", Font.PLAIN, 18));
        reset.setOpaque(true);
        reset.setBackground(Color.WHITE);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                court.getButton(i, j).addKeyListener(new KeyListener() {

                    @Override
                    public void keyTyped(KeyEvent e) {

                        if (!madeWords.contains(court.getCurrent().toLowerCase())
                                && dictionary.contains(court.getCurrent().toLowerCase())) {
                            score += (Math.pow((Math.max(0, court.getCurrent().length() - 2)), 2)
                                    * 100);
                            scorePane.setText(" Score: " + score);
                            madeWords.add(court.getCurrent().toLowerCase());
                        }

                        court.clearCurrent();
                        court.clearColors();
                        court.clearPrev();

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }

                });

                court.getButton(i, j).addMouseListener(new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (!madeWords.contains(court.getCurrent().toLowerCase())
                                && dictionary.contains(court.getCurrent().toLowerCase())) {
                            score += (Math.pow((Math.max(0, court.getCurrent().length() - 2)), 2)
                                    * 100);
                            scorePane.setText(" Score: " + score);
                            madeWords.add(court.getCurrent().toLowerCase());
                        }
                        court.makeGoingFalse();
                        court.clearCurrent();
                        court.clearColors();
                        court.clearPrev();

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }

        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {

            @Override
            public void run() {
                time--;
                if (time < 0) {
                    timePane.setText(" 0:00");
                }
                if (time < 10) {
                    timePane.setText(" 0:0" + time);
                }
                else
                    if (time < 60) {
                        timePane.setText(" 0:" + time);
                    }
                    else
                        if (time < 70) {
                            timePane.setText(" 1:0" + (time - 60));
                        }
                        else {
                            timePane.setText(" 1:" + (time - 60));
                        }
            }

        }, 2000, 1000);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                timer2.cancel();
                timer2.purge();
                if (score > prevScore) {
                    try {
                        save();
                    }
                    catch (Exception e) {

                    }
                }
                JFrame frame = new JFrame();
                String message = "Time's up! Your score was " + score + " and your high score is "
                        + Math.max(score, prevScore) + ". Would you like to play again?";
                int answer = JOptionPane.showConfirmDialog(frame, message, "Game Over!",
                        JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    timer.cancel();
                    timer.purge();
                    doDis();
                }
                else
                    if (answer == JOptionPane.NO_OPTION) {
                        System.exit(0);
                    }
            }

        }, 92 * 1000);
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timer2.cancel();
                timer2.purge();
                timer.cancel();
                timer.purge();
                doDis();

            }

        });

        bstatus_panel.add(reset, gbc);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getWidth() / 2,
                dim.height / 2 - frame.getHeight() / 2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void save() throws IOException {
        BufferedWriter w = new BufferedWriter(new FileWriter("saved.txt"));
        w.write(Integer.toString(score));
        w.close();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }

    public boolean isVowel(char c) {
        // return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c
        // == 'y';
        return "AEIOUY".indexOf(c) >= 0;
    }
}
