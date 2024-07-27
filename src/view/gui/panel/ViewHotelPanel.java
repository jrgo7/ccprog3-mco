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

        this.hotelDataComponent = new StyledHTMLPane();
        this.add(this.hotelDataComponent, BorderLayout.NORTH);

        this.subpanels = new StyledTabbedPane();

        this.viewAvailabilitySubpanel = new ViewAvailabilityPanel();
        this.subpanels.addTab("Check availability", this.viewAvailabilitySubpanel);

        this.viewRoomSubPanel = new ViewRoomPanel();
        this.subpanels.addTab("Check rooms", viewRoomSubPanel);

        this.viewReservationsSubPanel = new ViewReservationsPanel();
        this.subpanels.addTab("Check reservations", viewReservationsSubPanel);

        this.add(subpanels, BorderLayout.CENTER);
    }

    public void setRoomListListener(RoomListListener roomListListener) {
        this.viewRoomSubPanel.setRoomListListener(roomListListener);
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

    public void setReservationListener(ReservationListListener listener) {
        this.viewReservationsSubPanel.setListener(listener);
    }

    /** {@return the index of the selected subpanel} */
    public int getSelectedSubpanelIndex() {
        return this.subpanels.getSelectedIndex();
    }

    /**
     * Updates the {@link StyledHTMLPane} containing the hotel data.
     * 
     * @param text The hotel data string to use
     */
    public void setHotelData(String text) {
        this.hotelDataComponent.setText(text);
    }

    /**
     * Sets the text contained in the availability data for the hotel.
     * 
     * @param text The availability data string to set
     * @see ViewAvailabilityPanel#setAvailabilityData(String)
     */
    public void setAvailabilityData(String text) {
        this.viewAvailabilitySubpanel.setAvailabilityData(text);
    }

    /**
     * {@return the index of the row on where a given point lies on the
     * availability calendar}
     * 
     * @param point The point to capture in the table
     * @see JTable#rowAtPoint(Point)
     * @see ViewAvailabilityPanel#getCalendarRowAtPoint(Point)
     */
    public int getAvailabilityCalendarRowAtPoint(Point point) {
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
    public int getAvailabilityCalendarColAtPoint(Point point) {
        return this.viewAvailabilitySubpanel.getCalendarColAtPoint(point);
    }

    public void clearAvailabilityCalendarSelection() {
        this.viewAvailabilitySubpanel.clearCalendarSelection();
    }

    /* View rooms subpanel */

    public void setRoomData(String data, ArrayList<Integer> availableDates) {
        this.viewRoomSubPanel.setRoomData(data, availableDates);
    }

    public void setRoomList(ArrayList<String> data) {
        this.viewRoomSubPanel.setRoomList(data);
    }

    public int getSelectedRoomIndex() {
        return this.viewRoomSubPanel.getSelectedRoomIndex();
    }

    public void clearRoomListSelection() {
        this.viewRoomSubPanel.clearRoomListSelection();
    }

    public void setSelectedRoomIndex(int index) {
        this.viewRoomSubPanel.setSelectedIndex(index);
    }

    /* View reservations subpanel */

    public void setReservationList(ArrayList<String> data) {
        this.viewReservationsSubPanel.setReservationList(data);
    }

    public int getSelectedReservationIndex() {
        return this.viewReservationsSubPanel.getSelectedIndex();
    }

    public void setReservationData(String data) {
        this.viewReservationsSubPanel.setReservationData(data);
    }

    public void setReservationDataVisible(boolean visible) {
        this.viewReservationsSubPanel.setWrapperVisible(visible);
    }

    public void setSelectedReservationIndex(int index) {
        this.viewReservationsSubPanel.setSelectedIndex(index);
    }

    public void setRoomDataVisible(boolean visible) {
        this.viewRoomSubPanel.setWrapperVisible(visible);
    }
}
