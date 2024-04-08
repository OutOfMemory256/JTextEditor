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

    public static void initEditMenu(JMenuBar menuBar, ActionListener listener) {
        JMenu menu = new JMenu("Edit");
        menuBar.add(menu);

        addMenuItem(menu, "Undo", listener);
        addMenuItem(menu, "Redo", listener);
        menu.addSeparator();
        addMenuItem(menu, "Cut", listener);
        addMenuItem(menu, "Copy", listener);
        addMenuItem(menu, "Paste", listener);
        addMenuItem(menu, "Delete", listener);
        menu.addSeparator();
        addMenuItem(menu,  "Find", listener);
        addMenuItem(menu, "Replace", listener);
        menu.addSeparator();
        addMenuItem(menu, "Select all", listener);
        addMenuItem(menu, "Date and Time", listener);
    }

    public static void initFormatMenu(JMenuBar menuBar, ActionListener listener) {
        JMenu menu = new JMenu("Format");
        menuBar.add(menu);

        addCheckBoxMenuItem(menu, "Line Wrap", listener);
        addMenuItem(menu, "Font..", listener);
    }

    public static void initAboutMenu(JMenuBar menuBar, ActionListener listener) {
        JMenu menu = new JMenu("About");
        menuBar.add(menu);

        addMenuItem(menu, "About Program", listener);
    }

    private static void addMenuItem(JMenu menu, String title, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    private static void addCheckBoxMenuItem(JMenu menu, String title, ActionListener listener) {
        JCheckBoxMenuItem item = new JCheckBoxMenuItem(title);
        item.addActionListener(listener);
        menu.add(item);
    }
}
