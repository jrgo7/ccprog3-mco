package src.view.gui.component;

/** Extends {@link ListPanel} for viewing reservations. */
public class ReservationListPanel extends ListPanel {
    /**
     * Initializes the panel.
     * 
     * @param width The width of the panel
     */
    public ReservationListPanel(int width) {
        super("Reservations", width, FontCollection.SEGOE_UI_SUBTITLE);
    }
}
