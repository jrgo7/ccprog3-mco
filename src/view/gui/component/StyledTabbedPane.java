package src.view.gui.component;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

/** A styled {@link JTabbedPane}. */
public class StyledTabbedPane extends JTabbedPane {
    /** Instantiate this class. */
    public StyledTabbedPane() {
        super();
        this.setFont(FontCollection.SEGOE_UI_BODY_LARGE);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }
}
