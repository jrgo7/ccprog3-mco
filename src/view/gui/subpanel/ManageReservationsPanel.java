package src.view.gui.subpanel;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.controller.gui.RoomListListener;

public class ManageReservationsPanel extends ViewReservationsPanel {

    private JButton removeButton = new JButton("Remove reservation");

    public ManageReservationsPanel() {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
        wrapper.add(removeButton);
        this.outer.add(wrapper);
    }

    public void setRemoveButtonListener(ActionListener listener) {
        this.removeButton.addActionListener(listener);
    }
}
