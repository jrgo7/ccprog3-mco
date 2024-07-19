package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.Calendar;
import src.view.gui.TopView;

public class DatePickerCalendarListener extends CalendarListener {
    public DatePickerCalendarListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
    }

    @Override
    protected void handleSelected(int row, int col) {

    }

    @Override
    protected void handleEntered(int row, int col) {
        int day = Calendar.toDay(row, col);
        // Do something...
    }

    @Override
    protected void handleClicked(int row, int col) {
        int day =  Calendar.toDay(row, col);
        // Do something...
    }

    @Override
    protected void handleExited(int row, int col) {
    }

    @Override
    protected void handleReleased(int row, int col) {
    }

    @Override
    protected void handleDragged(int row, int col) {
    }

    @Override
    protected void handleMoved(int row, int col) {
    }
}
