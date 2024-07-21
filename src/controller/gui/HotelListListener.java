package src.controller.gui;

import src.model.Hotel;
import src.model.ReservationSystem;
import src.view.gui.TopView;

public class HotelListListener extends ListAddListener {
    public HotelListListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
        this.reservationSystem = reservationSystem;
        this.view = view;
        this.updateList();
    }

    /** {@inheritDoc} */
    @Override
    protected int getListLength() {
        return reservationSystem.getHotelCount();
    }

    /** {@inheritDoc} */
    @Override
    public void updateList() {
        view.setHotelListData(reservationSystem.getHotelNamesAsList());
        if (reservationSystem.getHotelCount() == 0)
            view.setTopMenuPaneVisible(false);
        else
            view.setTopMenuPaneVisible(true);
    }

    /** {@inheritDoc} Prompts the user to add a hotel to the list. */
    @Override
    protected void addToList(int selectedIndex) {
        String name = view.promptAddHotel();
        if (reservationSystem.addHotel(name)) {
            view.resetState();
            this.updateList();
            /* Redundant but gives a nice blue selection highlight */
            view.setHotelListSelectedIndex(selectedIndex);
        } else if (reservationSystem.getHotelCount() > 0) {
            view.setHotelListSelectedIndex(
                    view.getHotelListPrevSelectedIndex());
            /* The user did not cancel */
            if (name != null)
                view.showHotelNameExistsError();
        } else
            view.removeHotelListSelection();
    }

    /** {@inheritDoc} Displays hotel information and updates the right panel. */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* Set default values in GUI */
        Hotel hotel = reservationSystem.getHotel(selectedIndex);

        /* View Hotel */
        view.setHotelDataText(hotel.toString());
        view.setHotelListPrevSelectedIndex(selectedIndex);
        view.updateRoomList(
                reservationSystem.getHotel(selectedIndex).getRoomNames());

        /* Manage Hotel */
        view.setRenameHotelText(hotel.getName());
        view.setUpdateBasePriceText(String.valueOf(hotel.getBasePrice()));
        for (int day = 1; day <= 31; day++)
            view.setManagePricesCalendarText(
                    day,
                    String.format(
                            "%d: %.2f",
                            day,
                            hotel.getPriceModifierOnNight(day)));
    }
}
