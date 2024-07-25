package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import src.controller.gui.ReservationListListener;
import src.view.gui.component.ReservationListPanel;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledPanel;
import src.view.gui.component.StyledScrollPane;

public class ViewReservationsPanel extends StyledPanel {
    private ReservationListPanel reservationListPanel;
    private StyledHTMLPane reservationDataComponent;
    protected StyledPanel reservationDataPanel;
    protected StyledPanel outer;

    public ViewReservationsPanel() {
        this.setLayout(new BorderLayout());

        reservationDataPanel = new StyledPanel();
        reservationDataPanel.setLayout(new BorderLayout());

        this.outer = new StyledPanel();
        this.outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));

        this.reservationListPanel = new ReservationListPanel(200);
        this.reservationDataComponent = new StyledHTMLPane();

        this.reservationDataPanel.add(
                this.reservationDataComponent,
                BorderLayout.NORTH);

        StyledScrollPane scrollPane = new StyledScrollPane(
                reservationDataPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.outer.add(scrollPane);

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
