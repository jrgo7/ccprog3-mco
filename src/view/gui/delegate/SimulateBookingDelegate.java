package src.view.gui.delegate;

import java.awt.Point;
import java.util.ArrayList;

import src.model.Room;
import src.view.gui.TopView;
import src.view.gui.component.BookingCalendarRenderer;
import src.view.gui.panel.SimulateBookingPanel;

/**
 * Represents a delegate class that assists {@link TopView} in passing data to
 * its composite {@link SimulateBookingPanel}.
 */
public class SimulateBookingDelegate {
    /** The panel that this delegate communicates with. */
    private SimulateBookingPanel simulateBookingPanel;

    /**
     * Initializes the panel.
     * 
     * @param simulateBookingPanel the panel tied to this delegate
     */
    public SimulateBookingDelegate(SimulateBookingPanel simulateBookingPanel) {
        this.simulateBookingPanel = simulateBookingPanel;
    }

    /**
     * Sets which dates are selectable in the booking calendar.
     *
     * @param dates an {@link ArrayList} of available dates. Usually obtained
     *              with {@link Room#getAvailableDates()}
     * @see SimulateBookingPanel#setCalendarAvailability
     */
    public void setBookingCalendarAvailability(ArrayList<Integer> dates) {
        this.simulateBookingPanel.setCalendarAvailability(dates);
    }

    /**
     * Sets the check-in date for a reservation.
     *
     * @param date the check-in date.
     * @see SimulateBookingPanel#setCalendarCheckIn
     */
    public void setBookingCalendarCheckIn(int date) {
        this.simulateBookingPanel.setCalendarCheckIn(date);
    }

    /**
     * Sets the check-out date for a reservation.
     *
     * @param date the check-out date.
     * @see SimulateBookingPanel#setCalendarCheckOut
     */
    public void setBookingCalendarCheckOut(int date) {
        this.simulateBookingPanel.setCalendarCheckOut(date);
    }

    /**
     * {@return the string content of the guest name input field}
     *
     * @see SimulateBookingPanel#getGuestNameFieldText
     */
    public String getBookingGuestName() {
        return this.simulateBookingPanel.getGuestNameFieldText();
    }

    /**
     * {@return the index of the selected room}
     *
     * @see SimulateBookingPanel#getRoomIndex
     */
    public int getBookingRoomIndex() {
        return this.simulateBookingPanel.getRoomIndex();
    }

    /**
     * Clears the room list selection
     *
     * @see SimulateBookingPanel#clearRoomListSelection
     */
    public void resetBookingRoomListSelection() {
        this.simulateBookingPanel.clearRoomListSelection();
    }

    /**
     * {@return the string content of the discount code input field}
     *
     * @see SimulateBookingPanel#getDiscountCodeFieldText
     */
    public String getBookingDiscountCode() {
        return this.simulateBookingPanel.getDiscountCodeFieldText();
    }

    /**
     * Sets the text in the reservation data preview panel.
     *
     * @param text the text to set
     * @see SimulateBookingPanel#setPreview
     */
    public void updateSimulateBookingReservationPreview(String text) {
        this.simulateBookingPanel.setPreview(text);
    }

    /**
     * Checks if the booking calendar is under mouse focus.
     *
     * @return {@code true} if the calendar is under mouse focus, {@code false}
     *         otherwise
     * @see SimulateBookingPanel#getIsCalendarFocused
     */
    public boolean getIsBookingCalendarFocused() {
        return this.simulateBookingPanel.getIsCalendarFocused();
    }

    /**
     * {@return the row index within the bookking calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see SimulateBookingPanel#getBookingCalendarRowFromMouse
     */
    public int getBookingCalendarRowFromMouse(Point point) {
        return this.simulateBookingPanel.getBookingCalendarRowFromMouse(point);
    }

    /**
     * {@return the column index within the bookking calendar given a point
     * position}
     *
     * @param point the point to check, usually representing the mouse location
     * @see SimulateBookingPanel#getBookingCalendarColFromMouse
     */
    public int getBookingCalendarColFromMouse(Point point) {
        return this.simulateBookingPanel.getBookingCalendarColFromMouse(point);
    }

    /**
     * Clears all input ffields for the booking.
     *
     * @see SimulateBookingPanel#setGuestNameFieldText
     * @see SimulateBookingPanel#setDiscountCodeFieldText
     * @see SimulateBookingPanel#enableCheckInButton
     */
    public void resetBookingFields() {
        this.simulateBookingPanel.setGuestNameFieldText("");
        this.simulateBookingPanel.setDiscountCodeFieldText("");
        this.simulateBookingPanel.enableCheckInButton();
    }

    /**
     * Clears the booking calendar selection.
     *
     * @see SimulateBookingPanel#clearCalendarSelection
     */
    public void resetBookingCalendarSelection() {
        this.simulateBookingPanel.clearCalendarSelection();
    }

    /**
     * Sets the visibility of the booking details panel.
     *
     * @param visible {@code true} to display the panel, {@code false} otherwise
     * @see SimulateBookingPanel#setDetailsVisible
     */
    public void setBookingDetailsVisible(boolean visible) {
        this.simulateBookingPanel.setDetailsVisible(visible);
    }

    /**
     * Clears thhe entire Simulate Booking screen.
     *
     * @see #resetBookingFields
     * @see #resetBookingRoomListSelection
     * @see #updateSimulateBookingReservationPreview
     * @see #setBookingCalendarCheckIn
     * @see #setBookingCalendarCheckOut
     */
    public void resetBookingScreen() {
        this.resetBookingFields();
        this.resetBookingRoomListSelection();
        this.updateSimulateBookingReservationPreview(
                SimulateBookingPanel.RESERVATION_PREVIEW_INITIAL_TEXT);
        this.setBookingCalendarCheckIn(BookingCalendarRenderer.NONE);
        this.setBookingCalendarCheckOut(BookingCalendarRenderer.NONE);
    }
}
