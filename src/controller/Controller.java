package controller;

import util.FontChooser;
import view.View;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;

    private FileMenuActions fileMenuActions;
    private ActionListener fileMenuListener;

    private EditMenuActions editMenuActions;
    private ActionListener editMenuListener;

    private ActionListener formatMenuListener;
    private ActionListener aboutMenuListener;

    public void init() {
        if(view != null) {
            initFileMenuListener();
            initEditMenuListener();
            initFormatMenuListener();
            initAboutMenuListener();
        } else
            System.out.println("View didn't initialize");
    }

    private void initFileMenuListener() {
        fileMenuActions = new FileMenuActions(view);
        fileMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "New" -> fileMenuActions.createFile();
                    case "New Frame" -> fileMenuActions.openNewFrame();
                    case "Open" -> fileMenuActions.openFile();
                    case "Save" -> fileMenuActions.saveFile();
                    case "Save as.." -> fileMenuActions.saveFileAs();
                    case "Exit" -> fileMenuActions.exit();
                }
            }
        };
    }

    private void initEditMenuListener() {
        editMenuActions = new EditMenuActions(view);
        editMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        };
    }

    private void initFormatMenuListener() {
        formatMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        };
    }

    private void initAboutMenuListener() {
        aboutMenuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mahmel Production Inc.\nJNotepad\nVersion: 1.0", "Program Info", JOptionPane.INFORMATION_MESSAGE);
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

    public void setView(View view) {
        this.view = view;
        init();
    }
}
