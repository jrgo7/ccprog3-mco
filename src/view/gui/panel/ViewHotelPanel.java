package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JTable;
 import src.controller.gui.HotelAvailabilityCalendarListener;
import src.controller.gui.ReservationListListener;
import src.controller.gui.RoomListListener;
import src.model.Hotel;
import src.view.gui.component.StyledHTMLPane;
import src.view.gui.component.StyledPanel;
import src.view.gui.component.StyledTabbedPane;
import src.view.gui.subpanel.ViewAvailabilityPanel;
import src.view.gui.subpanel.ViewReservationsPanel;
import src.view.gui.subpanel.ViewRoomPanel;

/** Represents the View Hotel panel. */
public class ViewHotelPanel extends StyledPanel {
    /** The {@link StyledHTMLPane} containing {@link Hotel} data. */
    private StyledHTMLPane hotelDataComponent;

    /** A {@link StyledTabbedPane} containing subpanels. */
    private StyledTabbedPane subpanels;

    /** The View Availability subpanel tied to this panel. */
    private ViewAvailabilityPanel viewAvailabilitySubpanel;

    private ViewRoomPanel viewRoomSubPanel;

    private ViewReservationsPanel viewReservationsSubPanel;

    public ViewHotelPanel() {
        this.setLayout(new BorderLayout());

        this.hotelDataComponent = new StyledHTMLPane("<h1>Hotels</h1>");
        this.add(this.hotelDataComponent, BorderLayout.NORTH);

        this.subpanels = new StyledTabbedPane();

        this.viewAvailabilitySubpanel = new ViewAvailabilityPanel();
        this.subpanels.add("Check availability", this.viewAvailabilitySubpanel);

        this.viewRoomSubPanel = new ViewRoomPanel();
        this.subpanels.add("Check rooms", viewRoomSubPanel);

        this.viewReservationsSubPanel = new ViewReservationsPanel();
        this.subpanels.add("Check reservations", viewReservationsSubPanel);

        this.add(subpanels, BorderLayout.CENTER);
    }

    public void setRoomListListener(RoomListListener roomListListener) {
        this.viewRoomSubPanel.setRoomListListener(roomListListener);
    }

    public void updateRoomData(String data, ArrayList<Integer> availableDates) {
        this.viewRoomSubPanel.updateRoomData(data, availableDates);
    }

    /**
     * Updates the {@link StyledHTMLPane} containing the hotel data.
     * 
     * @param text The hotel data string to use
     */
    public void updateHotelData(String text) {
        this.hotelDataComponent.setText(text);
    }

    /* Cover methods for the View Availability panel */

    /**
     * Sets the text contained in the availability data for the hotel.
     * 
     * @param text The availability data string to set
     * @see ViewAvailabilityPanel#updateAvailability(String)
     */
    public void updateAvailability(String text) {
        this.viewAvailabilitySubpanel.updateAvailability(text);
    }

    /**
     * {@return the index of the row on where a given point lies on the
     * availability calendar}
     * 
     * @param point The point to capture in the table
     * @see JTable#rowAtPoint(Point)
     * @see ViewAvailabilityPanel#getCalendarRowAtPoint(Point)
     */
    public int getCalendarRowAtPoint(Point point) {
        return this.viewAvailabilitySubpanel.getCalendarRowAtPoint(point);
    }

    /**
     * {@return the index of the column on where a given point lies on the
     * availability calendar}
     * 
     * @param point The point to capture in the table
     * @see JTable#columnAtPoint(Point)
     * @see ViewAvailabilityPanel#getCalendarRowAtPoint(Point)
     */
    public int getCalendarColAtPoint(Point point) {
        return this.viewAvailabilitySubpanel.getCalendarColAtPoint(point);
    }

    public void resetCalendarSelection() {
        this.viewAvailabilitySubpanel.resetCalendarSelection();
    }

    /**
     * Sets a listener for the calendar in the View Availability subpanel.
     * 
     * @param availabilityCalendarListener The listener to assign to the
     *                                     calendar
     * @see ViewAvailabilityPanel#setListener(HotelAvailabilityCalendarListener)
     */
    public void setCalendarListener(
            HotelAvailabilityCalendarListener availabilityCalendarListener) {
        this.viewAvailabilitySubpanel.setListener(availabilityCalendarListener);
    }

    /** {@return the index of the selected subpanel} */
    public int getSelectedSubpanelIndex() {
        return this.subpanels.getSelectedIndex();
    }

    public void updateRoomList(ArrayList<String> data) {
        this.viewRoomSubPanel.updateRoomList(data);
    }

    public int getViewRoomSelectedIndex() {
        return this.viewRoomSubPanel.getViewRoomSelectedIndex();
    }

    public void resetRoomListSelection() {
        this.viewRoomSubPanel.resetRoomListSelection();
    }

    // Reservation

    public void updateReservationList(ArrayList<String> data) {
        this.viewReservationsSubPanel.updateReservationList(data);
    }

    public int getViewReservationSelectedIndex() {
        return this.viewReservationsSubPanel.getSelectedIndex();
    }

    public void updateReservationData(String data) {
        this.viewReservationsSubPanel.updateReservationData(data);
    }

    public void setReservationListener(ReservationListListener listener) {
        this.viewReservationsSubPanel.setListener(listener);
    }

    public void setReservationVisible(boolean visible){
        this.viewReservationsSubPanel.setWrapperVisible(visible);
    }
}
