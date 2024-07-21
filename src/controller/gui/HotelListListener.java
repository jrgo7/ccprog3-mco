package src.controller.gui;

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

        /* View Hotel */
        view.setHotelDataText(reservationSystem.getHotelString(selectedIndex));
        view.setHotelListPrevSelectedIndex(selectedIndex);
        view.updateRoomList(
                reservationSystem.getRoomNames(selectedIndex));

        /* Manage Hotel */
        view.setRenameHotelText(reservationSystem.getHotelName(selectedIndex));
        view.setUpdateBasePriceText(
                String.valueOf(
                        reservationSystem.getBasePrice(selectedIndex)));
        for (int date = 1; date <= 31; date++)
            view.setManagePricesCalendarText(
                    date,
                    String.format(
                            "%d: %.2f",
                            date,
                            reservationSystem.getPriceModifier(selectedIndex, date)));
    }
}
