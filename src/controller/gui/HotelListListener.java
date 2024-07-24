package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.HotelListPanel;

/**
 * Represents an event listener that handles actions received by a
 * {@link HotelListPanel}.
 * 
 * @see ListAddListener
 */
public class HotelListListener extends ListAddListener {
    /** Initializes the listener and updates the list */
    public HotelListListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
        this.reservationSystem = reservationSystem;
        this.view = view;
        this.updateList();
    }

    /** {@inheritDoc} */
    @Override
    public void updateList() {
        this.view.setHotelListData(reservationSystem.getHotelNamesAsList());

        /* Hide panel when there are no hotels in the system */
        if (reservationSystem.getHotelCount() == 0)
            this.view.setTopMenuPaneVisible(false);
        else
            this.view.setTopMenuPaneVisible(true);
    }

    /** {@inheritDoc} Equal to the number of hotels in the system. */
    @Override
    protected int getListLength() {
        return this.reservationSystem.getHotelCount();
    }

    /** {@inheritDoc} */
    @Override
    protected void handleValueChanged(int selectedIndex) {
        if (selectedIndex == this.getListLength())
            this.addToList(selectedIndex);
        else if (selectedIndex >= 0)
            this.updateDataPanel(selectedIndex);
    };

    /** {@inheritDoc} Updates panel to display hotel information. */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* Exit if selected index is invalid */
        if (selectedIndex < 0)
            return;

        /* Updates the list's fallback index */
        this.view.setHotelListPrevSelectedIndex(selectedIndex);

        /* Updates View Hotel panels */
        this.view.setHotelDataText(
                reservationSystem.getHotelString(selectedIndex));
        this.view.updateRoomList(
                reservationSystem.getRoomNames(selectedIndex));

        /* Updates Manage Hotel panels */
        this.view.setRenameHotelText(
                reservationSystem.getHotelName(selectedIndex));
        this.view.setUpdateBasePriceText(
                String.valueOf(
                        reservationSystem.getBasePrice(selectedIndex)));
                        
        for (int date = 1; date <= 31; date++)
            this.view.setManagePricesCalendarText(
                    date,
                    String.format(
                            "%d: %.2f",
                            date,
                            reservationSystem.getPriceModifier(selectedIndex,
                                    date)));
    }

    /** {@inheritDoc} Prompts the user to add a hotel to the list. */
    @Override
    protected void addToList(int selectedIndex) {
        /* Exit if selected index is invalid */
        if (selectedIndex < 0)
            return;

        /* Prompts the user to enter a hotel name */
        String name = this.view.promptAddHotel();

        if (reservationSystem.addHotel(name)) {
            /* Refreshing the view after each update keeps it synchronized */
            this.view.resetState();
            this.updateList();

            /* Prevents the selection highlight from disappearing */
            this.view.setHotelListSelectedIndex(selectedIndex);
        } else if (reservationSystem.getHotelCount() > 0) {
            this.view.setHotelListSelectedIndex(
                    this.view.getHotelListPrevSelectedIndex());

            /* The user did not cancel */
            if (name != null)
                this.view.showHotelNameExistsError();
        } else
            this.view.removeHotelListSelection();
    }
}
