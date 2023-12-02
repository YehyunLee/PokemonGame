package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.CreatePlayersTest;
import use_case.PokemonFactoryFromData;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState gameState = new GameState();

    @BeforeEach
    void setUp() {
        gameState.initializeGameState();


    }

    @Test
    void initializeGameState() {

    }
}