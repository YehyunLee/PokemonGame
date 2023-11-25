package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;

public class BattleView {

    private JFrame frame;
    private JLabel backTestLabel;
    private JLabel frontTestLabel;
    private JPanel bottomMenuPanel;
    private BackgroundPanel mainPanel;


    // Custom panel with background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            try {
                backgroundImage = ImageIO.read(new File(fileName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image, scaled to fill the entire panel
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }


    public BattleView() {
        frame = new JFrame("Battle View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set up the background panel and use it as the main content pane
        BackgroundPanel backgroundPanel = new BackgroundPanel("UIAssets/battle.png");
        backgroundPanel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundPanel);

        ImageIcon backTestIcon = new ImageIcon("UIAssets/back-test.gif");
        backTestLabel = new JLabel(new ImageIcon(backTestIcon.getImage().getScaledInstance(
                backTestIcon.getIconWidth() * 2,
                backTestIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT)));

        ImageIcon frontTestIcon = new ImageIcon("UIAssets/front-test.gif");
        frontTestLabel = new JLabel(new ImageIcon(frontTestIcon.getImage().getScaledInstance(
                frontTestIcon.getIconWidth() * 2,
                frontTestIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT)));

        // Adjust the button size for a better fit
        // Adjust the button size for a better fit
        Dimension buttonSize = new Dimension(170, 100); // Increased width to 170

        bottomMenuPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        bottomMenuPanel.setOpaque(false);
        // Add buttons with adjusted size
        bottomMenuPanel.add(createFixedSizeButtonWithHover("attack_button", "attack_button_hover", buttonSize));
        bottomMenuPanel.add(createFixedSizeButtonWithHover("heal_button", "heal_button_hover", buttonSize));
        bottomMenuPanel.add(createFixedSizeButtonWithHover("defense_button", "defense_button_hover", buttonSize));
        bottomMenuPanel.add(createFixedSizeButtonWithHover("swap_button", "swap_button_hover", buttonSize));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.BOTH;

        // Back Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(backTestLabel, gbc);

        // Front Label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(frontTestLabel, gbc);

        // Bottom Menu Panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0; // Set this to 0 so the bottom menu doesn't expand vertically
        gbc.anchor = GridBagConstraints.SOUTHWEST; // Anchor the panel to the bottom left
        gbc.fill = GridBagConstraints.NONE; // No fill as we don't want to stretch the buttons
        frame.add(bottomMenuPanel, gbc);

        frame.pack(); // Pack the frame for a compact look
        frame.setVisible(true);
    }

    private JButton createFixedSizeButtonWithHover(String imageName, String hoverImageName, Dimension size) {
        ImageIcon icon = new ImageIcon("UIAssets/" + imageName + ".png");
        ImageIcon hoverIcon = new ImageIcon("UIAssets/" + hoverImageName + ".png");

        JButton button = new JButton(icon);
        button.setRolloverIcon(hoverIcon);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);

        // Scale the button icons to fit the button size
        Image img = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        Image hoverImg = hoverIcon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setRolloverIcon(new ImageIcon(hoverImg));

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, imageName + " button clicked!");
            }
        });

        return button;
    }


    private GridBagConstraints createGbc(int x, int y, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width; // Variable width for flexible sizing
        gbc.gridheight = 1;

        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        gbc.fill = (y == 0) ? GridBagConstraints.NONE : GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weightx = 1.0;
        gbc.weighty = (y == 0) ? 0.9 : 0.1; // Allocate more vertical space to the sprites
        return gbc;
    }


    // Method to change the GIFs
    public void updateGifs(String backGifPath, String frontGifPath) {
        // This method would also need to scale the new GIFs as done above
        ImageIcon backIcon = new ImageIcon(backGifPath);
        Image backImage = backIcon.getImage().getScaledInstance(
                backIcon.getIconWidth() * 2,
                backIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT
        );
        backTestLabel.setIcon(new ImageIcon(backImage));

        ImageIcon frontIcon = new ImageIcon(frontGifPath);
        Image frontImage = frontIcon.getImage().getScaledInstance(
                frontIcon.getIconWidth() * 2,
                frontIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT
        );
        frontTestLabel.setIcon(new ImageIcon(frontImage));
    }

    // Static method to display the battle view
    public static void DisplayBattleView() {
        SwingUtilities.invokeLater(() -> new BattleView());
    }

}