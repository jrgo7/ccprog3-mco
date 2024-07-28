package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.component.HotelListPanel;

/**
 * Represents an event listener that handles actions received by a
 * {@link HotelListPanel}.
 * 
 * @see ListAddListener
 */
public class HotelListListener extends ListAddListener
        implements ActionListener {
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
        this.view.setList(reservationSystem.getHotelNamesAsList());
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

        // * Global updates
        this.view.setSelectedIndex(selectedIndex);
        this.view.setRoomList(
                reservationSystem.getRoomNames(selectedIndex));
        this.view.setReservationList(
                reservationSystem.getReservationNames(selectedIndex));

        // * View hotel-specific updates
        this.view.getViewHotelDelegate().setHotelData(
                reservationSystem.getHotelString(selectedIndex));

        // * Manage hotel-specific updates
        this.view.getManageHotelDelegate().setRenameHotelFieldText(
                reservationSystem.getHotelName(selectedIndex));
        this.view.getManageHotelDelegate().setUpdateBasePriceFieldText(
                String.valueOf(
                        reservationSystem.getBasePrice(selectedIndex)));
        for (int date = 1; date <= 31; date++)
            this.view.getManageHotelDelegate().setManagePricesCalendarText(
                    date,
                    String.format(
                            "%d: %.2f",
                            date,
                            reservationSystem.getPriceModifier(
                                    selectedIndex,
                                    date)));

        // * Simulate booking-specific updates
        view.getSimulateBookingDelegate().resetBookingScreen();
        reservationSystem.resetReservationBuilder();
        reservationSystem.getReservationBuilder().setHotelIndex(selectedIndex);
    }

    /** {@inheritDoc} Prompts the user to add a hotel to the list. */
    @Override
    protected void addToList(int selectedIndex) {
        /* Exit if selected index is invalid */
        if (selectedIndex < 0)
            return;

        System.out.println("Index " + selectedIndex);
        /* Prompts the user to enter a hotel name */
        String name = this.view.promptAddHotel();

        if (reservationSystem.addHotel(name)) {
            /* Refreshing the view after each update keeps it synchronized */
            view.getViewHotelDelegate().clearAvailabilityCalendarSelection();
            view.getViewHotelDelegate().setHotelAvailability("<p></p>");
            this.updateList();
            // /* Prevents the selection highlight from disappearing */
            this.view.setSelectedIndex(selectedIndex);
        } else {
            // Did not add a hotel successfully.
            if (name != null) {
                /// User did not cancel
                this.view.showHotelNameExistsError();
            }
            this.view.clearSelectedIndex();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.confirmAction("remove this hotel?", "Remove hotel")) {
            return;
        }
        this.reservationSystem.removeHotel(this.view.getSelectedIndex());
        this.updateList();
        this.view.clearSelectedIndex();
    }
}
