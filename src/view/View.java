package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Controller controller;
    private JStealthTextArea textArea = new JStealthTextArea();
    private JComboBox<String> comboBox;
    private JTextField textField = new JTextField();

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

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.X_AXIS));
        innerPanel.add(menuBar);
        innerPanel.add(new JLabel("Password"));
        innerPanel.add(textField);
        textField.setColumns(32);

        String[] strs = new String[] {
                "CAESAR",
                "XOR"
        };
        comboBox = new JComboBox<>(strs);
        innerPanel.add(comboBox);
        getContentPane().add(innerPanel, BorderLayout.NORTH);
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

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public JTextField getTextField() {
        return textField;
    }
}
