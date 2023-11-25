package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import javax.swing.Timer;
import java.util.LinkedList;
import java.util.Queue;

public class BattleView {

    private JFrame frame;
    private JLabel backTestLabel;
    private JLabel frontTestLabel;
    private JPanel bottomMenuPanel;
    private BackgroundPanel mainPanel;

    private JTextArea consoleTextArea;
    private JScrollPane consoleScrollPane;

    private Queue<String> messageQueue = new LinkedList<>();
    private Timer typingTimer;
    private boolean isTyping = false;


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


        typingTimer = new Timer(50, new ActionListener() {
            private String currentMessage;
            private int charIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMessage == null || charIndex >= currentMessage.length()) {
                    if (messageQueue.isEmpty()) {
                        typingTimer.stop();
                        isTyping = false;
                        return;
                    }
                    currentMessage = messageQueue.poll();
                    if (consoleTextArea.getText().length() > 0) { // Check if there is already text in the console
                        consoleTextArea.append("\n"); // Start on a new line if there is
                    }
                    charIndex = 0;
                }

                consoleTextArea.append(String.valueOf(currentMessage.charAt(charIndex)));
                consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength()); // Auto-scroll to the bottom
                charIndex++;
            }
        });


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
        gbc.gridx = 0; // The first column
        gbc.gridy = 0; // The first row
        gbc.gridwidth = 2; // Span two columns
        gbc.weightx = 0.5; // Request additional horizontal space
        gbc.weighty = 1; // Request additional vertical space
        gbc.anchor = GridBagConstraints.CENTER; // Center the component
        backgroundPanel.add(backTestLabel, gbc);

        // Front Label
        gbc.gridx = 3; // The second column
        gbc.gridy = 0; // Still the first row
        gbc.gridwidth = 2; // Span two columns
        gbc.weightx = 0.5; // Request additional horizontal space
        gbc.weighty = 1; // Request additional vertical space
        gbc.anchor = GridBagConstraints.CENTER; // Center the component
        backgroundPanel.add(frontTestLabel, gbc);

        // Place the bottom menu panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Only take up one cell
        gbc.weightx = 0; // Set weightx to 0 for now
        gbc.weighty = 0; // Set weighty to 0 as we don't want to stretch vertically
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally within its cell
        frame.add(bottomMenuPanel, gbc);


// Initialize the console text area
        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setBackground(new Color(188,162,126)); // Brown color for the box
        consoleTextArea.setForeground(Color.WHITE); // White text color
        consoleTextArea.setFont(new Font("SansSerif", Font.BOLD, 18)); // Set the font size and style

// Wrap the text area in a scroll pane
        consoleScrollPane = new JScrollPane(consoleTextArea);
        consoleScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

// Adjust constraints for the scroll pane
        gbc.gridx = 1; // Place it to the right of the buttons
        gbc.gridy = 1; // Same row as buttons
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Take up the rest of the row
        gbc.weightx = 1; // Give it all the remaining horizontal space
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        frame.add(consoleScrollPane, gbc);



        // Pack the frame and make it visible
        frame.pack();
        frame.setVisible(true);
    }

    public void appendToConsoleWithTypingAnimation(String message) {
        messageQueue.add(message);
        if (!isTyping) {
            isTyping = true;
            typingTimer.start();
        }
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



    public void appendToConsole(String message) {
        consoleTextArea.append(message + "\n");
        consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
    }


    // Static method to display the battle view
    public static void DisplayBattleView() {
        SwingUtilities.invokeLater(() -> new BattleView());
    }

}