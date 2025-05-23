package src.view.gui.component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Extends the functionality of {@link PlainDocument} to allow for decimal real
 * number input.
 */
public class DecimalDocument extends PlainDocument {
    /** Initializes the document. */
    public DecimalDocument() {
        super();
    }

    /** {@inheritDoc} */
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