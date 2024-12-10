import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    private final int boxSize = 30;
    private final int boardWidth;
    private final int boardHeight;
    private final Timer timer;

    private int appleX, appleY;
    private boolean running = true;

    private final ArrayList<Point> snake = new ArrayList<>();
    private int dx = boxSize, dy = 0; // Initial riktning (höger).

    Random random = new Random();

    Game(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setBackground(Color.black);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        // Initiera orm och äpple
        snake.add(new Point(boxSize * 3, boxSize * 3)); // Ormens huvud
        newApple();

        // Timer för spelloopen
        timer = new Timer(150, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {
            // Rita äpple
            g.setColor(Color.yellow);
            g.fillOval(appleX, appleY, boxSize, boxSize);

            // Rita orm
            for (int i = 0; i < snake.size(); i++) {
                if (i == 0) {
                    g.setColor(Color.green); // Ormens huvud
                } else {
                    g.setColor(new Color(45, 180, 0)); // Ormens kropp
                }
                Point p = snake.get(i);
                g.fillRect(p.x, p.y, boxSize, boxSize);
            }

            // Rita grid (valfritt)
            g.setColor(Color.darkGray);
            for (int i = 0; i < boardWidth / boxSize; i++) {
                g.drawLine(i * boxSize, 0, i * boxSize, boardHeight);
                g.drawLine(0, i * boxSize, boardWidth, i * boxSize);
            }
        } else {
            showGameOver(g);
        }
    }

    private void showGameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String message = "Game Over";
        int textWidth = g.getFontMetrics().stringWidth(message);
        g.drawString(message, (boardWidth - textWidth) / 2, boardHeight / 2);
    }

    private void newApple() {
        appleX = random.nextInt(boardWidth / boxSize) * boxSize;
        appleY = random.nextInt(boardHeight / boxSize) * boxSize;
    }

    private void moveSnake() {
        // Beräkna nytt huvud
        Point head = snake.getFirst();
        int newX = head.x + dx;
        int newY = head.y + dy;

        // Kontrollera kollision med väggar eller sig själv
        if (newX < 0 || newY < 0 || newX >= boardWidth || newY >= boardHeight || snake.contains(new Point(newX, newY))) {
            running = false;
            timer.stop();
            return;
        }

        // Lägg till nytt huvud
        snake.addFirst(new Point(newX, newY));

        // Kontrollera om ormen äter ett äpple
        if (newX == appleX && newY == appleY) {
            newApple(); // Skapa nytt äpple
        } else {
            snake.removeLast(); // Ta bort sista delen av kroppen
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            moveSnake();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //System.out.println("key pressed" + key);
        if (key == KeyEvent.VK_UP && dy == 0) {
            dx = 0;
            dy = -boxSize;
        } else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && dy == 0) {
            dx = 0;
            dy = boxSize;
        } else if (key == KeyEvent.VK_LEFT && dx == 0) {
            dx = -boxSize;
            dy = 0;
        } else if (key == KeyEvent.VK_RIGHT && dx == 0) {
            dx = boxSize;
            dy = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
