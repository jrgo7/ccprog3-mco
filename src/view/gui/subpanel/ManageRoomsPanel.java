package src.view.gui.subpanel;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;

import src.view.gui.component.GUIFactory;
import src.view.gui.component.StyledButton;
import src.view.gui.component.StyledPanel;
import src.view.gui.panel.ManageHotelPanel;

/** Represents the Manage Rooms subpanel under a {@link ManageHotelPanel} */
public class ManageRoomsPanel extends ViewRoomPanel {
    /** The button for removing a room. */
    private StyledButton removeButton = GUIFactory
            .createDestructiveButton("Remove room");

    /** Initializes the subpanel. */
    public ManageRoomsPanel() {
        super(true);
        StyledPanel wrapper = new StyledPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        wrapper.add(removeButton);
        this.outer.add(wrapper);
    }

    /**
     * Sets the listener for the remove room button.
     * 
     * @param listener the listener to set
     */
    public void setRemoveButtonListener(ActionListener listener) {
        this.removeButton.addActionListener(listener);
    }

}
