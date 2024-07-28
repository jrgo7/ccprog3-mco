package src.controller.gui;

import src.model.ReservationBuilder;
import src.model.ReservationSystem;
import src.view.gui.TopView;

/** Represents a method for updating the preview in the Simulate Booking tab. */
public interface ReservationPreviewUpdatable {
    /**
     * Update the reservation preview.
     * 
     * @param reservationSystem the {@link ReservationSystem} tied to the tab
     * @param view              the {@link TopView} tied to the tab
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
