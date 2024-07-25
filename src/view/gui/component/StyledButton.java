package src.view.gui.component;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/** A styled {@link JButton}. */
public class StyledButton extends JButton {
    /**
     * Instantiate this class.
     * 
     * @param text text to display on this component
     */
    public StyledButton(String text) {
        super(text);
        this.setStyle(FontCollection.SEGOE_UI_BODY, Color.BLACK);
    }

    public StyledButton(String text, Color color) {
        super(text);
        this.setStyle(FontCollection.SEGOE_UI_BODY, color);
    }

    public void setStyle(Font font, Color color) {
        this.setBackground(Color.WHITE);
        this.setForeground(color);
        this.setFont(font);
    }
}
