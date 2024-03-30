package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;
    private JTextArea textArea = new JTextArea();

    private void init() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        initGUI();
        setVisible(true);
    }

    private void initGUI() {
        initFrame();
        initTextArea();
        initMenuBar();

        pack();
    }

    private void initFrame() {
        setTitle("Sky Text Editor");
        setPreferredSize(new Dimension(800, 550));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        MenuBarHelper.initFileMenu(menuBar, controller.getFileMenuListener());
        MenuBarHelper.initEditMenu(this, menuBar);

        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    private void initTextArea() {
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setBorder(BorderFactory.createMatteBorder(1,0, 0, 0, Color.GRAY));

        getContentPane().add(scrollPane);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        init();
    }
}
