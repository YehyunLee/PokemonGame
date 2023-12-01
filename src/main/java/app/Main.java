package app;
import view.MenuView;
import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonListFromSpritesDataAcessObject;


public class Main {
    public static void main(String[] args) {
        PokemonApiCallDataAccessObject apiDataAccess = new PokemonApiCallDataAccessObject();
        PokemonListFromSpritesDataAcessObject spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        MenuView menuView = new MenuView(apiDataAccess, spritesDataAccess);
        MenuView.displayMainView();

    }
}