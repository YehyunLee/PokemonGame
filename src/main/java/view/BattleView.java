package view;

import use_case.RunGameOutput;
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
import javax.swing.JProgressBar;

/**
 * View for handling the Battle Effects.
 */
public class BattleView implements BattleViewInterface {
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
    public GridBagConstraints gbc = new GridBagConstraints();

    public String move = "";
    private RunGameOutput gameOutput;

    /**
     * Constructor
     */
    public BattleView(JFrame frame) {
        initializeFrame(frame);
        initializeTimer();
        initializeBackgroundPanel();
        initializePokemonLabels();
        initilizeAllButtons();
        inintilizeHealthBars();
        initilizeMenuView();
        initializeFrameLayout();
        initializeConsoleTextArea();
    }

    /**
     * Sets the game output interface for this view.
     * @param gameOutput The game output interface to be used.
     */
    public void setGameOutput(RunGameOutput gameOutput) {
        this.gameOutput = gameOutput;
    }

    /**
     * Custom JPanel with a background image.
     */
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        /**
         * Constructs a BackgroundPanel with the specified image file.
         * @param fileName The path to the image file.
         */
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


    /**
     * Initializes the main frame of the Battle View.
     */
    private void initializeFrame(JFrame frame) {
        this.frame = frame;
        frame.setTitle("Battle View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Initializes the typing timer for console text animation.
     */
    private void initializeTimer() {
        typingTimer = new Timer(15, new ActionListener() {
            private String currentMessage;
            private int charIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMessage == null || charIndex >= currentMessage.length()) {
                    if (messageQueue.isEmpty()) {
                        typingTimer.stop();
                        isTyping = false;
                        setButtonsEnabled(true); // Re-enable buttons when typing ends
                        return;
                    }
                    currentMessage = messageQueue.poll();
                    if (consoleTextArea.getText().length() > 0) {
                        consoleTextArea.append("\n");
                    }
                    charIndex = 0;
                    setButtonsEnabled(false); // Disable buttons when typing starts
                }

                consoleTextArea.append(String.valueOf(currentMessage.charAt(charIndex)));
                consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
                charIndex++;
            }
        });
    }

    /**
     * Prevents clicks from the Buttons as text is being printed
     */
    private void setButtonsEnabled(boolean enabled) {
        attackButton.setEnabled(enabled);
        heavyAttackButton.setEnabled(enabled);
        lightAttackButton.setEnabled(enabled);
        trueAttackButton.setEnabled(enabled);
        defenseButton.setEnabled(enabled);
        heavyDefenseButton.setEnabled(enabled);
        lightDefenseButton.setEnabled(enabled);
        healButton.setEnabled(enabled);
        lightHealButton.setEnabled(enabled);
        heavyHealButton.setEnabled(enabled);
        swapButton.setEnabled(enabled);
        zero.setEnabled(enabled);
        one.setEnabled(enabled);
        two.setEnabled(enabled);
        three.setEnabled(enabled);
        four.setEnabled(enabled);
        five.setEnabled(enabled);
    }

    /**
     * Initializes the background panel for the frame.
     */
    private void initializeBackgroundPanel() {
        backgroundPanel.setLayout(new GridBagLayout());
        frame.setContentPane(backgroundPanel);
    }

    /**
     * Initializes the labels for Pokémon images.
     */
    private void initializePokemonLabels() {
        backTestLabel = createScaledPokemonLabel("UIAssets/back-test.gif");
        frontTestLabel = createScaledPokemonLabel("UIAssets/front-test.gif");
    }

    /**
     * Creates a scaled JLabel for a Pokémon image.
     * @param imagePath The path to the image file.
     * @return A JLabel with a scaled version of the image.
     */
    private JLabel createScaledPokemonLabel(String imagePath) {
        ImageIcon pokemonIcon = new ImageIcon(imagePath);
        JLabel pokemonLabel = new JLabel(new ImageIcon(pokemonIcon.getImage().getScaledInstance(
                pokemonIcon.getIconWidth() * 2,
                pokemonIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT)));
        return pokemonLabel;
    }

    /**
     * Creates all the buttons with Hover effect
     */
    public void initilizeAllButtons() {
        swapButton = createFixedSizeButtonWithHover("swap_button", "swap_button_hover", new Dimension(170, 100));
        swapButton.addActionListener(this::swapButtonClicked);
        blankButton = createFixedSizeButtonWithHover("blank_button", "blank_button_hover", new Dimension(170, 100));
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

    /**
     * Initialize the health bars
     */
    public void inintilizeHealthBars() {
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

    /**
     * Initialize the MenuView
     */
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

    /**
     * Set Frame layout
     */
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

    /**
     * Make the text area
     */
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

    /**
     * Method to render the display area
     */
    public void displayBattleView () {
        frame.pack();
        frame.setVisible(true);
        updateHealthBarPositions();
    }

    /**
     * Adds text to Queue to print out
     * @param message The message to be printed
     */
    public void printToConsole (String message) {
        messageQueue.add(message);
        if (!isTyping) {
            isTyping = true;
            typingTimer.start();
        }
    }

    /**
     * Creates a JButton with specified images for its default and hover states.
     *
     * @param imageName       The name of the image file for the button's default appearance.
     * @param hoverImageName  The name of the image file for the button's hover appearance.
     * @param size            The size of the button.
     * @return                A JButton with the specified properties.
     */
    private JButton createFixedSizeButtonWithHover(String imageName, String hoverImageName, Dimension size) {
        // Load icons for the button's default and hover states
        ImageIcon icon = new ImageIcon("UIAssets/" + imageName + ".png");
        ImageIcon hoverIcon = new ImageIcon("UIAssets/" + hoverImageName + ".png");

        // Create a new JButton with the default icon
        JButton button = new JButton(icon);
        button.setRolloverIcon(hoverIcon);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);

        // Scale the icons to fit the button size
        Image img = icon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        Image hoverImg = hoverIcon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setRolloverIcon(new ImageIcon(hoverImg));

        // Add action listeners based on the button type
        addActionListenerBasedOnImageName(button, imageName);

        return button;
    }

    /**
     * Adds an action listener to a button based on its image name.
     *
     * @param button     The JButton to which the listener is added.
     * @param imageName  The name of the image used to determine the type of action listener.
     */
    private void addActionListenerBasedOnImageName(JButton button, String imageName) {
        switch (imageName) {
            case "attack_button":
                button.addActionListener(this::attackButtonClicked);
                break;
            case "heal_button":
                button.addActionListener(this::healButtonClicked);
                break;
            case "defense_button":
                button.addActionListener(this::defenseButtonClicked);
                break;
            case "swap_button":
                button.addActionListener(this::swapButtonClicked);
                break;
            // Include additional cases for other buttons
        }
    }

    /**
     * Helper method to reset and update the bottom panel with specified buttons
     */
    private void updateBottomMenuPanel(JButton... buttons) {
        bottomMenuPanel.removeAll();
        for (JButton button : buttons) {
            bottomMenuPanel.add(button);
        }
        bottomMenuPanel.revalidate();
        bottomMenuPanel.repaint();
    }

    /**
     * Helper method for common operations in button click events
     */
    private void handleButtonClick(String moveName, Runnable action) {
        setMove(moveName);
        action.run();
        gameOutput.playRandomMove();
        updateBottomMenuPanel(attackButton, defenseButton, healButton, swapButton);
        String winner = gameOutput.getWinnerOfGame();
        if (winner.equals("Player")) {
            Winner win = new Winner();
            win.displayWinnerView(frame);
        } else if (winner.equals("AIPlayer")) {
            Loser lose = new Loser();
            lose.displayLoserView(frame);
        }
    }

    public void attackButtonClicked(ActionEvent e) {
        updateBottomMenuPanel(heavyAttackButton, lightAttackButton, trueAttackButton, blankButton);
    }

    public void heavyAttackButtonClicked(ActionEvent e) {
        handleButtonClick("Heavy Attack", () -> gameOutput.useAttackOnOpponent(move));
        flashScreen(Color.RED, 500);
    }

    public void lightAttackButtonClicked(ActionEvent e) {
        handleButtonClick("Light Attack", () -> gameOutput.useAttackOnOpponent(move));
        flashScreen(Color.RED, 500);
    }

    public void trueAttackButtonClicked(ActionEvent e) {
        handleButtonClick("True Attack", () -> gameOutput.useAttackOnOpponent(move));
        flashScreen(Color.RED, 500);
    }

    /**
     * Similar implementations for other attack buttons like lightAttackButtonClicked, trueAttackButtonClicked...
     */
    public void swapButtonClicked(ActionEvent e) {
        updateBottomMenuPanel(zero, one, two, three, four, five);
    }

    public void zeroButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 0");
        handleButtonClick("0", () -> gameOutput.SwitchPlayerPokemon(move));
    }

    public void oneButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 1");
        handleButtonClick("1", () -> gameOutput.SwitchPlayerPokemon(move));
    }

    public void twoButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 2");
        handleButtonClick("2", () -> gameOutput.SwitchPlayerPokemon(move));
    }

    public void threeButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 3");
        handleButtonClick("3", () -> gameOutput.SwitchPlayerPokemon(move));
    }

    public void fourButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 4");
        handleButtonClick("4", () -> gameOutput.SwitchPlayerPokemon(move));
    }

    public void fiveButtonClicked(ActionEvent e) {
        JOptionPane.showMessageDialog(frame, "Swap to Pokemon at Index 5");
        handleButtonClick("5", () -> gameOutput.SwitchPlayerPokemon(move));
    }

    public void defenseButtonClicked(ActionEvent e) {
        updateBottomMenuPanel(heavyDefenseButton, lightDefenseButton, blankButton);
    }

    public void heavyDefenseButtonClicked(ActionEvent e) {
        handleButtonClick("Heavy Defense", () -> gameOutput.useDefense(move));
        flashScreen(Color.ORANGE, 500);
    }

    public void lightDefenseButtonClicked(ActionEvent e) {
        handleButtonClick("Light Defense", () -> gameOutput.useDefense(move));
        flashScreen(Color.ORANGE, 500);
    }

    public void healButtonClicked(ActionEvent e) {
        updateBottomMenuPanel(heavyHealButton, lightHealButton, blankButton);
    }

    public void lightHealButtonClicked(ActionEvent e) {
        handleButtonClick("Light Heal", () -> gameOutput.useHealOnSelf(move));
        flashScreen(Color.GREEN, 500);
    }

    public void heavyHealButtonClicked(ActionEvent e) {
        handleButtonClick("Heavy Heal", () -> gameOutput.useHealOnSelf(move));
        flashScreen(Color.GREEN, 500);
    }

    /**
     * Method to update the player's health bar
     */
    public void updatePlayerHealth(int health) {
        playerHealthBar.setValue(health);
    }

    /**
     * Method to update the enemy's health bar
     */
    public void updateEnemyHealth(int health) {
        enemyHealthBar.setValue(health);
    }

    /**
     * Update Health Bar Positions
     */
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

    /**
     * Flash Screen function with semi-transparent overlay
     */
    private void flashScreen(Color color, int duration) {
        SwingUtilities.invokeLater(() -> {
            // Create a new frame to act as the flash overlay
            JFrame flashFrame = new JFrame();
            flashFrame.setUndecorated(true);
            flashFrame.setAlwaysOnTop(true);
            flashFrame.setBackground(new Color(0, 0, 0, 0)); // Completely transparent background

            // Create a panel with the desired flash color
            JPanel flashPanel = new JPanel();
            flashPanel.setOpaque(true);
            flashPanel.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 128)); // Semi-transparent

            // Add the panel to the frame and set the frame size to match the screen size
            flashFrame.add(flashPanel);
            flashFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

            // Display the flash frame
            flashFrame.setVisible(true);

            // Timer to remove the flash frame after the specified duration
            new Timer(duration, e -> {
                flashFrame.dispose(); // Close and dispose of the flash frame
                ((Timer) e.getSource()).stop(); // Stop the timer
            }).start();
        });
    }


    /**
     * Change the front Gif
     */
    public void updateFrontGif(String backGifPath) {
        ImageIcon backIcon = new ImageIcon(backGifPath);
        Image backImage = backIcon.getImage().getScaledInstance(
                backIcon.getIconWidth() * 2,
                backIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT
        );
        backTestLabel.setIcon(new ImageIcon(backImage));
    }

    /**
     * Change the back Gif
     */
    public void updateBackGif(String frontGifPath) {
        ImageIcon frontIcon = new ImageIcon(frontGifPath);
        Image frontImage = frontIcon.getImage().getScaledInstance(
                frontIcon.getIconWidth() * 2,
                frontIcon.getIconHeight() * 2,
                Image.SCALE_DEFAULT
        );
        frontTestLabel.setIcon(new ImageIcon(frontImage));
    }

    /**
     * Change the move
     */
    public void setMove(String move) {
        this.move = move;
    }


}