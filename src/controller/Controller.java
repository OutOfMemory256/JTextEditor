package controller;

import util.FontChooser;
import view.JStealthTextArea;
import view.View;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionListener;

public class Controller {
    private View view;

    private FileMenuActions fileMenuActions;
    private ActionListener fileMenuListener;

    private EditMenuActions editMenuActions;
    private ActionListener editMenuListener;

    private ActionListener formatMenuListener;
    private ActionListener aboutMenuListener;
    private ActionListener cipheringMenuListener;

    public void init() {
        if(view != null) {
            initFileMenuListener();
            initEditMenuListener();
            initFormatMenuListener();
            initAboutMenuListener();
            initCipheringMenuListener();
        } else
            System.out.println("View didn't initialize");
    }

    private void initFileMenuListener() {
        fileMenuActions = new FileMenuActions(view);
        fileMenuListener = e -> {
            switch (e.getActionCommand()) {
                case "New" -> fileMenuActions.createFile();
                case "New Frame" -> fileMenuActions.openNewFrame();
                case "Open" -> fileMenuActions.openFile();
                case "Save" -> fileMenuActions.saveFile();
                case "Save as.." -> fileMenuActions.saveFileAs();
                case "Exit" -> fileMenuActions.exit();
            }
        };
    }

    private void initEditMenuListener() {
        editMenuActions = new EditMenuActions(view);
        editMenuListener = e -> {
            switch (e.getActionCommand()) {
                case "Undo" -> editMenuActions.undo();
                case "Redo" -> editMenuActions.redo();
                case "Cut" -> editMenuActions.cut();
                case "Copy" -> editMenuActions.copy();
                case "Paste" -> editMenuActions.paste();
                case "Delete" -> editMenuActions.delete();
                case "Find" -> editMenuActions.find();
                case "Select all" -> view.getTextArea().selectAll();
                case "Date and Time" -> editMenuActions.printDateAndTime();
                default -> System.out.println("Working...");
            }
        };
    }

    private void initFormatMenuListener() {
        formatMenuListener = e -> {
            switch (e.getActionCommand()) {
                case "Line Wrap" -> {
                    boolean isLineWrap = view.getTextArea().getLineWrap();
                    view.getTextArea().setLineWrap(!isLineWrap);

                    JCheckBoxMenuItem item = ((JCheckBoxMenuItem) e.getSource());
                    item.setSelected(!isLineWrap);
                }
                case "Font.." -> {
                    FontChooser fontChooser = new FontChooser(view.getTextArea());
                    fontChooser.showDialog(view);
                }
            }
        };
    }

    private void initAboutMenuListener() {
        aboutMenuListener = e -> JOptionPane.showMessageDialog(null, "Mahmel Production Inc.\nJNotepad\nVersion: 1.0", "Program Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void initCipheringMenuListener() {
        cipheringMenuListener = e -> {
            switch (e.getActionCommand()) {
                case "Visibility" -> {
                    JStealthTextArea stealthTextArea = view.getTextArea();
                    stealthTextArea.setStealthModeEnabled(!stealthTextArea.isStealthModeEnabled());

                    JCheckBoxMenuItem item = ((JCheckBoxMenuItem) e.getSource());
                    item.setSelected(!stealthTextArea.isStealthModeEnabled());

                    stealthTextArea.updateTextDisplay();
                }
            }
        };
    }

    public UndoManager getUndoManager() {
        return editMenuActions.getUndoManager();
    }

    public ActionListener getFileMenuListener() {
        return fileMenuListener;
    }

    public ActionListener getEditMenuListener() {
        return editMenuListener;
    }

    public ActionListener getFormatMenuListener() {
        return formatMenuListener;
    }

    public ActionListener getAboutMenuListener() {
        return aboutMenuListener;
    }

    public ActionListener getCipheringMenuListener() {
        return cipheringMenuListener;
    }

    public void setView(View view) {
        this.view = view;
        init();
    }
}
