package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MenuView {
    public static void displayMainView() {
        // Create the main frame
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to full screen size
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false); // Remove decorations (title bar, borders)

        // Custom panel with background image
        JPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create "Start Game" button with an image and hover image
        JButton startGameButton = createButtonWithHoverImage(
                "UIAssets/start_game.png", "UIAssets/start_game_hover.png");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code here to start the actual game
//                JOptionPane.showMessageDialog(frame, "Starting the Game!");
                GameView gameView = new GameView();
                gameView.startGame(frame);
            }
        });

        // Create "Start Test Game" button with an image and hover image
        JButton startTestGameButton = createButtonWithHoverImage(
                "UIAssets/start_test_game.png", "UIAssets/start_test_game_hover.png");
        startTestGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code here to start the test game
                JOptionPane.showMessageDialog(frame, "Starting the Test Game!");
            }
        });

        // Add buttons to the backgroundPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0); // Top, left, bottom, right padding
        backgroundPanel.add(startGameButton, gbc);

        gbc.gridy = 1;
        backgroundPanel.add(startTestGameButton, gbc);

        // Add the backgroundPanel to the center of the frame
        frame.add(backgroundPanel, BorderLayout.CENTER);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame to be visible
        frame.setVisible(true);
    }

    // Helper method to create a button with an image and hover image
    private static JButton createButtonWithHoverImage(String imagePath, String hoverImagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        ImageIcon hoverIcon = new ImageIcon(hoverImagePath);

        JButton button = new JButton(icon);
        button.setRolloverIcon(hoverIcon);
        button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
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

    ////////////////////////////////////////////////////////////////////////////
    // [2] CODE for choosing Pokemons
}
