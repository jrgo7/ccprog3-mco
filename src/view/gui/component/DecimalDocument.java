package src.view.gui.component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class DecimalDocument extends PlainDocument {
    public DecimalDocument() {
        super();
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        if (this.getText(0, this.getLength()).contains(".")) {
            str = str.replaceAll("\\D", "");
        } else {
            str = str.replaceAll("[^\\d\\.]", "");
        }
        super.insertString(offs, str, a);
    }
}