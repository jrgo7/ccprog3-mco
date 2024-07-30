package src.view.gui.component;

import javax.swing.JTextField;

/** Represents a styled {@link JTextField}. */
public class StyledTextField extends JTextField {
    /** Initializes the text field with the default body font */
    public StyledTextField() {
        super();
        this.setFont(FontCollection.SEGOE_UI_BODY);
    }
}
