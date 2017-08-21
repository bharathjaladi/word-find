
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    final private JLabel[][] grid = new JLabel[4][4];
    private StringBuilder current = new StringBuilder();
    private WordFind game;
    private int prevX = -1;
    private int prevY = -1;
    private boolean going = false;

    public GameCourt(char[][] temp) {
        game = new WordFind(temp);
        toDo();
    }

    public void toDo() {

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new GridLayout(4, 4, -1, -1));
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        for (int u = 0; u < 4; u++) {
            for (int v = 0; v < 4; v++) {
                final int i = u;
                final int j = v;
                ImageIcon start = new ImageIcon(Character.toString(game.getValue(i, j)) + ".png");
                Image image = start.getImage();
                Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
                start = new ImageIcon(newimg);
                grid[i][j] = new JLabel(start);
                JButton button = new JButton();

                button.setName(i + "," + j);

                grid[i][j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

                button.addMouseListener(new MouseListener() {

                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (game.makeNotDefault(i, j) && going != true) {
                            {
                                current.append(game.getValue(i, j));
                                ImageIcon start = new ImageIcon(
                                        Character.toString(game.getValue(i, j)) + "2.png");
                                Image image = start.getImage();
                                Image newimg = image.getScaledInstance(120, 120,
                                        java.awt.Image.SCALE_SMOOTH);
                                start = new ImageIcon(newimg);
                                grid[i][j].setIcon(start);
                                button.setIcon(start);
                                button.repaint();
                                grid[i][j].repaint();
                                prevX = i;
                                prevY = j;
                                going = true;
                            }
                        }
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (going == true) {
                            String temp = e.getComponent().getName();
                            int i = Integer.parseInt(temp.substring(0, 1));
                            int j = Integer.parseInt(temp.substring(2));
                            if (game.makeNotDefault(i, j)) {
                                if (prevX == -1 || (Math.abs(prevX - i) <= 1
                                        && Math.abs(prevY - j) <= 1)) {
                                    current.append(game.getValue(i, j));
                                    ImageIcon start = new ImageIcon(
                                            Character.toString(game.getValue(i, j)) + "2.png");
                                    Image image = start.getImage();
                                    Image newimg = image.getScaledInstance(120, 120,
                                            java.awt.Image.SCALE_SMOOTH);
                                    start = new ImageIcon(newimg);
                                    grid[i][j].setIcon(start);
                                    button.setIcon(start);
                                    button.repaint();
                                    grid[i][j].repaint();
                                    prevX = i;
                                    prevY = j;
                                }
                            }
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }

                });
                grid[i][j].setLayout(new FlowLayout(FlowLayout.CENTER));
                button.setPreferredSize(new Dimension(60, 60));
                button.setIcon(start);
                grid[i][j].add(button);
                grid[i][j].setIcon(start);
                this.add(grid[i][j]);

            }
        }
    }

    public WordFind getGame() {
        return game;
    }

    public char[][] getBoard() {
        return game.getBoard();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public String getCurrent() {
        return current.toString();
    }

    public void clearCurrent() {
        current = new StringBuilder();
    }

    public void clearPrev() {
        prevX = -1;
        prevY = -1;
    }

    public JButton getButton(int i, int j) {
        Component[] components = grid[i][j].getComponents();
        Component component = null;
        for (int k = 0; k < components.length; k++) {
            component = components[k];
            if (component instanceof JButton) {
                return (JButton) component;
            }
        }
        return null;
    }

    public void clearColors() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon start = new ImageIcon(Character.toString(game.getValue(i, j)) + ".png");
                Image image = start.getImage();
                Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH);
                start = new ImageIcon(newimg);
                grid[i][j].setIcon(start);
                getButton(i, j).setIcon(start);
                getButton(i, j).repaint();
                grid[i][j].repaint();
                game.makeDefault(i, j);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(290, 290);
    }

    public void makeGoingFalse() {
        going = false;
    }

}
