package entity;

public class GameState implements State {
    private boolean turn;

    GameState() {
        this.turn = true;
    }

    @Override
    public boolean getGameState() {
        return turn;
    }

    public boolean changeTurn() {
        this.turn = !turn;
        return turn;
    }


}
