import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        int boardWidth = 600;
        int boardHeight = 600;
        Game game = new Game(boardWidth, boardHeight);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }
}