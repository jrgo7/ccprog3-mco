package src.controller.gui;

import java.awt.event.MouseEvent;
import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;

/**
 * An extension of {@link CalendarListener} with functionality to handle
 * checking the availability of a hotel (i.e., number of available rooms) on
 * selected dates.
 */
public class HotelAvailabilityCalendarListener extends CalendarListener {

    /** Initialize this listener. */
    public HotelAvailabilityCalendarListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    public void handleCheckAvailability(int row, int col) {
        int index = view.getSelectedIndex();
        int date = Calendar.toDate(row, col);
        if (date > 31 || date < 1) {
            view.getViewHotelDelegate().setHotelAvailability("<p></p>");
            return; // Block invalid input
        }

        boolean isOneRoom = reservationSystem.getAvailableRoomCount(index, date) == 1;
        view.getViewHotelDelegate().setHotelAvailability(
                String.format("""
                        <h2>Day %d</h2>
                        <ul>
                        <li>Reservations: %d</li>
                        <li>Rooms available: %d room%s.</li>
                        </ul>""",
                        date,
                        reservationSystem.getReservationCountOnDate(index, date, false),
                        reservationSystem.getAvailableRoomCount(index, date),
                        isOneRoom ? "" : "s"));
    }

    @Override
    protected void handleSelected(int row, int col) {
        handleCheckAvailability(row, col);
    }

    @Override
    protected void handleClicked(int row, int col) {
        handleCheckAvailability(row, col);
    }

    @Override
    protected void handleReleased(int row, int col) {
        /** No special behavior */
    }

    @Override
    protected void handleReleasedOutsideComponent() {
        view.getViewHotelDelegate().resetAvailabilityCalendarSelection();
    }

    @Override
    protected void handleDragged(int row, int col) {
        handleCheckAvailability(row, col);
    }

    @Override
    protected void handleMoved(int row, int col) {
        /** No special behavior */
    }

    @Override
    protected void setRowAndCol(MouseEvent e) {
        this.setRow(view.getViewHotelDelegate().getAvailabilityCalendarRowFromMouse(e.getPoint()));
        this.setCol(view.getViewHotelDelegate().getAvailabilityCalendarColFromMouse(e.getPoint()));
    }

}
