package src.controller.gui;

import javax.swing.event.ListSelectionEvent;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class RoomListListener extends ListAddListener {

    public RoomListListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);

    }

    public void updateRoomData(int index) {
        this.view.updateRoomData(
                reservationSystem.getRoomString(view.getHotelListSelectedIndex(), index));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        updateDataPanel(view.getViewRoomSelectedIndex());
        System.out.println(view.getViewRoomSelectedIndex());
    }

    @Override
    public void updateList() {
        view.updateRoomList(
                reservationSystem.getRoomNames(view.getHotelListSelectedIndex()));
    }

    @Override
    protected void updateDataPanel(int selectedIndex) {
        this.view.updateRoomData(
                reservationSystem.getRoomString(
                    view.getHotelListSelectedIndex(), selectedIndex));
    }

    @Override
    protected void addToList(int selectedIndex) {

    }

    @Override
    protected int getListLength() {
        return reservationSystem.getRoomCount(view.getHotelListSelectedIndex());
    }
}
