package controller;

import view.View;

import javax.swing.*;
import java.io.*;

public class FileMenuActions {
    private final View view;
    private File currentFile;

    public FileMenuActions(View view) {
        this.view = view;
    }

    public void createFile() {
        if (askForSavingChanges())
            return;
        view.getTextArea().setText("");
    }

    public void openNewFrame() {
        Controller controller = new Controller();
        View view = new View();
        controller.setView(view);
        view.setController(controller);
    }

    public void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(view) != JFileChooser.APPROVE_OPTION)
            return;
        else {
            currentFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                String line;
                view.getTextArea().setText("");
                while ((line = reader.readLine()) != null) {
                    view.getTextArea().append(line);
                    view.getTextArea().append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveFile() {
        if(currentFile == null) {
            saveFileAs();
            return;
        }

        try (PrintWriter writer = new PrintWriter(currentFile)) {
            writer.write(view.getTextArea().getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(view) != JFileChooser.APPROVE_OPTION)
            return;

        currentFile = fileChooser.getSelectedFile();
        try (PrintWriter writer = new PrintWriter(currentFile)) {
            writer.write(view.getTextArea().getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void exit() {
        view.dispose();
    }

    private boolean askForSavingChanges() {
        if(!view.hasCurrentFileChanges())
            return true;
        int answer = JOptionPane.showConfirmDialog(view, "Current file has some changes. Are you sure you want to close it?");
        return answer != JOptionPane.OK_OPTION;
    }
}
