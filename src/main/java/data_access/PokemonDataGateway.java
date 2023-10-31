package data_access;

import entity.Pokemon;

public interface PokemonDataGateway {
    Pokemon fetchPokemonData(String pokemonName);
}
