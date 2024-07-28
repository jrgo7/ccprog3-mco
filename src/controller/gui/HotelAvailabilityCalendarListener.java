package src.controller.gui;

import java.awt.event.MouseEvent;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;

/**
 * Extends {@link CalendarListener} with functionality to handle checking the
 * availability of a hotel (i.e., number of available rooms) on selected dates.
 */
public class HotelAvailabilityCalendarListener extends CalendarListener {
    /**
     * Initialize this listener.
     * 
     * @param reservationSystem the {@link ReservationSystem} to bind to this
     *                          listener
     * @param view              the {@link TopView} to bind to this listener
     */
    public HotelAvailabilityCalendarListener(
            ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    /**
     * Checks for and updates the hotel's availability data on a selected date.
     * 
     * @param row the {@link Calendar} row associated with the current action
     * @param col the {@link Calendar} column associated with the current action
     * @see #handleSelected(int, int)
     * @see #handleClicked(int, int)
     * @see #handleDragged(int, int)
     */
    public void handleCheckAvailability(int row, int col) {
        int index = view.getSelectedIndex();
        int date = Calendar.toDate(row, col);

        /* Exit if selection is out of bounds */
        if (date > 31 || date < 1) {
            view.getViewHotelDelegate().setHotelAvailability("<p></p>");
            return;
        }

        boolean isOneRoom = reservationSystem.getAvailableRoomCount(index,
                date) == 1;

        view.getViewHotelDelegate().setHotelAvailability(
                String.format("""
                        <h2>Day %d</h2>
                        <ul>
                        <li>Reservations: %d</li>
                        <li>Rooms available: %d room%s.</li>
                        </ul>""",
                        date,
                        reservationSystem.getReservationCountOnDate(index, date,
                                false),
                        reservationSystem.getAvailableRoomCount(index, date),
                        isOneRoom ? "" : "s"));
    }

    /** {@inheritDoc} Updates the hotel's availability information. */
    @Override
    protected void handleSelected(int row, int col) {
        this.handleCheckAvailability(row, col);
    }

    /** {@inheritDoc} Treated as if the selection was updated. */
    @Override
    protected void handleClicked(int row, int col) {
        this.handleCheckAvailability(row, col);
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    protected void handleReleased(int row, int col) {
        /* Implementation left blank */
    }

    /** {@inheritDoc} Clears the calendar selection. */
    @Override
    protected void handleReleasedOutsideComponent() {
        this.view.getViewHotelDelegate().clearAvailabilityCalendarSelection();
    }

    /** {@inheritDoc} Treated as if the selection was updated. */
    @Override
    protected void handleDragged(int row, int col) {
        this.handleCheckAvailability(row, col);
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    protected void handleMoved(int row, int col) {
        /* Implementation left blank */
    }

    /** {@inheritDoc} Sets the calendar selection. */
    @Override
    protected void setRowAndCol(MouseEvent e) {
        this.setRow(view.getViewHotelDelegate()
                .getAvailabilityCalendarRowFromMouse(e.getPoint()));
        this.setCol(view.getViewHotelDelegate()
                .getAvailabilityCalendarColFromMouse(e.getPoint()));
    }
}
