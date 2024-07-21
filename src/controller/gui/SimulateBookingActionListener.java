package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class SimulateBookingActionListener implements ActionListener {
    private ReservationSystem reservationSystem;
    private TopView view;

    public SimulateBookingActionListener(
            ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    // Use these methods when building the reservation, perhaps from other classes
    // view.getHotelListSelectedIndex(),
    // view.getBookingGuestName(),
    // view.getBookingCheckIn(),
    // view.getBookingCheckOut(),
    // view.getBookingRoomIndex(),
    // view.getBookingDiscountCode()

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean status = reservationSystem.addReservation(
                reservationSystem.getReservationBuilder());
        if (status) {
            // view.showBookingSuccess();
        }

        // int status = reservationSystem.addReservation(
        //         reservationSystem.getReservationBuilder());
        // switch (status) {
        //     case Hotel.BOOKING_SUCCESS:
        //         view.showBookingSuccess();
        //         view.resetBookingScreen();
        //         break;
        //     default:
        //         view.showBookingError();
        //         break;
        // }
    }

}
