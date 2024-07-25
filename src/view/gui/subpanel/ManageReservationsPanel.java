package src.view.gui.subpanel;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import src.view.gui.component.StyledButton;


public class ManageReservationsPanel extends ViewReservationsPanel {

    private StyledButton removeButton = new StyledButton("Remove reservation");

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
