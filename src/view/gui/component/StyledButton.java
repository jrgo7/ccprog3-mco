package src.view.gui.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

/** A styled {@link JButton}. */
public class StyledButton extends JButton {
    /**
     * Instantiates a StyledButton.
     *
     * @param text text to display on this component
     */
    public StyledButton(String text) {
        super(text);
        this.setStyle(FontCollection.SEGOE_UI_BODY, Color.BLACK);
    }

    /**
     * Instantiates a StyledButton with the specified text and color.
     *
     * @param text  text to display on this component
     * @param color the color to use for the text
     */
    public StyledButton(String text, Color color) {
        super(text);
        this.setStyle(FontCollection.SEGOE_UI_BODY, color);
    }

    /**
     * Sets the style for this button.
     *
     * @param font  the font to use for the text
     * @param color the color to use for the text
     */
    public void setStyle(Font font, Color color) {
        this.setBackground(Color.WHITE);
        this.setForeground(color);
        this.setFont(font);
    }

}
