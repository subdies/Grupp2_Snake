import java.util.ArrayList;

public class GameState {
    private boolean running;
    private boolean gameOverBool;
    private ArrayList<GameObserver> observers = new ArrayList<>();

    public GameState() {
        running = false;
        gameOverBool = false;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void setRunning(boolean running) {
        this.running = running;
        if (!running) {
            notifyObservers();
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOverBool = gameOver;
    }

    public boolean isGameOver() {
        return gameOverBool;
    }

    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.onGameOver(0); // Example score value, can be passed as an argument
        }
    }
}
