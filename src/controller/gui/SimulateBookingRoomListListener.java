package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class SimulateBookingRoomListListener extends RoomListListener {

    public SimulateBookingRoomListListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
    }

    @Override
    public void updateRoomData(int index) {
        view.setBookingCalendarAvailability(
                reservationSystem.getAvailableDatesForRoom(
                    view.getHotelListSelectedIndex(), index));
    }
}
