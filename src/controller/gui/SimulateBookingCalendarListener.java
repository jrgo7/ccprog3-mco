package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import src.model.Hotel;
import src.model.ReservationBuilder;
import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.Calendar;

/**
 * An extension of {@link CalendarListener} with functionality to handle
 * selecting check-in and check-out dates.
 */
public class SimulateBookingCalendarListener extends CalendarListener implements ActionListener {
    static final private int CHECK_IN = 0;
    static final private int CHECK_OUT = 1;
    private int mode = CHECK_IN;

    /** Initialize this listener. */
    public SimulateBookingCalendarListener(
            ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
    }

    /**
     * Update the reservation preview.
     */
    public void updateReservationPreview() {
        view.updateSimulateBookingReservationPreview(
                reservationSystem.getReservationBuilderString());

        view.setBookingCalendarAvailability(
                reservationSystem.getAvailableDatesForRoom(
                        view.getHotelListSelectedIndex(),
                        view.getBookingRoomIndex()));

        ReservationBuilder builder = reservationSystem.getReservationBuilder();
        view.setBookingCalendarCheckIn(builder.getCheckIn());
        view.setBookingCalendarCheckOut(builder.getCheckOut());
    }

    private void resetBookingScreen() {
        this.mode = CHECK_IN;
        view.resetBookingScreen();
        reservationSystem.resetReservationBuilder();
    }

    private void selectedDate(int row, int col) {
        ReservationBuilder builder = reservationSystem.getReservationBuilder();
        int date = Calendar.toDate(row, col);
        if (mode == CHECK_IN) {
            builder.setCheckIn(date);
        } else {
            builder.setCheckOut(date);
        }
    }

    public void submitReservation() {
        int hotelIndex = view.getHotelListSelectedIndex();

        if (hotelIndex < 0)
            return;

        int result = reservationSystem.addReservation(
                reservationSystem.getReservationBuilder());
        switch (result) {
            case Hotel.RESERVATION_SUCCESS:
                view.showReservationSuccess();
                view.updateReservationList(
                        reservationSystem.getReservationNames(hotelIndex));
                resetBookingScreen();
                break;
            case Hotel.RESERVATION_ERROR_INVALID_TIME:
                view.showReservationError("Invalid time chosen.");
                break;
            case Hotel.RESERVATION_ERROR_INVALID_ROOM:
                view.showReservationError("Invalid room chosen.");
                break;
            case Hotel.RESERVATION_ERROR_UNAVAILABLE_ROOM:
                view.showReservationError("Room chosen is unavailable.");
                break;
            case Hotel.RESERVATION_ERROR_INVALID_DISCOUNT_CODE:
                view.showReservationError(
                        "Discount code \"" +
                                reservationSystem
                                        .getReservationBuilder().getDiscountCode()
                                +
                                "\" is invalid.");
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void setRowAndCol(MouseEvent e) {
        this.setRow(view.getBookingCalendarRowFromMouse(e.getPoint()));
        this.setCol(view.getBookingCalendarColFromMouse(e.getPoint()));
    }

    /** {@inheritDoc} */
    @Override
    protected void handleSelected(int row, int col) {
        this.selectedDate(row, col);
        this.updateReservationPreview();
    }

    /** {@inheritDoc} */
    @Override
    protected void handleClicked(int row, int col) {
        this.selectedDate(row, col);
        this.updateReservationPreview();
    }

    /** {@inheritDoc} */
    @Override
    protected void handleDragged(int row, int col) {
        this.selectedDate(row, col);
        this.updateReservationPreview();
    }

    /** {@inheritDoc} */
    @Override
    protected void handleMoved(int row, int col) {
        /** No special behavior */
    }

    @Override
    protected void handleReleased(int row, int col) {
        this.selectedDate(row, col);
        this.updateReservationPreview();
    }

    /** {@inheritDoc} */
    @Override
    protected void handleReleasedOutsideComponent() {
        view.resetBookingCalendarSelection();
        this.updateReservationPreview();
    }

    /**
     * If the user presses a key in the booking calendar, delegate to the
     * ancestor {@link CalendarListener} method to handle moving the selection.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (view.getIsBookingCalendarFocused()) {
            super.keyPressed(e);
        }
    }

    /**
     * Automatically update the reservation preview when the user types outside
     * the booking calendar, e.g. in the guest name and discount code fields.
     * 
     * ({@link #selectedDate()} and its callers deal with updating the
     * reservation preview when the booking calendar is focused.)
     * 
     * @param e {@link KeyEvent}, actual value ignored
     */
    @Override
    public void keyReleased(KeyEvent e) {
        ReservationBuilder builder = reservationSystem.getReservationBuilder();
        if (!view.getIsBookingCalendarFocused()) {
            builder.setGuestName(view.getBookingGuestName());
            builder.setDiscountCode(view.getBookingDiscountCode());
            this.updateReservationPreview();
        }
    }

    /**
     * Handle button presses.
     * 
     * @param e {@link ActionEvent}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Set check-in date")) {
            mode = CHECK_IN;
        } else if (command.equals("Set check-out date")) {
            mode = CHECK_OUT;
        } else if (command.equals("Reset booking fields")) {
            resetBookingScreen();
        } else if (command.equals("Confirm booking")) {
            submitReservation();
        }
    }
}
