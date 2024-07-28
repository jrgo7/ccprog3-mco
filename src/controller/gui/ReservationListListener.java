package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

/** Represents an event listener that handles actions on a reservation list. */
public class ReservationListListener extends ListAddListener {
    /** Initializes the listener and updates the list */
    public ReservationListListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    /** {@inheritDoc} */
    @Override
    public void updateList() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getSelectedIndex();
        if (hotelIndex < 0)
            return;

        view.setReservationList(
                reservationSystem.getReservationNames(hotelIndex));
    }

    /**
     * {@inheritDoc} Equal to the number of reservations in the selected hotel.
     */
    @Override
    protected int getListLength() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getSelectedIndex();
        if (hotelIndex < 0)
            return 0;

        return reservationSystem.getReservationCount(hotelIndex);
    }

    /**
     * {@inheritDoc} Always updates the data panel as there is no option to add
     * a reservation embedded within the list.
     */
    @Override
    protected void handleValueChanged(int selectedIndex) {
        this.updateDataPanel(selectedIndex);
    }

    /**
     * {@inheritDoc} Displays a string representation of the reservation data.
     * 
     * @see ReservationSystem#getReservationString(int, int)
     */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* Exit and hide the panel if there is no selection */
        if (selectedIndex < 0) {
            this.view.getManageHotelDelegate().setReservationDataVisible(false);
            return;
        }

        /* Show the panel */
        this.view.getManageHotelDelegate().setReservationDataVisible(true);

        int hotelIndex = view.getSelectedIndex();
        this.view.getManageHotelDelegate().setManageReservationData(
                reservationSystem.getReservationString(
                        hotelIndex, selectedIndex));
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    protected void addToList(int selectedIndex) {
        /* Implementation left blank */
    }
}
