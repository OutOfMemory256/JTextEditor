package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;
    private JStealthTextArea textArea = new JStealthTextArea();

    private void init() {
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
        MenuBarHelper.initCipheringMenu(menuBar, controller.getCipheringMenuListener());

        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    private void initTextArea() {
        textArea.getDocument().addUndoableEditListener(controller.getUndoManager());

        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));

        getContentPane().add(scrollPane);
    }

    public boolean hasCurrentFileChanges() {
        return controller.getUndoManager().canUndoOrRedo();
    }

    public JStealthTextArea getTextArea() {
        return textArea;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        init();
    }
}
