import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        int boardWidth = 600;
        int boardHeight = 600;

        JFrame frame = new JFrame("SnakeGame");
        frame.setSize(boardWidth, boardHeight);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Game snakeGame = new Game(boardWidth, boardHeight);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocusInWindow();

    }
}
