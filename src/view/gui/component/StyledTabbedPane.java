package src.view.gui.component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

public class StyledTabbedPane extends JTabbedPane {
    public StyledTabbedPane() {
        super();
        this.setFont(FontCollection.SEGOE_UI_SUBTITLE);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }
}
