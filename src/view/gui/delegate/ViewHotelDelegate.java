package src.view.gui.delegate;

import java.awt.Point;
import java.util.ArrayList;

import src.model.Room;
import src.view.gui.TopView;
import src.view.gui.panel.SimulateBookingPanel;
import src.view.gui.panel.ViewHotelPanel;

/**
 * Represents a delegate class that assists {@link TopView} in passing data to
 * its composite {@link ViewHotelPanel}.
 */
public class ViewHotelDelegate {
    /** The panel that this delegate communicates with. */
    private final ViewHotelPanel viewHotelPanel;

    /**
     * Initializes the panel.
     * 
     * @param viewHotelPanel the panel tied to this delegate
     */
    public ViewHotelDelegate(ViewHotelPanel viewHotelPanel) {
        this.viewHotelPanel = viewHotelPanel;
    }

    /* Hotel data panel */

    /**
     * Sets the hotel data panel text.
     *
     * @param data the data to display
     * @see ViewHotelPanel#setHotelData
     */
    public void setHotelData(String data) {
        this.viewHotelPanel.setHotelData(data);
    }

    /**
     * Sets the visibility of the room data panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see ViewHotelPanel#setRoomDataVisible
     */
    public void setRoomDataVisible(boolean visible) {
        this.viewHotelPanel.setRoomDataVisible(visible);
    }

    /**
     * Sets the text contained in the availability data for the hotel.
     *
     * @param data the data to display
     * @see ViewHotelPanel#setAvailabilityData
     */
    public void setHotelAvailability(String data) {
        this.viewHotelPanel.setAvailabilityData(data);
    }

    /* View rooms subpanel */

    /**
     * Sets the room data panel with the given data and available dates.
     *
     * @param data           the room data string to set
     * @param availableDates an {@link ArrayList} of available dates. Usually
     *                       obtained with {@link Room#getAvailableDates()}
     * @see ViewHotelPanel#setRoomData
     */
    public void setRoomData(String data, ArrayList<Integer> availableDates) {
        this.viewHotelPanel.setRoomData(data, availableDates);
    }

    /**
     * Sets the selected room index.
     *
     * @param index the index of the selected room.
     * @see ViewHotelPanel#setSelectedRoomIndex
     */
    public void setSelectedRoomIndex(int index) {
        this.viewHotelPanel.setSelectedRoomIndex(index);
    }

    /**
     * Clears the room list selection.
     *
     * @see ViewHotelPanel#clearRoomListSelection
     */
    public void clearRoomSelectedIndex() {
        this.viewHotelPanel.clearRoomListSelection();
    }

    /**
     * {@return the row index within the availability calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see ViewHotelPanel#getAvailabilityCalendarRowAtPoint
     */
    public int getAvailabilityCalendarRowFromMouse(Point point) {
        return this.viewHotelPanel.getAvailabilityCalendarRowAtPoint(point);
    }

    /**
     * {@return the column index within the availability calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see ViewHotelPanel#getAvailabilityCalendarColAtPoint
     */
    public int getAvailabilityCalendarColFromMouse(Point point) {
        return this.viewHotelPanel.getAvailabilityCalendarColAtPoint(point);
    }

    /**
     * Clears the availability calendar selection.
     *
     * @see ViewHotelPanel#clearAvailabilityCalendarSelection
     */
    public void clearAvailabilityCalendarSelection() {
        this.viewHotelPanel.clearAvailabilityCalendarSelection();
    }

    /* View reservations subpanel */

    /**
     * Sets the text in the reservation data panel.
     *
     * @param data the text to set.
     * @see ViewHotelPanel#setReservationData
     */
    public void setReservationData(String data) {
        this.viewHotelPanel.setReservationData(data);
    }

    /**
     * Sets the selected reservation index.
     *
     * @param index the index of the selected reservation.
     * @see ViewHotelPanel#setSelectedReservationIndex
     */
    public void setSelectedReservationIndex(int index) {
        this.viewHotelPanel.setSelectedReservationIndex(index);
    }

    /**
     * Sets the visibility of the reservation data panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see SimulateBookingPanel#setDetailsVisible
     */
    public void setReservationDataVisible(boolean visible) {
        this.viewHotelPanel.setReservationDataVisible(visible);
    }

    /** Clears the reservation list selection. */
    public void clearReservationListSelection() {
        this.viewHotelPanel.clearReservationListSelection();
    }
}
