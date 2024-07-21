package src.controller.gui;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;

public class HotelListListener implements ListSelectionListener {
    ReservationSystem reservationSystem;
    TopView view;

    public HotelListListener(ReservationSystem reservationSystem,
            TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
        this.updateHotelList();
    }

    public void updateHotelList() {
        view.setHotelListData(reservationSystem.getHotelNamesAsList());
        if (reservationSystem.getHotelCount() == 0) {
            view.setTopMenuPaneVisible(false);
        } else {
            view.setTopMenuPaneVisible(true);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return; // repeated selection; ignore
        }
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        int selectedIndex = lsm.getMinSelectionIndex();
        int hotelCount = reservationSystem.getHotelCount();
        if (selectedIndex == hotelCount) {
            // Selected "Add hotel" button
            String name = view.promptAddHotel();
            if (reservationSystem.addHotel(name)) {
                view.resetState();
                this.updateHotelList();
                view.setHotelListSelectedIndex(selectedIndex); // redundant but
                                                               // gives a nice
                                                               // blue selection
                                                               // highlight
            } else if (reservationSystem.getHotelCount() > 0) {
                view.setHotelListSelectedIndex(
                        view.getHotelListPrevSelectedIndex());
                if (name != null) { // user did not cancel
                    view.showHotelNameExistsError();
                }
            } else {
                view.removeHotelListSelection();
            }
        }
        else if (selectedIndex >= 0) {
            // Selected an existing hotel: initialize default values
            Hotel hotel = reservationSystem.getHotel(selectedIndex);

            // View hotel
            view.setHotelDataText(hotel.toString());
            view.setHotelListPrevSelectedIndex(selectedIndex);
            view.updateRoomList(
                    reservationSystem.getHotel(selectedIndex).getRoomNames());

            // Manage hotel
            view.setRenameHotelText(hotel.getName());
            view.setUpdateBasePriceText(String.valueOf(hotel.getBasePrice()));
            for (int date = 1; date <= 31; date++) {
                view.setManagePricesCalendarText(
                        date,
                        String.format(
                                "%d: %.2f",
                                date,
                                hotel.getPriceModifierOnNight(date)));
            }
        }
    }

}
