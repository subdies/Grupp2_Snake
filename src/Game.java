import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener, GameObserver {
    private final int boxSize = 30;
    private final int boardWidth;
    private final int boardHeight;
    private Timer timer;
    private GameState gameState;

    private ArrayList<GameObject> snake = new ArrayList<>();
    private int appleX, appleY;
    private int dx, dy;
    private JFrame frame;

    public Game(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.frame = new JFrame("Snake");
        this.gameState = new GameState();

        gameState.addObserver(this);

        GameStart();
    }

    private void GameStart() {
        setBackground(Color.black);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        // Rensar ormen ifall man kör om spelet
        gameState.resetScore();
        snake.clear();
        snake.add(GameObjectFactory.createObject("Point", boxSize * 3, boxSize * 3)); // Ormens huvud
        // Placerar ut första äpplet.
        newApple();

        gameState.setRunning(true);
        gameState.setGameOver(false);

        timer = new Timer(150, this);
        timer.start();

        dx = boxSize;
        dy = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        if (gameState.isGameOver()) {
            g.setColor(Color.red);
            g.drawString("Game Over: " + (snake.size() - 1), 100, 100);
        } else {
            g.setColor(Color.GREEN);
            g.drawString("Score: " + (snake.size() - 1), 100, 100);
        }

        if (gameState.isRunning()) {
            g.setColor(Color.yellow);
            g.fillOval(appleX, appleY, boxSize, boxSize);

            for (int i = 0; i < snake.size(); i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(new Color(45, 180, 0));
                }
                GameObject obj = snake.get(i);
                g.fillRect(obj.getX(), obj.getY(), boxSize, boxSize);
            }

            g.setColor(Color.darkGray);
            for (int i = 0; i < boardWidth / boxSize; i++) {
                g.drawLine(i * boxSize, 0, i * boxSize, boardHeight);
                g.drawLine(0, i * boxSize, boardWidth, i * boxSize);
            }
        } else {
            GameOver();
        }
    }

    private void GameOver() {
        gameState.setRunning(false);
        gameState.setGameOver(true);

        int score = snake.size() - 1;
        int response = JOptionPane.showConfirmDialog(
                frame,
                "Game over. Your score was: " + score + "\n Do you want to play again?",
                "Game over",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            GameStart();
        } else {
            System.exit(0);
        }
    }

    private void newApple() {
        Random random = new Random();
        appleX = random.nextInt(boardWidth / boxSize) * boxSize;
        appleY = random.nextInt(boardHeight / boxSize) * boxSize;
    }
    // Hantering av ormens rörelser, kollision med gränser, med sin kropp
    private void moveSnake() {
        GameObject head = snake.get(0);
        int newX = head.getX() + dx;
        int newY = head.getY() + dy;

        // Kollision med gränser
        if (newX < 0 || newY < 0 || newX >= boardWidth || newY >= boardHeight) {
            gameState.setRunning(false);
            gameState.setGameOver(true);
            timer.stop();
            return;
        }

        // Kollision med ormens kropp
        for (int i = 1; i < snake.size(); i++) {
            GameObject p = snake.get(i);
            if (p.getX() == newX && p.getY() == newY) {
                gameState.setRunning(false);
                gameState.setGameOver(true);
                timer.stop();
                return;
            }
        }

        //Skapar nytt huvud och kollar om det åt äpplet.
        snake.add(0, GameObjectFactory.createObject("Point", newX, newY));

        //Om ormen åt äpplet, placerar ut eyy nytt
        if (newX == appleX && newY == appleY) {
            gameState.updateScore(1);
            newApple();
        } else {
            //Om ormen inte åt äpplet, ta bort en del av ormen.
            snake.remove(snake.size() - 1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState.isRunning()) {
            moveSnake();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && dy == 0) {
            dx = 0;
            dy = -boxSize;
        } else if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && dy == 0) {
            dx = 0;
            dy = boxSize;
        } else if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && dx == 0) {
            dx = -boxSize;
            dy = 0;
        } else if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && dx == 0) {
            dx = boxSize;
            dy = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void onGameOver(int score) {
        System.out.println("Game Over! Score: " + score);
    }
}