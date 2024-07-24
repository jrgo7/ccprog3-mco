package src.view.gui.component;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class StyledLabel extends JLabel {
    public StyledLabel() {
        super();
        setStyle(FontCollection.SEGOE_UI_BODY);
    }

    public StyledLabel(String text) {
        super(text);
        setStyle(FontCollection.SEGOE_UI_BODY);
    }

    public StyledLabel(String text, Font font) {
        super(text);
        setStyle(font);
    }

    public void setStyle(Font font) {
        this.setFont(font);
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }
}
