package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.subpanel.ManageRoomsPanel;

/**
 * Represents an event listener that handles actions received by a
 * {@link ManageRoomsPanel}.
 * 
 * @see ListAddListener
 */
public class ManageRoomListener extends ListAddListener {
    /** Initializes the listener and updates the list */
    public ManageRoomListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    /**
     * {@inheritDoc} Equivalent to the number of rooms in the selected hotel.
     */
    @Override
    protected int getListLength() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getHotelListSelectedIndex();
        if (hotelIndex < 0)
            return 0;

        return reservationSystem.getRoomCount(hotelIndex);
    }

    /** {@inheritDoc} */
    @Override
    public void updateList() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getHotelListSelectedIndex();
        if (hotelIndex < 0)
            return;

        view.updateRoomList(
                reservationSystem.getRoomNames(hotelIndex));
    }

    /** {@inheritDoc} */
    @Override
    protected void addToList(int selectedIndex) {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getHotelListSelectedIndex();
        if (hotelIndex < 0)
            return;

        /* A hotel can have at most 50 rooms */
        int limit = 50 - this.getListLength();
        if (limit <= 0)
            view.showRoomCountFullError();
        else {
            /* Result is returned as an integer array `{amount, type}` */
            int[] result = view.promptAddRoom(limit);
            if (result[0] == -1)
                return;
            reservationSystem.addRooms(hotelIndex, result[1], result[0] + 1);
            updateList();
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* TODO */
    }
}
