package src.view.gui.component;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

/** A styled {@link JTabbedPane}. */
public class StyledTabbedPane extends JTabbedPane {
    /** Instantiate this class. */
    public StyledTabbedPane() {
        super();
        this.setFont(FontCollection.SEGOE_UI_BODY);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }

    /**
     * Prepend {@code panel} with header {@code text},
     * and add it as a tab entry to this tabbed pane.
     * 
     * @param text
     * @param panel
     */
    public void addTab(String text, StyledPanel panel) {
        super.add(text, GUIFactory.createTabContainer(text, panel));
    }
}
