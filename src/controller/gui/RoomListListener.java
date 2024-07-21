package src.controller.gui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class RoomListListener implements ListSelectionListener {
    private ReservationSystem reservationSystem;
    private TopView view;

    /* TODO: Can this inherit from ListAddListener or something like that */
    public RoomListListener(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        // updateRoomData(0);

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
}
