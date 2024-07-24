package src.view.gui.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JRadioButton;

public class StyledRadioButton extends JRadioButton {
    public StyledRadioButton(String text) {
        super(text);
        this.setFont(FontCollection.SEGOE_UI_BODY);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }
}
