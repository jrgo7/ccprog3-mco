package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.view.gui.component.StyledHTMLPane; 
import src.controller.gui.ReservationListListener;
import src.view.gui.component.ReservationListPanel;

public class ViewReservationsPanel extends JPanel {
    private ReservationListPanel reservationListPanel;

    private StyledHTMLPane reservationDataComponent;

    public ViewReservationsPanel() {
        this.setLayout(new BorderLayout());

        JPanel reservationDataPanel = new JPanel();
        reservationDataPanel.setLayout(new BorderLayout());


        this.reservationListPanel = new ReservationListPanel(200);
        this.reservationDataComponent = new StyledHTMLPane();
        this.reservationDataComponent.setEditable(false);

        reservationDataPanel.add(this.reservationDataComponent, BorderLayout.NORTH);

        this.add(this.reservationListPanel, BorderLayout.WEST);
        this.add(reservationDataPanel, BorderLayout.CENTER);
    }

    public void updateReservationList(ArrayList<String> data) {
        this.reservationListPanel.setList(data);
    }

    public void updateReservationData(String data) {
        this.reservationDataComponent.setText(data);
    }

    public int getSelectedIndex() {
        return this.reservationListPanel.getSelectedIndex();
    }

    public void setListener(ReservationListListener listener) {
        this.reservationListPanel.setListener(listener);
    }
}
