package src.view.gui.panel;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;

import src.controller.gui.HotelAvailabilityCalendarListener;
import src.controller.gui.ReservationListListener;
import src.controller.gui.RoomListListener;
import src.model.Hotel;
import src.model.ReservationSystem;
import src.model.Room;
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

    /** Represents the View Availability subpanel. */
    private ViewAvailabilityPanel viewAvailabilitySubpanel;

    /** Represents the View Room subpanel. */
    private ViewRoomPanel viewRoomSubPanel;

    /** Represents the View Reservations subpanel. */
    private ViewReservationsPanel viewReservationsSubPanel;

    /** Initialies the hotel data pane and the subpanels. */
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

    /**
     * Sets a listener for the calendar in the View Availability subpanel.
     * 
     * @param availabilityCalendarListener the listener to set
     * @see ViewAvailabilityPanel#setListener(HotelAvailabilityCalendarListener)
     */
    public void setCalendarListener(
            HotelAvailabilityCalendarListener availabilityCalendarListener) {
        this.viewAvailabilitySubpanel.setListener(availabilityCalendarListener);
    }

    /**
     * Sets a listener for the reservation list in the View Reservations
     * subpanel.
     * 
     * @param listener the listener to set
     */
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
     * {@return the row index within the availability calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see ViewAvailabilityPanel#getCalendarRowAtPoint(Point)
     */
    public int getAvailabilityCalendarRowAtPoint(Point point) {
        return this.viewAvailabilitySubpanel.getCalendarRowAtPoint(point);
    }

    /**
     * {@return the column index within the availability calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see ViewAvailabilityPanel#getCalendarColAtPoint(Point)
     */
    public int getAvailabilityCalendarColAtPoint(Point point) {
        return this.viewAvailabilitySubpanel.getCalendarColAtPoint(point);
    }

    /**
     * Clears the availability calendar selection.
     * 
     * @see ViewAvailabilityPanel#clearCalendarSelection
     */
    public void clearAvailabilityCalendarSelection() {
        this.viewAvailabilitySubpanel.clearCalendarSelection();
    }

    /* View rooms subpanel */

    /**
     * Sets the room data panel with the given data and available dates.
     *
     * @param data           the room data string to set
     * @param availableDates an {@link ArrayList} of available dates. Usually
     *                       obtained with {@link Room#getAvailableDates()}
     * @see ViewRoomSubPanel#setRoomData
     */
    public void setRoomData(String data, ArrayList<Integer> availableDates) {
        this.viewRoomSubPanel.setRoomData(data, availableDates);
    }

    /**
     * Sets the room list with the given data.
     *
     * @param data an {@link ArrayList} of room names to set
     * @see ViewRoomSubPanel#setRoomList
     */
    public void setRoomList(ArrayList<String> data) {
        this.viewRoomSubPanel.setRoomList(data);
    }

    /**
     * {@return the index of the selected room}
     * 
     * @see ViewRoomSubPanel#getSelectedRoomIndex
     */
    public int getSelectedRoomIndex() {
        return this.viewRoomSubPanel.getSelectedRoomIndex();
    }

    /**
     * Clears the room list selection.
     *
     * @see ViewRoomSubPanel#clearRoomListSelection
     */
    public void clearRoomListSelection() {
        this.viewRoomSubPanel.clearRoomListSelection();
    }

    /**
     * Sets the selected room index.
     *
     * @param index the index of the selected room
     * @see ViewRoomSubPanel#setSelectedIndex
     */
    public void setSelectedRoomIndex(int index) {
        this.viewRoomSubPanel.setSelectedIndex(index);
    }

    /**
     * Sets the visibility of the room data panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see ViewRoomSubPanel#setWrapperVisible
     */
    public void setRoomDataVisible(boolean visible) {
        this.viewRoomSubPanel.setWrapperVisible(visible);
    }

    /* View reservations subpanel */

    /**
     * Sets the reservation list with the given data.
     *
     * @param data the array containing string representations of active
     *             reservations. Usually obtained through
     *             {@link ReservationSystem#getReservationNames(int)}
     * @see ViewReservationsSubPanel#setReservationList
     */
    public void setReservationList(ArrayList<String> data) {
        this.viewReservationsSubPanel.setReservationList(data);
    }

    /**
     * {@return the index of the selected reservation}
     * 
     * @see ViewReservationsSubPanel#getSelectedIndex
     */
    public int getSelectedReservationIndex() {
        return this.viewReservationsSubPanel.getSelectedIndex();
    }

    /**
     * Sets the text in the reservation data panel.
     *
     * @param data the text to set
     * @see ViewReservationsSubPanel#setReservationData
     */
    public void setReservationData(String data) {
        this.viewReservationsSubPanel.setReservationData(data);
    }

    /**
     * Sets the visibility of the reservation data panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see ViewReservationsSubPanel#setWrapperVisible
     */
    public void setReservationDataVisible(boolean visible) {
        this.viewReservationsSubPanel.setWrapperVisible(visible);
    }

    /**
     * Sets the selected reservation index.
     *
     * @param index the index of the selected reservation
     * @see ViewReservationsSubPanel#setSelectedIndex
     */
    public void setSelectedReservationIndex(int index) {
        this.viewReservationsSubPanel.setSelectedIndex(index);
    }
}
