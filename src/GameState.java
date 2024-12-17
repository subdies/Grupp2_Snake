import java.util.ArrayList;


/**
 * Observer design
 * Gäller för denna klass samt GameObserver.
 * Säger till objekten vilken state vi är i.
 */
public class GameState {
    private boolean running;
    private boolean gameOverBool;
    private int score;
    private ArrayList<GameObserver> observers = new ArrayList<>();

    public GameState() {
        running = false;
        score = 0;
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

    public void updateScore(int score){
        this.score += score;
    }

    public void resetScore(){
        this.score = 0;
    }
    public int getScore(){
        return score;
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
            observer.onGameOver(score);
        }
    }
}
