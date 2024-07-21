package src.controller.gui;

import javax.swing.event.ListSelectionEvent;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;

public class RoomListListener extends ListAddListener {

    public RoomListListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);

    }

    public void updateRoomData(int index) {
        this.view.updateRoomData(
                reservationSystem.getHotel(view.getHotelListSelectedIndex())
                        .getRoomString(index));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        updateRoomData(e.getFirstIndex());
        System.out.println(e.getFirstIndex());
    }

    @Override
    public void updateList() {
        Hotel hotel = reservationSystem.getHotel(
                view.getHotelListSelectedIndex());
        if (hotel != null) {
            view.updateRoomList(
                    reservationSystem.getHotel(
                            view.getHotelListSelectedIndex()).getRoomNames());
        }
    }

    @Override
    protected void updateDataPanel(int selectedIndex) {

    }

    @Override
    protected void addToList(int selectedIndex) {

    }

    @Override
    protected int getListLength() {
        return reservationSystem.getHotel(getListLength()).getRoomCount();
    }
}
