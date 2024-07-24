package src.view.gui.component;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/** A styled {@link JLabel} */
public class StyledLabel extends JLabel {
    /** Instanatiate this class. */
    public StyledLabel() {
        super();
        setStyle(FontCollection.SEGOE_UI_BODY);
    }

    /**
     * Instantiate this class with some initial text.
     * 
     * @param text the text to display
     */
    public StyledLabel(String text) {
        super(text);
        setStyle(FontCollection.SEGOE_UI_BODY);
    }

    /**
     * Instantiate this class with some initial text and a set font.
     * 
     * @param text the text to display
     * @param font the font to use
     */
    public StyledLabel(String text, Font font) {
        super(text);
        setStyle(font);
    }

    /**
     * Set common style attributes.
     * 
     * @param font the font to use
     */
    private void setStyle(Font font) {
        this.setFont(font);
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }
}
