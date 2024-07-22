package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class SimulateBookingRoomListListener extends RoomListListener {
    public SimulateBookingRoomListListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    @Override
    public void updateDataPanel(int index) {
        view.setSimulateBookingCalendarAvailability(
                reservationSystem.getAvailableDatesForRoom(
                        view.getHotelListSelectedIndex(), index));
    }
}
