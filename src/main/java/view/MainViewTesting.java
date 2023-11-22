package view;

import interface_adapters.menu.MenuController;
import interface_adapters.menu.MenuViewModel;

import javax.swing.*;
import java.awt.*;

public class MainViewTesting {
    public static void main(String[] args) {

        MenuController menuController = new MenuController();
        MenuViewModel menuViewModel = new MenuViewModel();
        MenuView menuView = new MenuView(menuController, menuViewModel);
        //make sure the view is visible
        menuView.setVisible(true);

    }
}
