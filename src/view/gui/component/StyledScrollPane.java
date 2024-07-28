package src.view.gui.component;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

/** A styled {@link JScrollPane}. */
public class StyledScrollPane extends JScrollPane {
    /**
     * Instantiate this class.
     * 
     * @param component A component to envelop this scroll pane onto
     */
    public StyledScrollPane(Component component) {
        super(component);
        this.setStyle();
    }

    /**
     * Instantiate this class with vertical and horizontal scroll bar policies.
     * Check {@link JScrollPane#setVerticalScrollBarPolicy(int)} and
     * {@link JScrollPane#setHorizontalScrollBarPolicy(int)} for a list of
     * policies.
     * 
     * @param component A component to envelop this scroll pane onto
     * @param vsbPolicy Vertical scroll bar policy
     * @param hsbPolicy Horizontal scroll bar policy
     */
    public StyledScrollPane(Component component, int vsbPolicy, int hsbPolicy) {
        super(component, vsbPolicy, hsbPolicy);
        this.setStyle();
    }

    /**
     * Assign common style attributes.
     */
    private void setStyle() {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.getVerticalScrollBar()
                .setBackground(ColorCollection.BACKGROUND_COMPLEMENT);
        this.getHorizontalScrollBar()
                .setBackground(ColorCollection.BACKGROUND_COMPLEMENT);
        this.getVerticalScrollBar().setUnitIncrement(100);
        this.getHorizontalScrollBar().setUnitIncrement(100);
    }

}
