package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBarHelper {
    public static void initFileMenu(JMenuBar menuBar, ActionListener listener) {
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        addMenuItem(menu, "New", listener);
        addMenuItem(menu, "New Frame", listener);
        addMenuItem(menu, "Open", listener);
        addMenuItem(menu, "Save", listener);
        addMenuItem(menu, "Save as..", listener);
        menu.addSeparator();
        addMenuItem(menu, "Exit", listener);
    }

    public static void initEditMenu(JFrame frame, JMenuBar menuBar) {
        JMenu menu = new JMenu("Edit");
        menuBar.add(menu);

        menu.add(new JMenuItem("Edit"));
    }

    private static void addMenuItem(JMenu menu, String title, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }
}
