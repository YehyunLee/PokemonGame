package entity;

public class GameState {
    public boolean turn;
    GameState() {
        this.turn = true;
    }

    public boolean getGameState() {
        return turn;
    }

    public boolean changeTurn() {
        this.turn = !turn;
        return turn;
    }

}
