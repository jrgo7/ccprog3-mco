package src.view.gui.component;

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
        this.setFont(FontCollection.SEGOE_UI_BODY);
    }
}
