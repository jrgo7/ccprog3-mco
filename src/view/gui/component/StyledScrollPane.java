package src.view.gui.component;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class StyledScrollPane extends JScrollPane {
    
    /**
     * 
     * @param component
     * @param VSP
     * @param HSP
     */
    public StyledScrollPane(Component component) {
        super(component);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * 
     * @param component
     * @param vsbPolicy
     * @param hsbPolicy
     */
    public StyledScrollPane(Component component, int vsbPolicy, int hsbPolicy) {
        super(component, vsbPolicy, hsbPolicy);
        this.setBorder(BorderFactory.createEmptyBorder());
    }

}
