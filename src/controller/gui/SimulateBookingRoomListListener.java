package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.panel.SimulateBookingPanel;

/**
 * Extends the functions of {@link RoomListListener} to listen to events from a
 * {@link SimulateBookingPanel}.
 */
public class SimulateBookingRoomListListener extends RoomListListener
        implements ReservationPreviewUpdatable {
    /**
     * Initialize this listener.
     * 
     * @param reservationSystem the {@link ReservationSystem} to bind to this
     *                          listener
     * @param view              the {@link TopView} to bind to this listener
     */
    public SimulateBookingRoomListListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    /** {@inheritDoc} */
    @Override
    public void updateDataPanel(int index) {
        int hotelIndex = view.getSelectedIndex();

        if (index < 0 || hotelIndex < 0)
            return;

        view.getSimulateBookingDelegate().setBookingCalendarAvailability(
                reservationSystem.getAvailableDatesForRoom(hotelIndex, index));
        view.getSimulateBookingDelegate().setBookingDetailsVisible(true);
        view.setReservationList(
                reservationSystem.getReservationNames(hotelIndex));

        /* Changes the selected room index in Simulate Booking Panel */
        reservationSystem.getReservationBuilder().setRoomIndex(index);

        this.updateReservationPreview(reservationSystem, view);
    }
}
