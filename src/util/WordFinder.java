package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WordFinder implements ActionListener {
    private JFrame frame = new JFrame();
    private JTextField textField = new JTextField();
    private JTextArea textArea;
    private ButtonGroup radioGroup = new ButtonGroup();
    JRadioButton buttonUp = new JRadioButton("Up");
    JRadioButton buttonDown = new JRadioButton("Down");
    JCheckBox checkBoxButtonMatchCase = new JCheckBox("Match case");
    JCheckBox checkBoxButtonWrapAround = new JCheckBox("Wrap around");

    public WordFinder(JTextArea textArea) {
        this.textArea = textArea;
        init();
    }

    public static void main(String[] args) {
        WordFinder wordFinder = new WordFinder(new JTextArea("NIGGEA"));
    }

    private void init() {
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

        radioGroup.add(buttonUp);
        radioGroup.add(buttonDown);
        radioButtonsPanel.add(buttonUp);
        radioButtonsPanel.add(buttonDown);

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
        if (buttonUp.isSelected()) {
            startIndex -= 1;
            int possibleSelectionStart;
            int possibleSelectionEnd;
            for (int i = startIndex; i >= 0; i--) {
                boolean hasFound = false;
                if(checkBoxButtonMatchCase.isSelected() && Character.toLowerCase(str.charAt(str.length() - 1)) != Character.toLowerCase(text.charAt(i)) || str.charAt(str.length() - 1) != text.charAt(i) && !checkBoxButtonMatchCase.isSelected())
                    continue;
                int temp = i;
                for(int j = str.length() - 1; j >= 0 && i >= 0; j--) {
                    if(checkBoxButtonMatchCase.isSelected() && Character.toLowerCase(str.charAt(j)) != Character.toLowerCase(text.charAt(i)) || str.charAt(j) == text.charAt(i) && !checkBoxButtonMatchCase.isSelected()) {
                        i--;
                        if(j == 0) {
                            possibleSelectionStart = i;
                            possibleSelectionEnd = i + str.length();
                            textArea.setSelectionStart(possibleSelectionStart + 1);
                            textArea.setSelectionEnd(possibleSelectionEnd + 1);
                            System.out.println(i + " " + possibleSelectionEnd);
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
        } else if (buttonDown.isSelected()) {
            int possibleSelectionStart;
            int possibleSelectionEnd;
            startIndex += str.length();
            for (int i = startIndex; i < text.length(); i++) {
                boolean hasFound = false;
                if(checkBoxButtonMatchCase.isSelected() && Character.toLowerCase(str.charAt(0)) != Character.toLowerCase(text.charAt(i)) || str.charAt(0) != text.charAt(i) && !checkBoxButtonMatchCase.isSelected())
                    continue;
                int temp = i;
                for(int j = 0; j < str.length() && i < text.length(); j++) {
                    if(checkBoxButtonMatchCase.isSelected() && Character.toLowerCase(str.charAt(j)) == Character.toLowerCase(text.charAt(i)) || str.charAt(j) == text.charAt(i) && !checkBoxButtonMatchCase.isSelected()) {
                        i++;
                        if(j == str.length() - 1) {
                            possibleSelectionStart = i - str.length();
                            possibleSelectionEnd = i;
                            textArea.setSelectionStart(possibleSelectionStart);
                            textArea.setSelectionEnd(possibleSelectionEnd);
                            System.out.println(possibleSelectionStart + " " + possibleSelectionEnd);
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
