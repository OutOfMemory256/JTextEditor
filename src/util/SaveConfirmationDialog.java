package util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveConfirmationDialog extends JDialog {
    private boolean userConfirmed;

    public SaveConfirmationDialog(JFrame parent) {
        super(parent, "Подтверждение сохранения", true);
        this.userConfirmed = false;

        // Настройка диалогового окна
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        JLabel messageLabel = new JLabel("Вы хотите сохранить изменения?");
        add(messageLabel);

        // Кнопка "Сохранить"
        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userConfirmed = true;
                dispose();
            }
        });
        add(saveButton);

        // Кнопка "Не сохранять"
        JButton dontSaveButton = new JButton("Не сохранять");
        dontSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userConfirmed = false;
                dispose();
            }
        });
        add(dontSaveButton);

        // Кнопка "Отмена"
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userConfirmed = false;
                dispose();
            }
        });
        add(cancelButton);

        // Настройка размеров и видимости диалога
        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isUserConfirmed() {
        return userConfirmed;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Пример диалогового окна");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton openDialogButton = new JButton("Открыть диалог");
        openDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveConfirmationDialog dialog = new SaveConfirmationDialog(frame);
                dialog.setVisible(true);

                if (dialog.isUserConfirmed()) {
                    System.out.println("Изменения сохранены.");
                } else {
                    System.out.println("Изменения не сохранены.");
                }
            }
        });

        frame.add(openDialogButton);
        frame.setVisible(true);
    }
}

