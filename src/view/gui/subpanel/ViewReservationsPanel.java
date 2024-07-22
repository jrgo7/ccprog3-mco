package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.view.gui.component.ReservationListPanel;

public class ViewReservationsPanel extends JPanel {
    private ReservationListPanel reservationListPanel;

    public ViewReservationsPanel() {
        this.setLayout(new BorderLayout());

        this.reservationListPanel = new ReservationListPanel(200);
        this.add(this.reservationListPanel, BorderLayout.WEST);
    }

    public void updateReservationList(ArrayList<String> data) {
        this.reservationListPanel.setList(data);
    }

    public int getSelectedIndex() {
        return this.reservationListPanel.getSelectedIndex();
    }
}
