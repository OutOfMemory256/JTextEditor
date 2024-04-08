package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordFinder implements ActionListener {
    private final JFrame frame = new JFrame();
    private final JTextField textField = new JTextField();
    private final JTextArea textArea;
    private final ButtonGroup radioGroup = new ButtonGroup();
    JRadioButton radioButtonUp = new JRadioButton("Up");
    JRadioButton radioButtonDown = new JRadioButton("Down");
    JCheckBox checkBoxButtonMatchCase = new JCheckBox("Match case");
    JCheckBox checkBoxButtonWrapAround = new JCheckBox("Wrap around");

    public WordFinder(JTextArea textArea) {
        this.textArea = textArea;
        initGUI();
    }

    private void initGUI() {
        initFrame();
        initTextInputField();
        initSettingsButtons();
        initControlButtons();

        frame.pack();
        frame.setVisible(true);
    }

    private void initFrame() {
        frame.setSize(new Dimension(250, 350));
        frame.setTitle("Find");
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initTextInputField() {
        JPanel topPanel = new JPanel(new GridBagLayout());

        JLabel findLabel = new JLabel("Find:");
        findLabel.setFont(new Font("Arial", Font.BOLD, 20));

        textField.setPreferredSize(new Dimension(200, 20));
        JScrollPane scrollPane = new JScrollPane(textField);
        scrollPane.setHorizontalScrollBar(new JScrollBar(JScrollBar.HORIZONTAL));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.insets = new Insets(0, 5, 0, 8);

        topPanel.add(findLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;

        topPanel.add(scrollPane, constraints);

        frame.add(topPanel, BorderLayout.NORTH);
    }

    private void initSettingsButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JPanel radioButtonsPanel = new JPanel(new GridLayout(2, 1));

        radioGroup.add(radioButtonUp);
        radioGroup.add(radioButtonDown);
        radioButtonsPanel.add(radioButtonUp);
        radioButtonsPanel.add(radioButtonDown);

        JPanel checkboxPanel = new JPanel(new GridLayout(2, 1));

        checkboxPanel.add(checkBoxButtonMatchCase);
        checkboxPanel.add(checkBoxButtonWrapAround);

        panel.add(radioButtonsPanel);
        panel.add(checkboxPanel);

        frame.add(panel, BorderLayout.CENTER);
    }

    private void initControlButtons() {
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.RIGHT);
        JPanel panel = new JPanel(layout);

        JButton findButton = new JButton("Find");
        findButton.addActionListener(this);
        panel.add(findButton);
        panel.add(new JButton("Cancel"));

        frame.add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = textField.getText();
        String text = textArea.getText();
        int startIndex = textArea.getSelectionStart();
        boolean hasFound = false;

        if (radioButtonUp.isSelected()) {
            startIndex -= 1;
            for (int i = startIndex; i >= 0; i--) {
                int temp = i;
                for(int j = str.length() - 1; j >= 0 && i >= 0; j--) {
                    if(checkBoxButtonMatchCase.isSelected() && Character.toLowerCase(str.charAt(j)) == Character.toLowerCase(text.charAt(i)) || str.charAt(j) == text.charAt(i) && !checkBoxButtonMatchCase.isSelected()) {
                        i--;
                        if(j == 0) {
                            textArea.setSelectionStart(i + 1);
                            textArea.setSelectionEnd(i + str.length() + 1);
                            hasFound = true;
                            break;
                        }
                    } else {
                        i = temp;
                        break;
                    }
                }
                if(hasFound)
                    break;
            }
        }

        if (radioButtonDown.isSelected()) {
            startIndex += str.length();
            for (int i = startIndex; i < text.length(); i++) {
                int temp = i;
                for(int j = 0; j < str.length() && i < text.length(); j++) {
                    if(checkBoxButtonMatchCase.isSelected() && Character.toLowerCase(str.charAt(j)) == Character.toLowerCase(text.charAt(i)) || str.charAt(j) == text.charAt(i) && !checkBoxButtonMatchCase.isSelected()) {
                        i++;
                        if(j == str.length() - 1) {
                            textArea.setSelectionStart(i - str.length());
                            textArea.setSelectionEnd(i);
                            hasFound = true;
                            break;
                        }
                    } else {
                        i = temp;
                        break;
                    }
                }
                if(hasFound)
                    break;
            }
        }
    }
}
