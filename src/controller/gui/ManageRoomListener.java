package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class ManageRoomListener extends ListAddListener {
    public ManageRoomListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
    }

    /** {@inheritDoc} */
    @Override
    protected int getListLength() {
        return reservationSystem.getRoomCount(view.getHotelListSelectedIndex());
    }

    /** {@inheritDoc} */
    @Override
    public void updateList() {
        view.updateRoomList(reservationSystem.getRoomNames(view.getHotelListSelectedIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void addToList(int selectedIndex) {
        /* TODO: Refactor */
        int limit = 50 - this.getListLength();
        if (limit <= 0)
            view.showRoomCountFullError();
        else {
            int[] result = view.promptAddRoom(limit);
            reservationSystem.addRooms(view.getHotelListSelectedIndex(), result[1], result[0] + 1);
            updateList();
        }

    }

    /** {@inheritDoc} */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* TODO */
    }
}
