package src.controller.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;

public class RoomListListener extends ListAddListener {

    public RoomListListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);

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
        this.view.updateRoomData(
                reservationSystem.getHotel(view.getHotelListSelectedIndex())
                        .getRoomString(selectedIndex));
    }

    @Override
    protected void addToList(int selectedIndex) {

    }

    @Override
    protected int getListLength() {
        return reservationSystem.getHotel(view.getHotelListSelectedIndex()).getRoomCount();
    }
}
