package src.controller.gui;

import java.util.ArrayList;
import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.subpanel.ViewRoomPanel;

/**
 * Represents an event listener that handles actions received by a
 * {@link ViewRoomPanel}.
 * 
 * @see ListAddListener
 */
public class RoomListListener extends ListAddListener {
    /** Initializes the listener and updates the list */
    public RoomListListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
    }

    /** {@inheritDoc} */
    @Override
    protected void handleValueChanged(int selectedIndex) {
        this.updateDataPanel(selectedIndex);
    }

    /** {@inheritDoc} */
    @Override
    public void updateList() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getSelectedIndex();
        if (hotelIndex < 0)
            return;

        view.updateRoomList(
                reservationSystem.getRoomNames(hotelIndex));
    }

    protected void updateDataPanelData(String data, ArrayList<Integer> availableDates) {
        this.view.getViewHotelDelegate().setRoomData(data, availableDates);
    }

    /** {@inheritDoc} */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* Exit if selected index is invalid */
        if (selectedIndex < 0) {
            this.view.getViewHotelDelegate().setRoomDataVisible(false);
            return;
        }

        int hotelIndex = view.getSelectedIndex();

        if (hotelIndex < 0)
            hotelIndex = 0;
        this.updateDataPanelData(
                reservationSystem.getRoomString(hotelIndex, selectedIndex),
                reservationSystem.getAvailableDatesForRoom(hotelIndex, selectedIndex));

    }

    /** {@inheritDoc} */
    @Override
    protected void addToList(int selectedIndex) {
        /*
         * Method implementation left blank as there is no special behavior for
         * ViewRoomLists in this case.
         */
    }

    /** {@inheritDoc} Equal to the number of rooms in the selected hotel. */
    @Override
    protected int getListLength() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getSelectedIndex();
        if (hotelIndex < 0)
            return 0;

        return reservationSystem.getRoomCount(view.getSelectedIndex());
    }
}
