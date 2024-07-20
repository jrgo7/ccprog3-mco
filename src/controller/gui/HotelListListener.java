package src.controller.gui;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class HotelListListener implements ListSelectionListener {
    ReservationSystem reservationSystem;
    TopView view;

    public HotelListListener(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        this.updateHotelList();
    }

    public void updateHotelList() {
        view.setHotelListData(reservationSystem.getHotelNamesAsList());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return; // repeated selection; ignore
        }
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        int selectedIndex = lsm.getMinSelectionIndex();
        int hotelCount = reservationSystem.getHotelCount();
        System.out.print(selectedIndex + " ");
        if (selectedIndex == hotelCount) {
            // Selected "Add hotel" button
            if (reservationSystem.addHotel(view.promptAddHotel())) {
                view.setTabIndex(TopView.VIEW_HOTEL_SCREEN);
                view.resetState();
                this.updateHotelList();
                view.setHotelListSelectedIndex(selectedIndex); // redundant but gives a nice blue selection
                                                               // highlight
            } else if (reservationSystem.getHotelCount() > 0) {
                view.setHotelListSelectedIndex(
                        view.getHotelListPrevSelectedIndex());
            } else {
                view.removeHotelListSelection();
            }
        }
        if (selectedIndex >= 0 && selectedIndex < hotelCount) {
            view.setHotelNameLabelText(
                    reservationSystem.getHotelNames()[selectedIndex]);
            view.setHotelDataText(
                    reservationSystem.getHotel(selectedIndex).toString());
            view.setHotelListPrevSelectedIndex(selectedIndex);
        }
    }

}
