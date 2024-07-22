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
        int hotelIndex = view.getHotelListSelectedIndex();
        if (index < 0 || hotelIndex < 0)
            return;

        view.setBookingCalendarAvailability(
                reservationSystem.getAvailableDatesForRoom(
                        hotelIndex, index));
    }
}
