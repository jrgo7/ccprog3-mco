package src.view.gui.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JRadioButton;

/** A styled {@link JRadioButton}. */
public class StyledRadioButton extends JRadioButton {
    /**
     * Instantiate a {@link StyledRadioButton} with text.
     * 
     * @param text the text associated with this radio button.
     */
    public StyledRadioButton(String text) {
        super(text);
        this.setFont(FontCollection.SEGOE_UI_BODY);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }
}
