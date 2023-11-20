package view;
import javax.swing.*;
public class MenuView extends JFrame {
    JFrame frame = new JFrame("Main Menu");
    JPanel panel = new JPanel();

    JButton buttonStart = new JButton("Start");
    JButton buttonExit = new JButton("Exit");
    JButton buttonMusic = new JButton("Music");
    JLabel label = new JLabel("Welcome!");
    public MenuView() {
        panel.setLayout(null);
        label.setBounds(10, 20, 120, 25);
        panel.add(label);
        buttonStart.setBounds(10, 80, 80, 25);
        panel.add(buttonStart);
        buttonExit.setBounds(10, 110, 80, 25);
        panel.add(buttonExit);
        buttonMusic.setBounds(10, 140, 80, 25);
        panel.add(buttonMusic);
        frame.add(panel);

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
