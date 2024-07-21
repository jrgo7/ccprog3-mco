package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;

public class SimulateBookingActionListener implements ActionListener {
    private ReservationSystem reservationSystem;
    private TopView view;
    
    public  SimulateBookingActionListener(
        ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Hotel hotel = reservationSystem.getHotel(view.getHotelListSelectedIndex());
        hotel.addReservation(
            view.getBookingGuestName(),
            view.getBookingCheckIn(),
            view.getBookingCheckOut(),
            view.getBookingRoomIndex(),
            view.getBookingDiscountCode());
    }
    
}
