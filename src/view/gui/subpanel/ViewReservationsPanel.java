package src.view.gui.subpanel;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import src.controller.gui.ReservationListListener;
import src.view.gui.component.GUIFactory;
import src.view.gui.component.ReservationListPanel;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledPanel;

/** Represents the View Reservations subpanel under a {@link ViewRoomPanel} */
public class ViewReservationsPanel extends StyledPanel {
    /** The panel containing the list of reservations. */
    private ReservationListPanel reservationListPanel;

    /** The panel displaying information about the selected reservation. */
    private StyledHTMLPane reservationDataComponent;

    /** The panel wrapping around the panel displaying reservation data. */
    protected StyledPanel reservationDataPanel;

    /**
     * The panel wrapping around all other components. Hidden when no selection
     * is made.
     */
    protected StyledPanel outer;

    /** Initializes the panel. */
    public ViewReservationsPanel() {
        this.setLayout(new BorderLayout());

        this.outer = new StyledPanel();
        this.outer.setLayout(new BoxLayout(outer, BoxLayout.Y_AXIS));

        this.reservationListPanel = new ReservationListPanel(200);
        this.reservationDataComponent = new StyledHTMLPane();

        reservationDataPanel = GUIFactory
                .createStyledHTMLPaneContainer(reservationDataComponent);
        this.outer.add(reservationDataPanel);

        this.add(this.reservationListPanel, BorderLayout.WEST);
        this.add(outer, BorderLayout.CENTER);
        this.setWrapperVisible(false);
    }

    /**
     * Sets the reservation list with the given data.
     * 
     * @param data the list reservations to set
     */
    public void setReservationList(ArrayList<String> data) {
        this.reservationListPanel.setList(data);
    }

    /**
     * Sets the text in the reservation data panel.
     * 
     * @param data the text to set
     */
    public void setReservationData(String data) {
        this.reservationDataComponent.setText(data);
    }

    /** {@return the index of the reservation list selection} */
    public int getSelectedIndex() {
        return this.reservationListPanel.getSelectedIndex();
    }

    /**
     * Sets the listener for reservation list.
     * 
     * @param listener the listener to set
     */
    public void setListener(ReservationListListener listener) {
        this.reservationListPanel.setListener(listener);
    }

    /**
     * Sets the visibility of the right-hand panel.
     * 
     * @param visible {@code true} to display the panel, {@code false} otherwise
     */
    public void setWrapperVisible(boolean visible) {
        this.outer.setVisible(visible);
    }

    /** Clears the reservation list selection */
    public void clearSelectedIndex() {
        this.reservationListPanel.clearSelection();
    }
}
