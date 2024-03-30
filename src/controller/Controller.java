package controller;

import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;
    private FileMenuActions fileMenuActions;
    private ActionListener fileMenuListener;

    public void init() {
        if(view != null)
            initFileMenuListener();
        else
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

    public ActionListener getFileMenuListener() {
        return fileMenuListener;
    }

    public void setView(View view) {
        this.view = view;
        init();
    }
}
