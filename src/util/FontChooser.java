package util;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

import java.awt.event.*;

public class FontChooser implements ActionListener{
    //JFrame f;
    JTextArea textArea;
    JList<String> font, fontStyle, fontSize;
    JButton ok, cancel;
    JLabel exampleLabel;
    JPanel centerPanel, southPanel, topPanel, panel;
    JDialog dialog = null;

    public FontChooser(JTextArea textArea) {
        this.textArea = textArea;

        initTopPanel();
        initCenterPanel();
        initSouthPanel();

        ok.addActionListener(this);
        cancel.addActionListener(this);
        panel = new JPanel(new BorderLayout());
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        addActionListenersToScrollPanes();
    }

    private Font valueFontChange() {
        int setFontSize = Integer.parseInt(fontSize.getSelectedValue());
        int setFontStyle = fontStyle.getSelectedIndex();
        String setFont = font.getSelectedValue();

        Font newFont = switch (setFontStyle) {
            case 0 -> new Font(setFont, Font.PLAIN, setFontSize);
            case 1 -> new Font(setFont, Font.ITALIC, setFontSize);
            case 2 -> new Font(setFont, Font.BOLD, setFontSize);
            case 3 -> new Font(setFont, Font.BOLD + Font.ITALIC, setFontSize);
            default -> throw new IllegalStateException("Unexpected value: " + setFontStyle);
        };

        return newFont;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ok) {
            Font newFont = valueFontChange();
            textArea.setFont(newFont);
        }
        dialog.dispose();
    }

    public void showDialog(Component parent) {
        Frame owner;
        if(parent instanceof Frame)
            owner = (Frame) parent;
        else
            owner=(Frame) SwingUtilities.getAncestorOfClass(Frame.class,parent);

        if(dialog == null || dialog.getOwner() != owner) {
            dialog = new JDialog(owner,false);
            dialog.getContentPane().add(panel);
            dialog.getRootPane().setDefaultButton(cancel);
        }

        dialog.setSize(450,350);
        dialog.setTitle("Font Chooser");
        dialog.setVisible(true);
    }

    private void initTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,3));

        topPanel.add(new JLabel("Font"));
        topPanel.add(new JLabel("Font Style"));
        topPanel.add(new JLabel("Font Size"));
    }

    private void initCenterPanel() {
        initJLists();

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 3));

        centerPanel.add(new JScrollPane(font));
        centerPanel.add(new JScrollPane(fontStyle));
        centerPanel.add(new JScrollPane(fontSize));
        centerPanel.add(new JLabel(" "));

        JPanel direction = new JPanel();
        direction.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        "Example"));
        direction.setLayout(new GridLayout(1,1));

        JScrollPane scrollPane = new JScrollPane();
        exampleLabel = new JLabel("AbCdXyZ",SwingConstants.CENTER);
        scrollPane.setViewportView(exampleLabel);

        direction.add(scrollPane);

        centerPanel.add(direction);
        centerPanel.add(new JLabel(" "));
    }

    private void initJLists() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String[] fontStyles = {"Regular", "Italic", "Bold", "Bold Italic"};
        String[] fontSizes = {"10","12","14","16","18","20","22","24","26","28","30","32","34","36","38","40"};

        font = new JList<>(fonts);
        font.setSelectedIndex(0);

        fontStyle = new JList<>(fontStyles);
        fontStyle.setSelectedIndex(0);

        fontSize = new JList<>(fontSizes);
        fontSize.setSelectedIndex(0);
    }

    private void initSouthPanel() {
        ok = new JButton("OK");
        cancel = new JButton("Cancel");
        southPanel = new JPanel(new FlowLayout());
        southPanel.add(new JLabel(" "));
        southPanel.add(new JLabel(" "));
        southPanel.add(new JLabel(" "));
        southPanel.add(ok);
        southPanel.add(cancel);
        cancel.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void addActionListenersToScrollPanes() {
        font.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                Font newfont = valueFontChange();
                exampleLabel.setFont(newfont);
            }
        });
        fontStyle.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                Font newfont = valueFontChange();
                exampleLabel.setFont(newfont);
            }
        });
        fontSize.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                Font newfont = valueFontChange();
                exampleLabel.setFont(newfont);
            }
        });
    }
}
