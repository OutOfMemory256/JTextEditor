package controller;

import view.View;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.undo.UndoManager;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditMenuActions {
    private View view;
    private UndoManager undoManager = new UndoManager();

    public EditMenuActions(View view) {
        this.view = view;
    }

    public void undo() {
        if(undoManager.canUndo())
            undoManager.undo();
    }

    public void redo() {
        if(undoManager.canRedo())
            undoManager.redo();
    }

    public void cut() {
        view.getTextArea().cut();
    }

    public void copy() {
        view.getTextArea().copy();
    }

    public void paste() {
        view.getTextArea().paste();
    }

    public void delete() {
        int startSelectionIndex = view.getTextArea().getSelectionStart();
        int endSelectionIndex = view.getTextArea().getSelectionEnd();

        try {
            view.getTextArea().getDocument().remove(startSelectionIndex, endSelectionIndex - startSelectionIndex);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    public void find() {
        JFrame frame = new JFrame();
        frame.setSize(300, 150);
        frame.setTitle("Find");
        frame.setResizable(false);

        //frame.getContentPane().add();

        frame.setVisible(true);
    }

    public void printDateAndTime() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm dd.MM.yyyy");
        view.getTextArea().append(format.format(new Date()));
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }
}
