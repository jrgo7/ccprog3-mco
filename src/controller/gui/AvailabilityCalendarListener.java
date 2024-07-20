package src.controller.gui;

import java.awt.event.MouseEvent;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.Calendar;
import src.view.gui.TopView;

public class AvailabilityCalendarListener extends CalendarListener {

    public AvailabilityCalendarListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
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
    protected void handleEntered(int row, int col) {

    }

    @Override
    protected void handleExited(int row, int col) {

    }

    @Override
    protected void handleReleased(int row, int col) {
    }

    protected void handleReleasedOutsideComponent() {
        view.resetAvailabilityCalendarSelection();
    }

    @Override
    protected void handleDragged(int row, int col) {
        handleCheckAvailability(row, col);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // So CalendarListener.mouseMoved does not overwrite row, col

    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        // So CalendarListener.mouseEntered does not overwrite row, col

    }

    @Override
    protected void handleMoved(int row, int col) {

    }

    public void handleCheckAvailability(int row, int col) {
        Hotel hotel = reservationSystem.getHotel(view.getHotelListSelectedIndex());
        if (hotel == null) {
            return;
        }
        int day = Calendar.toDay(row, col);
        if (day > 31 || day < 1) {
            view.setHotelAvailabilityDataText("<p></p>");
            return; // Block invalid input
        }

        boolean isOneRoom = hotel.getAvailableRoomCount(day) == 1;
        view.setHotelAvailabilityDataText(
                String.format("""
                        <div style="font-family: sans-serif">
                        <h2>Day %d</h2>
                        <ul>
                        <li>Reservations: %d</li>
                        <li>Rooms available: %d room%s.</li>
                        </ul>
                        </div>
                        """,
                        day,
                        hotel.getReservationCountOnDate(day, false),
                        hotel.getAvailableRoomCount(day),
                        isOneRoom ? "" : "s"));
    }

}
