package view;
import interface_adapters.menu.MenuController;
import interface_adapters.menu.MenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuView extends JFrame implements ActionListener, PropertyChangeListener {
    public final String viewName = "menu";
    private final MenuController menuController;
    private final MenuViewModel menuViewModel;
//    private final JButton start;
//    private final ImageIcon image;

    public MenuView(MenuController controller, MenuViewModel menuViewModel) {
    this.menuController = controller;
    this.menuViewModel = menuViewModel;
    menuViewModel.addPropertyChangeListener(this); //this registers the view as a listener to the view model

//    setExtendedState(JFrame.MAXIMIZED_BOTH);
//    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ImageIcon image = new ImageIcon(MenuViewModel.IMAGE_PATH);
    this.setIconImage(image.getImage());

    JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
    title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    title.setAlignmentY(JLabel.CENTER_ALIGNMENT);

    JPanel buttons = new JPanel();
    JButton start = new JButton(MenuViewModel.START_BUTTON_LABEL);
    buttons.add(start);

    start.addActionListener(new ActionListener()     {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(start)) {menuController.execute();}}});

    this.add(title);
    this.add(buttons);
    this.setSize(300, 300);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
