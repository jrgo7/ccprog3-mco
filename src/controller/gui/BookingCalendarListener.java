package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import src.model.ReservationBuilder;
import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;

public class BookingCalendarListener extends CalendarListener implements ActionListener {
    // TODO: Blank discount codes
    // TODO: Update the preview automatically (no button)
    // TODO: Reset button
    // TODO: Auto-reset on submission (and update the availability calendar)

    static final private int CHECK_IN = 0;
    static final private int CHECK_OUT = 1;
    private int mode = -1; // TODO Initialize to CHECK_IN and set the radio button

    public BookingCalendarListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
    }

    public void updateReservationPreview() {
        ReservationBuilder builder = reservationSystem.getReservationBuilder();
        builder.setGuestName(view.getBookingGuestName());
        builder.setDiscountCode(view.getBookingDiscountCode());
        builder.setRoomIndex(view.getBookingRoomIndex());
        builder.setHotelIndex(view.getHotelListSelectedIndex());
        updateBookingCalendar();
        view.updateSimulateBookingReservationPreview(
                reservationSystem.getReservationBuilderString());
    }

    public void updateBookingCalendar() {
        ReservationBuilder builder = reservationSystem.getReservationBuilder();
        int checkIn = builder.getCheckIn();
        int checkOut = builder.getCheckOut();
        view.setBookingCalendarAvailability(reservationSystem.getAvailableDatesForRoom(
                view.getHotelListSelectedIndex(), view.getBookingRoomIndex()));
        if (checkIn != -1)
            view.setBookingCalendarCheckIn(checkIn);
        if (checkOut != -1)
            view.setBookingCalendarCheckOut(checkOut);
    }

    @Override
    protected void setRowAndCol(MouseEvent e) {
        setRow(view.getBookingCalendarRowFromMouse(e.getPoint()));
        setCol(view.getBookingCalendarColFromMouse(e.getPoint()));
    }

    @Override
    protected void handleSelected(int row, int col) {
        selectedDate(row, col);
    }

    @Override
    protected void handleClicked(int row, int col) {
        selectedDate(row, col);
    }

    @Override
    protected void handlePressEnterKey(int row, int col) {

    }

    @Override
    protected void handleDragged(int row, int col) {
        selectedDate(row, col);
    }

    @Override
    protected void handleMoved(int row, int col) {

    }

    @Override
    protected void handleReleased(int row, int col) {
        selectedDate(row, col);
    }

    @Override
    protected void handleReleasedOutsideComponent() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println(command);
        if (command.equals("Set check-in date")) {
            mode = CHECK_IN;
        } else if (command.equals("Set check-out date")) {
            mode = CHECK_OUT;
        } else if (command.equals("Update preview")) {
            updateReservationPreview();
        } else if (command.equals("Book")) {
            submitReservation();
        }
    }

    private void selectedDate(int row, int col) {
        ReservationBuilder builder = reservationSystem.getReservationBuilder();
        int date = Calendar.toDate(row, col);
        if (mode == CHECK_IN) {
            System.out.println("Check-in date: " + date);
            builder.setCheckIn(date); // TODO validation -- get a return value
            updateBookingCalendar();
        } else if (mode == CHECK_OUT) {
            System.out.println("Check-out date: " + date);
            builder.setCheckOut(date); // TODO validation -- get a return value
            updateBookingCalendar();
        }
    }

    public void submitReservation() {
        if (reservationSystem.addReservation(
                reservationSystem.getReservationBuilder())) {
            view.showReservationSuccess();
        } else {
            view.showReservationError(); // * Normally shouldn't happen once we
                                         // * get validation running
        }

    }
}
