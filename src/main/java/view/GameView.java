package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import data_access.PokemonApiCallDataAccessObject;
import data_access.PokemonApiCallInterface;
import data_access.PokemonListFromSpritesDataAcessObject;
import data_access.PokemonListFromSpritesInterface;
import entity.Pokemon;
import use_case.*;

public class GameView extends JPanel {
    public void startGame(JFrame frame) {
        // Remove existing components
        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();

        // Custom panel with background image
        JPanel backgroundPanel = new MenuView.BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());

        // Show loading message
        JLabel loadingLabel = new JLabel("Loading 1000 Pokemon data... Please wait.");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 40));
        loadingLabel.setHorizontalAlignment(JLabel.CENTER);
        // Add the backgroundPanel to the center and loadingLabel to the north of the frame
        frame.add(backgroundPanel, BorderLayout.CENTER);
        frame.add(loadingLabel, BorderLayout.NORTH);

        /////////////////////// [USE CASE] ///////////////////////
        //Initialize the API and sprite data access objects
        PokemonApiCallInterface apiDataAccess = new PokemonApiCallDataAccessObject();
        PokemonListFromSpritesInterface spritesDataAccess = new PokemonListFromSpritesDataAcessObject();

        // Create an instance of PokemonFactoryFromData and inject the data access objects
        PokemonFactoryFromData factory = new PokemonFactoryFromData(apiDataAccess, spritesDataAccess);
        String[] allPokemonNames = factory.spriteParser.getAllPokemonNamesNoDuplicate(apiDataAccess);

        // remove loading msg
        frame.getContentPane().remove(loadingLabel);

        // display 2 button: "Use Loaded 1000 Pokemon Data" and "New API Call for 6 Pokemons"
        JButton useLoadedDataButton = new JButton("Use Loaded 1000 Pokemon Data");
        JButton newAPICallButton = new JButton("New API Call for 6 Pokemons");
        frame.add(useLoadedDataButton, BorderLayout.NORTH);
        frame.add(newAPICallButton, BorderLayout.SOUTH);

//        if useLoadedDataButton clicked remove all buttons and prompt hi
        useLoadedDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                remove button useLoadedDataButton
                frame.getContentPane().remove(useLoadedDataButton);
                frame.getContentPane().remove(newAPICallButton);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();

                // Create the game state
//                InitilizeGameState gameState = new InitilizeGameState(factory, allPokemonNames);
//                InitializeTestGameState gameState = new InitializeTestGameState(factory, allPokemonNames);

                // Constructor
                CreateAllPokemons allPokemons = new CreateAllPokemons(factory, allPokemonNames);
                Pokemon[] allPokemonsObjects = allPokemons.CreatePokemons();  // call method
            }
        });





    }

    // Custom panel class for drawing the background image
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File("UIAssets/pokemonBackground.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

}
