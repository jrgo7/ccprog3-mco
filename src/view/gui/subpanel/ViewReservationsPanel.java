package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import src.controller.gui.ReservationListListener;
import src.view.gui.component.ReservationListPanel;

public class ViewReservationsPanel extends JPanel {
    private ReservationListPanel reservationListPanel;

    private JEditorPane reservationDataComponent;

    protected JPanel reservationDataPanel;

    protected JPanel outer;

    public ViewReservationsPanel() {
        this.setLayout(new BorderLayout());

        this.reservationDataPanel = new JPanel();
        this.reservationDataPanel.setLayout(new BorderLayout());

        this.outer = new JPanel();
        this.outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));

        this.reservationListPanel = new ReservationListPanel(200);
        this.reservationDataComponent = new JEditorPane("text/html", "<p></p>");
        this.reservationDataComponent.setEditable(false);

        this.reservationDataPanel.add(this.reservationDataComponent,
                BorderLayout.NORTH);

        this.outer.add(new JScrollPane(reservationDataPanel));

        this.add(this.reservationListPanel, BorderLayout.WEST);
        this.add(outer, BorderLayout.CENTER);

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

    public void setWrapperVisible(boolean visible) {
        this.outer.setVisible(visible);
    }
}
