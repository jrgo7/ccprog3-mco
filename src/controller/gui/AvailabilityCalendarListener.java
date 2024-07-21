package src.controller.gui;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;

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
    protected void handlePressEnterKey(int row, int col) {

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
