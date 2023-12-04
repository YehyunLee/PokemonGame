package use_case;

import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesInterface;
import entity.Pokemon;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PokemonApiCallInterfaceMock implements PokemonApiCallInterface {
    @Override
    public JSONArray fetchRawPokemonData(String pokemonName) {
        return new JSONArray();
    }
}

class PokemonListFromSpritesInterfaceMock implements PokemonListFromSpritesInterface {
    @Override
    public String[] getRawListOfPokemonSprites(String subfolder) {
        return new String[]{"path/to/sprite.png"};
    }
}

class PokemonFactoryFromDataTest {

    @Test
    void testCreatePokemonFromData() {
        // Arrange
        String pokemonName = "Pikachu";
        PokemonApiCallInterface apiDataAccessMock = new PokemonApiCallInterfaceMock();
        PokemonListFromSpritesInterface spritesDataAccessMock = new PokemonListFromSpritesInterfaceMock();
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccessMock, spritesDataAccessMock);

        Pokemon pokemon = factory.createPokemonFromData(pokemonName);
        pokemon.setName(pokemonName);

        // Assert
        assertNotNull(pokemon);
        assertEquals("Pikachu", pokemon.getName());
    }


    @Test
    void testSetPokemonAttributes() {
        // Arrange
        PokemonApiCallInterface apiDataAccessMock = new PokemonApiCallInterfaceMock();
        PokemonListFromSpritesInterface spritesDataAccessMock = new PokemonListFromSpritesInterfaceMock();
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccessMock, spritesDataAccessMock);
        Pokemon pokemon = new Pokemon();


        factory.setPokemonAttributes(pokemon, "Pikachu");

        pokemon.setHealth(100);
        pokemon.setNumber(0);

        // Assert
        assertEquals(0, pokemon.getNumber());
        assertEquals(100, pokemon.getHealth());
    }

    @Test
    void testSetPokemonSprites() {
        // Arrange
        PokemonApiCallInterface apiDataAccessMock = new PokemonApiCallInterfaceMock();
        PokemonListFromSpritesInterface spritesDataAccessMock = new PokemonListFromSpritesInterfaceMock();
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccessMock, spritesDataAccessMock);
        Pokemon pokemon = new Pokemon();


        factory.setPokemonSprites(pokemon, "Pikachu");

        // Assert
        assertEquals("path/to/sprite.png", pokemon.getFrontSprite());
    }
}
