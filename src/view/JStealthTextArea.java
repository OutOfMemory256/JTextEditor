package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class JStealthTextArea extends JTextArea {
    private final StringBuilder originalText = new StringBuilder();
    private boolean stealthModeEnabled = true;
    private String currentHoverWord;
    private Rectangle wordBounds;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (stealthModeEnabled && currentHoverWord != null && wordBounds != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            try {
                int y = wordBounds.y + getFontMetrics(getFont()).getAscent();
                g2.setColor(getBackground());

                for(int i = -3; i < 3; i++) {
                    for(int j = -3; j < 3; j++) {
                        g2.drawString(currentHoverWord.replaceAll("\\S", "*"), wordBounds.x + i, y + j);
                    }
                }

                g2.setColor(Color.BLACK);
                g2.drawString(currentHoverWord, wordBounds.x, y);
            } finally {
                g2.dispose();
            }
        }
    }

    public JStealthTextArea() {
       setFont(new Font("Monospaced", Font.PLAIN, 20));
        ((AbstractDocument) getDocument()).setDocumentFilter(new StealthDocumentFilter());

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                updateHoverWord(e.getPoint());
            }
        });

        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { syncOriginalText(); }
            public void removeUpdate(DocumentEvent e) { syncOriginalText(); }
            public void changedUpdate(DocumentEvent e) {}
        });
    }

    public void updateTextDisplay() {
        if (stealthModeEnabled) {
            setText(maskString(originalText.toString()));
            ((AbstractDocument) getDocument()).setDocumentFilter(new StealthDocumentFilter());
        } else {
            ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter());
            setText(originalText.toString());
        }
    }

    private void syncOriginalText() {
        if (!stealthModeEnabled) {
            originalText.setLength(0);
            originalText.append(getText());
        }
    }

    private void updateHoverWord(Point pt) {
        if (!stealthModeEnabled) {
            currentHoverWord = null;
            return;
        }

        try {
            int pos = viewToModel2D(pt);
            if (pos < 0 || pos >= originalText.length()) {
                currentHoverWord = null;
                repaint();
                return;
            }

            int start = pos;
            int end = pos;
            while (start > 0 && !Character.isWhitespace(originalText.charAt(start-1))) start--;
            while (end < originalText.length() && !Character.isWhitespace(originalText.charAt(end))) end++;

            currentHoverWord = originalText.substring(start, end);
            wordBounds = modelToView2D(start).getBounds();
            wordBounds.add(modelToView2D(end - 1).getBounds());
            repaint();
        } catch (BadLocationException ex) {
            currentHoverWord = null;
            repaint();
        }
    }

    private String maskString(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            sb.append(Character.isLetter(c) ? '*' : c);
        }
        return sb.toString();
    }

    private class StealthDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset,
                                 String text, AttributeSet attr)
                throws BadLocationException {
            String masked = maskString(text);
            originalText.insert(offset, text);
            super.insertString(fb, offset, masked, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length,
                            String text, AttributeSet attrs)
                throws BadLocationException {

            String masked = maskString(text);
            originalText.replace(offset, offset + length, text);
            super.replace(fb, offset, length, masked, attrs);
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            originalText.delete(offset, offset + length);
            super.remove(fb, offset, length);
        }
    }

    public boolean isStealthModeEnabled() {
        return stealthModeEnabled;
    }

    public void setStealthModeEnabled(boolean stealthModeEnabled) {
        this.stealthModeEnabled = stealthModeEnabled;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("сигачед гнида");
        frame.setSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        JStealthTextArea stealthTextArea = new JStealthTextArea();
        frame.add(stealthTextArea, BorderLayout.CENTER);

        JButton toggleButton = new JButton("Stealth Mode: ON");
        toggleButton.addActionListener(e -> {
            stealthTextArea.setStealthModeEnabled(!stealthTextArea.isStealthModeEnabled());
            toggleButton.setText("Stealth Mode: " + (stealthTextArea.isStealthModeEnabled() ? "ON" : "OFF"));
            stealthTextArea.updateTextDisplay();
        });
        frame.add(toggleButton, BorderLayout.NORTH);
        frame.add(new JScrollPane(stealthTextArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
