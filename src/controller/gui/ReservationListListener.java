package src.controller.gui;

import src.model.ReservationSystem;
import src.view.gui.TopView;

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

        view.updateReservationList(
                reservationSystem.getReservationNames(hotelIndex));

        this.view.getManageHotelDelegate().setManageReservationVisible(reservationSystem.getReservationCount(hotelIndex) > 0);
    }

    /** {@inheritDoc} Equal to the number of rooms in the selected hotel. */
    @Override
    protected int getListLength() {
        /* Exit if selected index is invalid */
        int hotelIndex = this.view.getSelectedIndex();
        if (hotelIndex < 0)
            return 0;

        return reservationSystem.getReservationCount(hotelIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void handleValueChanged(int selectedIndex) {
        this.updateDataPanel(selectedIndex);
        this.updateList();
    }

    /** {@inheritDoc} */
    @Override
    protected void updateDataPanel(int selectedIndex) {
        /* Exit if selected index is invalid */
        if (selectedIndex < 0)
            return;

        int hotelIndex = view.getSelectedIndex();

        if (hotelIndex < 0)
            hotelIndex = 0;
        this.view.getViewHotelDelegate().setReservationData(
                reservationSystem.getReservationString(hotelIndex,
                        selectedIndex));
    }

    /**
     * {@inheritDoc} Method implementation left blank as no special behavior is
     * defined.
     */
    @Override
    protected void addToList(int selectedIndex) {
        /*
         * Method implementation left blank as no special behavior is defined.
         */
    }
}
