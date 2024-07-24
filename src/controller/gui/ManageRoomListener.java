package src.controller.gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.subpanel.ManageRoomsPanel;

/**
 * Represents an event listener that handles actions received by a
 * {@link ManageRoomsPanel}.
 * 
 * @see ListAddListener
 */
public class ManageRoomListener extends RoomListListener implements ActionListener {
    /** Initializes the listener and updates the list */
    public ManageRoomListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
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

    @Override
    protected void updateDataPanelData(String data, ArrayList<Integer> availableDates) {
        this.view.updateManageRoomData(data, availableDates);
    }

    @Override
    protected void handleValueChanged(int selectedIndex) {
        if (selectedIndex == this.getListLength())
            this.addToList(selectedIndex);
        else if (selectedIndex >= 0)
            this.updateDataPanel(selectedIndex);
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        int roomIndex = view.getManageRoomSelectedIndex();
        int hotelIndex = view.getHotelListSelectedIndex();

        if (roomIndex < 0 || hotelIndex < 0)
            return;

            System.out.println(roomIndex);
        
        
        if (!this.reservationSystem.removeRoom(hotelIndex, roomIndex))
            view.showCantRemoveRoomError();    
        this.updateList();
        this.updateDataPanel(roomIndex);
        
        this.updateDataPanel(view.getManageRoomSelectedIndex());
    }
}
