package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;
    private JTextArea textArea = new JTextArea();
    private UndoManager undoManager = new UndoManager();

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
        MenuBarHelper.initEditMenu(menuBar, controller.getEditMenuListener());
        MenuBarHelper.initFormatMenu(menuBar, controller.getFormatMenuListener());
        MenuBarHelper.initAboutMenu(menuBar, controller.getAboutMenuListener());

        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    private void initTextArea() {
        textArea.getDocument().addUndoableEditListener(controller.getUndoManager());

        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setBorder(BorderFactory.createMatteBorder(1,0, 0, 0, Color.GRAY));

        getContentPane().add(scrollPane);
    }

    public boolean hasCurrentFileForChanges() {
        return controller.getUndoManager().canUndoOrRedo();
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        init();
    }
}
