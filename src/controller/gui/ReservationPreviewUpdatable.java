package src.controller.gui;

import src.model.ReservationBuilder;
import src.model.ReservationSystem;
import src.view.gui.TopView;

public interface ReservationPreviewUpdatable {
    /**
     * Update the reservation preview.
     */
    public default void updateReservationPreview(
            ReservationSystem reservationSystem, TopView view) {
        view.getSimulateBookingDelegate()
                .updateSimulateBookingReservationPreview(
                        reservationSystem.getReservationBuilderString());

        view.getSimulateBookingDelegate().setBookingCalendarAvailability(
                reservationSystem.getAvailableDatesForRoom(
                        view.getSelectedIndex(),
                        view.getSimulateBookingDelegate()
                                .getBookingRoomIndex()));

        ReservationBuilder builder = reservationSystem.getReservationBuilder();
        view.getSimulateBookingDelegate()
                .setBookingCalendarCheckIn(builder.getCheckIn());
        view.getSimulateBookingDelegate()
                .setBookingCalendarCheckOut(builder.getCheckOut());
    }
}
