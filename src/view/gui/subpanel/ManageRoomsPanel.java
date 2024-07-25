package src.view.gui.subpanel;

import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import src.view.gui.component.StyledButton;
import src.view.gui.component.StyledButtonFactory;
import src.view.gui.component.StyledPanel;

public class ManageRoomsPanel extends ViewRoomPanel {
    private StyledButton removeButton = StyledButtonFactory.createDestructiveButton("Remove room");

    public ManageRoomsPanel() {
        super(true);
        StyledPanel wrapper = new StyledPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        wrapper.add(removeButton);
        this.outer.add(wrapper);
    }

    public void setRemoveButtonListener(ActionListener listener) {
        this.removeButton.addActionListener(listener);
    }
}
