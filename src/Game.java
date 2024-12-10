import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel {
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


        //Draw funktion för äpplen.


        //Draw funktion för linjerna emellan tiles.



        // DRAW funktion för ormens huvud.
        g.setColor(Color.black);
        g.fillRect(0, 0, boardWidth, boardHeight);
    }

}
