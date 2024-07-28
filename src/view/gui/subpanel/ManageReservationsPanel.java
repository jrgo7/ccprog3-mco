package src.view.gui.subpanel;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import src.view.gui.component.StyledButton;
import src.view.gui.component.GUIFactory;

/**
 * Represents the Manage Reservations subpanel under Manage Rooms. Extends the
 * functionality of {@link ViewReservationsPanel}.
 */
public class ManageReservationsPanel extends ViewReservationsPanel {
    /** Button for removing a reservation. */
    private StyledButton removeButton = GUIFactory
            .createDestructiveButton("Remove reservation");

    /** Initializes the subpanel */
    public ManageReservationsPanel() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        wrapper.add(removeButton);
        this.outer.add(wrapper);
    }

    /**
     * Sets a listener for the remove reservation button
     * 
     * @param listener the listener to set
     */
    public void setRemoveButtonListener(ActionListener listener) {
        this.removeButton.addActionListener(listener);
    }
}
