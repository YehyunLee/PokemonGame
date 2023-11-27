package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.io.File;
import javax.swing.Timer;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JProgressBar;


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

    private JButton attackButton;

    private JButton heavyAttackButton;
    private JButton lightAttackButton;
    private JButton trueAttackButton;
    private JButton defenseButton;
    private JButton heavyDefenseButton;
    private JButton lightDefenseButton;
    private JButton healButton;
    private JButton lightHealButton;
    private JButton heavyHealButton;

    private JButton swapButton;

    private JButton blankButton;

    private JButton zero;
    private JButton one;

    private JButton two;

    private JButton three;

    private JButton four;

    private JButton five;

    private JProgressBar playerHealthBar;
    private JProgressBar enemyHealthBar;

    public BackgroundPanel backgroundPanel = new BackgroundPanel("UIAssets/battle.png");

    public ImageIcon backTestIcon = new ImageIcon("UIAssets/back-test.gif");

    public GridBagConstraints gbc = new GridBagConstraints();


    public BattleView() {
        initializeFrame();
        initializeTimer();
        initializeBackgroundPanel();
        initializePokemonLabels();
        initilizeAllButtons();
        inintilizeHealthBars();
        initilizeMenuView();
        initializeFrameLayout();
        initializeConsoleTextArea();
        packAndShowFrame();
    }

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


    private void initializeFrame() {
        frame = new JFrame("Battle View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initializeTimer() {
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
                    if (consoleTextArea.getText().length() > 0) {
                        consoleTextArea.append("\n");
                    }
                    charIndex = 0;
                }

                consoleTextArea.append(String.valueOf(currentMessage.charAt(charIndex)));
                consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
                charIndex++;
            }
        });
    }

    private void initializeBackgroundPanel() {
        backgroundPanel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundPanel);
    }

    private void initializePokemonLabels() {
        backTestLabel = createScaledPokemonLabel("UIAssets/back-test.gif");
        frontTestLabel = createScaledPokemonLabel("UIAssets/front-test.gif");
    }

    private JLabel createScaledPokemonLabel(String imagePath) {
        ImageIcon pokemonIcon = new ImageIcon(imagePath);
        JLabel pokemonLabel = new JLabel(new ImageIcon(pokemonIcon.getImage().getScaledInstance(
                pokemonIcon.getIconWidth() * 2,
                pokemonIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT)));
        return pokemonLabel;
    }

    public void initilizeAllButtons() {

        swapButton = createFixedSizeButtonWithHover("swap_button", "swap_button_hover", new Dimension(170, 100));
        swapButton.addActionListener(this::swapButtonClicked);

        blankButton = createFixedSizeButtonWithHover("blank_button", "blank_button_hover", new Dimension(170, 100));
        blankButton.addActionListener(this::blankButtonClicked);


        attackButton = createFixedSizeButtonWithHover("attack_button", "attack_button_hover", new Dimension(170, 100));
        attackButton.addActionListener(this::attackButtonClicked);

        heavyAttackButton = createFixedSizeButtonWithHover("heavy_attack_button", "heavy_attack_button_hover", new Dimension(170, 100));
        heavyAttackButton.addActionListener(this::heavyAttackButtonClicked);

        lightAttackButton = createFixedSizeButtonWithHover("light_attack_button", "light_attack_button_hover", new Dimension(170, 100));
        lightAttackButton.addActionListener(this::lightAttackButtonClicked);

        trueAttackButton = createFixedSizeButtonWithHover("true_attack_button", "true_attack_button_hover", new Dimension(170, 100));
        trueAttackButton.addActionListener(this::trueAttackButtonClicked);

        defenseButton = createFixedSizeButtonWithHover("defense_button", "defense_button_hover", new Dimension(170, 100));
        defenseButton.addActionListener(this::defenseButtonClicked);


        heavyDefenseButton = createFixedSizeButtonWithHover("heavy_defense_button", "heavy_defense_button_hover", new Dimension(170, 100));
        heavyDefenseButton.addActionListener(this::heavyDefenseButtonClicked);

        lightDefenseButton = createFixedSizeButtonWithHover("light_defense_button", "light_defense_button_hover", new Dimension(170, 100));
        lightDefenseButton.addActionListener(this::lightDefenseButtonClicked);


        healButton = createFixedSizeButtonWithHover("heal_button", "heal_button_hover", new Dimension(170, 100));
        healButton.addActionListener(this::healButtonClicked);


        lightHealButton = createFixedSizeButtonWithHover("light_heal_button", "light_heal_button_hover", new Dimension(170, 100));
        lightHealButton.addActionListener(this::lightHealButtonClicked);

        heavyHealButton = createFixedSizeButtonWithHover("heavy_heal_button", "heavy_heal_button_hover", new Dimension(170, 100));
        heavyHealButton.addActionListener(this::heavyHealButtonClicked);


        zero = createFixedSizeButtonWithHover("0", "0_hover", new Dimension(170, 100));
        zero.addActionListener(this::zeroButtonClicked);

        one = createFixedSizeButtonWithHover("1", "1_hover", new Dimension(170, 100));
        one.addActionListener(this::oneButtonClicked);

        two = createFixedSizeButtonWithHover("2", "2_hover", new Dimension(170, 100));
        two.addActionListener(this::twoButtonClicked);

        three = createFixedSizeButtonWithHover("3", "3_hover", new Dimension(170, 100));
        three.addActionListener(this::threeButtonClicked);

        four = createFixedSizeButtonWithHover("4", "4_hover", new Dimension(170, 100));
        four.addActionListener(this::fourButtonClicked);

        five = createFixedSizeButtonWithHover("5", "5_hover", new Dimension(170, 100));
        five.addActionListener(this::fiveButtonClicked);

    }

    public void inintilizeHealthBars() {
        // Initialize the health bars
        playerHealthBar = new JProgressBar(0, 100);
        playerHealthBar.setValue(100); // Starting health
        playerHealthBar.setStringPainted(true); // Show health percentage
        playerHealthBar.setForeground(Color.GREEN); // Health color

        enemyHealthBar = new JProgressBar(0, 100);
        enemyHealthBar.setValue(100); // Starting health
        enemyHealthBar.setStringPainted(true); // Show health percentage
        enemyHealthBar.setForeground(Color.GREEN); // Health color

        // Assuming the backTestLabel and frontTestLabel are the Pokémon sprites:
        backTestLabel.setLayout(new BorderLayout());
        backTestLabel.add(playerHealthBar, BorderLayout.NORTH);
        frontTestLabel.setLayout(new BorderLayout());
        frontTestLabel.add(enemyHealthBar, BorderLayout.NORTH);
    }

    public void initilizeMenuView() {
        // Initialize and configure the bottomMenuPanel
        bottomMenuPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        bottomMenuPanel.setOpaque(false);


        // Add buttons with adjusted size
        bottomMenuPanel.add(attackButton);
        bottomMenuPanel.add(healButton);
        bottomMenuPanel.add(defenseButton);
        bottomMenuPanel.add(swapButton);
    };

    public void initializeFrameLayout() {

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
    }

    public void initializeConsoleTextArea() {
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
    }

    public void packAndShowFrame () {
        frame.pack();
        frame.setVisible(true);
        updateHealthBarPositions();
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

        Image img = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        Image hoverImg = hoverIcon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setRolloverIcon(new ImageIcon(hoverImg));

        // Determine the button action based on the image name
        if (imageName.equals("attack_button")) {
            button.addActionListener(this::attackButtonClicked);
        } else if (imageName.equals("heal_button")) {
            button.addActionListener(this::healButtonClicked);
        } else if (imageName.equals("defense_button")) {
            button.addActionListener(this::defenseButtonClicked);
        } else if (imageName.equals("swap_button")) {
            button.addActionListener(this::swapButtonClicked);
        }

        return button;
    }

    private void attackButtonClicked(ActionEvent e) {
        // Clear the panel and add new buttons
        bottomMenuPanel.removeAll();

        bottomMenuPanel.add(heavyAttackButton);
        bottomMenuPanel.add(lightAttackButton);
        bottomMenuPanel.add(trueAttackButton);
        bottomMenuPanel.add(blankButton);

        bottomMenuPanel.revalidate();
        bottomMenuPanel.repaint();
    }



    private void heavyAttackButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Heavy Attack Selected!");
        // Additional logic for Heavy Attack can be added here
    }

    private void swapButtonClicked(ActionEvent e) {
        bottomMenuPanel.removeAll();

        bottomMenuPanel.add(zero);
        bottomMenuPanel.add(one);
        bottomMenuPanel.add(two);
        bottomMenuPanel.add(three);
        bottomMenuPanel.add(four);
        bottomMenuPanel.add(five);


        bottomMenuPanel.revalidate();
        bottomMenuPanel.repaint();
    }

    private void blankButtonClicked(ActionEvent e) {
        bottomMenuPanel.removeAll();

        JOptionPane.showMessageDialog(frame, "Blank");

//        bottomMenuPanel.add(attackButton);
//        bottomMenuPanel.add(defenseButton);
//        bottomMenuPanel.add(healButton);
//        bottomMenuPanel.add(swapButton);
//
//        bottomMenuPanel.revalidate();
//        bottomMenuPanel.repaint();
    }


    private void lightAttackButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Light Attack Selected!");
    }

    private void trueAttackButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "True Attack Selected!");
    }


    private void zeroButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 0");
    }


    private void oneButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 1");
    }

    private void twoButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 2");
    }

    private void threeButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 3");
    }

    private void fourButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 4");
    }

    private void fiveButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 5");
    }



    private void defenseButtonClicked(ActionEvent e) {
        bottomMenuPanel.removeAll();

        bottomMenuPanel.add(heavyDefenseButton);
        bottomMenuPanel.add(lightDefenseButton);
        bottomMenuPanel.add(blankButton);
        bottomMenuPanel.revalidate();
        bottomMenuPanel.repaint();
    }

    private void heavyDefenseButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Heavy Defense button clicked!");
        // Implement defense logic here
    }

    private void lightDefenseButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Light Defense button clicked!");
        // Implement defense logic here
    }


    private void healButtonClicked(ActionEvent e) {
        bottomMenuPanel.removeAll();

        bottomMenuPanel.add(heavyHealButton);
        bottomMenuPanel.add(lightHealButton);
        bottomMenuPanel.add(blankButton);
        bottomMenuPanel.revalidate();
        bottomMenuPanel.repaint();
    }


    private void lightHealButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Light Heal button clicked!");
        // Implement heal logic here
    }

    private void heavyHealButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Heavy Heal button clicked!");
        // Implement heal logic here
    }

    // Method to update the player's health bar
    public void updatePlayerHealth(int health) {
        playerHealthBar.setValue(health);
    }

    // Method to update the enemy's health bar
    public void updateEnemyHealth(int health) {
        enemyHealthBar.setValue(health);
    }


    public void updateHealthBarPositions() {
        // The offset will be used to place the health bar slightly above the sprite.
        int offset = 20; // You may need to adjust this value

        // Get the bounds of the Pokémon labels.
        Rectangle backLabelBounds = backTestLabel.getBounds();
        Rectangle frontLabelBounds = frontTestLabel.getBounds();

        // Set the health bar bounds. Adjust the y position by subtracting the offset and the height of the health bar.
        playerHealthBar.setBounds(backLabelBounds.x, backLabelBounds.y - playerHealthBar.getPreferredSize().height - offset, backLabelBounds.width, playerHealthBar.getPreferredSize().height);
        enemyHealthBar.setBounds(frontLabelBounds.x, frontLabelBounds.y - enemyHealthBar.getPreferredSize().height - offset, frontLabelBounds.width, enemyHealthBar.getPreferredSize().height);
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
    public void updateFrontGif(String backGifPath) {
        ImageIcon backIcon = new ImageIcon(backGifPath);
        Image backImage = backIcon.getImage().getScaledInstance(
                backIcon.getIconWidth() * 2,
                backIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT
        );
        backTestLabel.setIcon(new ImageIcon(backImage));
    }

    public void updateBackGif(String frontGifPath) {
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