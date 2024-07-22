package src.controller.gui;

import javax.swing.event.ListSelectionEvent;

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
    public void valueChanged(ListSelectionEvent e) {
        /* Exit if selected value is still */
        if (e.getValueIsAdjusting())
            return;

        updateDataPanel(view.getViewRoomSelectedIndex());
        System.out.println(view.getViewRoomSelectedIndex());
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
    protected void updateDataPanel(int selectedIndex) {
        /* Exit if selected index is invalid */
        if (selectedIndex < 0)
            return;

        int hotelIndex = view.getHotelListSelectedIndex();

        if (hotelIndex < 0)
            hotelIndex = 0;
        this.view.updateRoomData(
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
        int hotelIndex = this.view.getHotelListSelectedIndex();
        if (hotelIndex < 0)
            return 0;

        return reservationSystem.getRoomCount(view.getHotelListSelectedIndex());
    }
}
