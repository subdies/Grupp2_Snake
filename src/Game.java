import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel {
    private int bodySize = 5;
    private int appleX;
    private int appleY;
    private int boxSize = 30;
    private boolean action;

    // Skapar tiles "rutor" som spelet kommer spela plats i.
    private class Tiles {
        int x;
        int y;

        Tiles(int x, int y) {
            this.x= x;
            this.y= y;
        }
    }

    //Behöver lägga till funktion för rörelse, velocity?


    int boardWidth, boardHeight;

    // Lägger till huvudet på ormen som är en tile i början.
    Tiles snakeHead, snakeTail;
    Tiles food;
    Tiles snakeBody;
    Random random = new Random();

    Game(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        setBackground(Color.black);
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(boardWidth, boardHeight));


        snakeHead = new Tiles(20, 20);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

// DRAW funktion för ormens kropp.
        for (int i = 0; i < bodySize; i++) {
            if (i == 0) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.darkGray);
            }
// applens parameter
            g.setColor(Color.yellow);
            g.fillOval(appleX, appleY, boxSize, boxSize);
        }
        //Draw funktion för linjerna emellan tiles.
        if (action) {
            for (int i = 0; i < boardHeight / boxSize; i++) {
                g.drawLine(i * boxSize, 0, i * boxSize, boardHeight);
                g.drawLine(0, i * boxSize, boardWidth, i * boxSize);

            }
        }
    }
        public void newApple() {
            appleY = (int)(Math.random() * (boardWidth / boxSize)) * boxSize;
            appleX = (int)(Math.random() * (boardHeight / boxSize)) * boxSize;
        }





        // DRAW funktion för ormens huvud.
        g.setColor(Color.black);
        g.fillRect(0, 0, boardWidth, boardHeight);
    }

}
