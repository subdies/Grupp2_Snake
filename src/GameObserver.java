//Observerar förändringar i spelets state
//Använder observer design mönster för att meddela om spelhändelser.
public interface GameObserver {
    void onGameOver(int score);
}
