package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.panel.ManageHotelPanel;

/**
 * Extends the functions of {@link RoomListListener} to listen to events from a
 * {@link ManageHotelPanel}.
 */
public class ManageRoomListener extends RoomListListener
        implements ActionListener {
    /**
     * Initialize this listener.
     * 
     * @param reservationSystem the {@link ReservationSystem} to bind to this
     *                          listener
     * @param view              the {@link TopView} to bind to this listener
     */
    public ManageRoomListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    /**
     * {@inheritDoc} Adds a room to the selected hotel. Note that a hotel can
     * only have a maximum of 50 rooms at once.
     * 
     * @see ReservationSystem#addRooms(int, int, int)
     * @see Hotel#addRooms(int, int)
     * @see TopView#promptAddRoom(int)
     */
    @Override
    protected void addToList(int selectedIndex) {
        /*
         * TODO: Maybe remove selectedIndex parameter here and instead fetch it
         * from View? Not sure if ListSelectionModel does some funny things with
         * the selection index
         */
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getSelectedIndex();
        if (hotelIndex < 0)
            return;

        /* A hotel can have at most 50 rooms */
        int limit = 50 - this.getListLength();
        if (limit <= 0) {
            view.showRoomCountFullError();
        } else {
            /* Result is returned as an integer array `{amount, type}` */
            int[] result = view.promptAddRoom(limit);
            if (result[0] != -1) {
                reservationSystem.addRooms(hotelIndex, result[1],
                        result[0] + 1);
                updateList();
            } else {
                /* The user cancelled */
                this.view.getManageHotelDelegate().clearSelectedRoomIndex();
            }
        }
    }

    /**
     * {@inheritDoc} Displays a string representation of the room data.
     * 
     * @see ReservationSystem#getRoomString(int, int)
     */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* Exit if selected index is invalid */
        if (selectedIndex < 0) {
            this.view.getViewHotelDelegate().setRoomDataVisible(false);
            return;
        }

        int hotelIndex = view.getSelectedIndex();
        this.view.getManageHotelDelegate().setManageRoomVisible(true);

        this.view.getManageHotelDelegate().setRoomData(
                reservationSystem.getRoomString(hotelIndex, selectedIndex),
                reservationSystem.getAvailableDatesForRoom(hotelIndex,
                        selectedIndex));
    }

    /**
     * {@inheritDoc} If the new selection is the last index, then the
     * {@code Add hotel...} option was selected.
     */
    @Override
    protected void handleValueChanged(int selectedIndex) {
        if (selectedIndex == this.getListLength())
            this.addToList(selectedIndex);
        else if (selectedIndex >= 0)
            this.updateDataPanel(selectedIndex);
    };

    /**
     * {@inheritDoc} Corresponds to the button for removing a room being
     * clicked. Note that a hotel is required to always have at least one room.
     * 
     * @see ReservationSystem#removeRoom(int, int)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.confirmAction("remove this room?", "Remove room")) {
            return;
        }

        int roomIndex = view.getManageHotelDelegate().getSelectedRoomIndex();
        int hotelIndex = view.getSelectedIndex();

        switch (this.reservationSystem.removeRoom(hotelIndex, roomIndex)) {
            case Hotel.REMOVE_ROOM_ONLY_ROOM:
                view.showCantRemoveOnlyRoomError();
                break;
            case Hotel.REMOVE_ROOM_RESERVATIONS_EXIST:
                view.showCantRemoveRoomWithReservationsError();
                break;
        }

        this.updateList();

        /* Always clear selection and hide panel afterwards */
        this.view.getManageHotelDelegate().clearSelectedRoomIndex();
        this.view.getManageHotelDelegate().setManageRoomVisible(false);
    }
}
