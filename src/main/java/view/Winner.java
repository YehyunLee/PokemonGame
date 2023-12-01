package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Winner {
    public void displayWinnerView(JFrame existingFrame) {
        // Use the existing frame instead of creating a new one
        JFrame frame = existingFrame;
        frame.setTitle("Winner!");

        // Clear the previous content of the frame
        frame.getContentPane().removeAll();
        frame.repaint();

        // Custom panel with background image
        JPanel backgroundPanel = new BackgroundPanel("UIAssets/win.png");
        backgroundPanel.setLayout(new GridBagLayout());

        // Create a label with the winning message, using HTML for styling
        JLabel winnerLabel = new JLabel("<html><u>You Are the New Pokemon Champion!</u></html>");
        winnerLabel.setFont(new Font("Serif", Font.BOLD, 90)); // Increase font size
        winnerLabel.setForeground(Color.RED); // Set font color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        backgroundPanel.add(winnerLabel, gbc); // Add the label to the background panel

        // Update the content of the existing frame
        frame.setContentPane(backgroundPanel);
        frame.revalidate(); // Refresh the frame layout

        // Set the frame to be visible (if it's not already)
        frame.setVisible(true);
    }

    // Custom panel class for drawing the background image
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = ImageIO.read(new File(imagePath));
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
