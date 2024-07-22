package src.controller.gui;

import javax.swing.event.ListSelectionEvent;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class ReservationListListener extends ListAddListener {
    /** Initializes the listener and updates the list */
    public ReservationListListener(ReservationSystem reservationSystem, TopView view) {
        super(reservationSystem, view);
    }

    /** {@inheritDoc} */
    @Override
    public void valueChanged(ListSelectionEvent e) {
        /* Exit if selected value is still */
        if (e.getValueIsAdjusting())
            return;

        updateDataPanel(view.getViewReservationSelectedIndex());
        System.out.println(view.getViewReservationSelectedIndex());
    }

    /** {@inheritDoc} */
    @Override
    public void updateList() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getHotelListSelectedIndex();
        if (hotelIndex < 0)
            return;

        view.updateReservationList(
                reservationSystem.getReservationNames(hotelIndex));
    }

    /** {@inheritDoc} */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* Exit if selected index is invalid */
        if (selectedIndex < 0)
            return;

        int hotelIndex = view.getHotelListSelectedIndex();

        if (hotelIndex < 0)
            hotelIndex = 0;
        this.view.setReservationData(
                reservationSystem.getReservationString(hotelIndex, selectedIndex));
    }

    /** {@inheritDoc} */
    @Override
    protected void addToList(int selectedIndex) {
        /*
         * Method implementation left blank as there is no special behavior for
         * ViewRoomLists in this case.
         */
    }

    /** {@inheritDoc} Equal to the number of rooms in the selected hotel. */
    @Override
    protected int getListLength() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getHotelListSelectedIndex();
        if (hotelIndex < 0)
            return 0;

        return reservationSystem.getReservationCount(hotelIndex);
    }
}
