package src.view.gui.component;

import javax.swing.JButton;

public class StyledButton extends JButton {
    public StyledButton(String text) {
        super(text);
        this.setFont(FontCollection.SEGOE_UI_BODY);
    }
}
