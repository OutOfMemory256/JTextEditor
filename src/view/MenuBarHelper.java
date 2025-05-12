package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuBarHelper {
    public static void initFileMenu(JMenuBar menuBar, ActionListener listener) {
        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        String[] fileMenuItems = new String[]{"New", "New Frame", "Open", "Save", "Save as..", "Separator", "Exit"};
        for (String item: fileMenuItems)
            if (item.equals("Separator"))
                menu.addSeparator();
            else
                addMenuItem(menu, item, listener);
    }

    public static void initEditMenu(JMenuBar menuBar, ActionListener listener) {
        JMenu menu = new JMenu("Edit");
        menuBar.add(menu);

        String[] editMenuItems = new String[] {"Undo", "Redo", "Separator", "Cut", "Copy", "Paste", "Delete", "Separator", "Find", "Replace", "Select all", "Date and Time"};
        for (String item: editMenuItems)
            if (item.equals("Separator"))
                menu.addSeparator();
            else
                addMenuItem(menu, item, listener);
    }

    public static void initCipheringMenu(JMenuBar menuBar, ActionListener listener) {
        JMenu menu = new JMenu("Ciphering");
        menuBar.add(menu);

        addCheckBoxMenuItem(menu, "Visibility", listener);
        addMenuItem(menu, "Encipher File", listener);
        addMenuItem(menu, "Decipher File", listener);
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
