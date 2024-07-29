package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;
import src.view.gui.subpanel.ManageReservationsPanel;

/**
 * Extends the functions of {@link ReservationListListener} to listen to events
 * from a {@link ManageReservationsPanel}.
 */
public class ManageReservationListener extends ReservationListListener
        implements ActionListener {
    /**
     * Initialize this listener.
     * 
     * @param reservationSystem the {@link ReservationSystem} to bind to this
     *                          listener
     * @param view              the {@link TopView} to bind to this listener
     */
    public ManageReservationListener(ReservationSystem reservationSystem,
            TopView view) {
        super(reservationSystem, view);
    }

    /**
     * {@inheritDoc} Corresponds to the button for removing a reservation being
     * clicked.
     * 
     * @see ReservationSystem#removeReservation(int, int)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!view.confirmAction("remove this reservation?",
                "Remove reservation")) {
            return;
        }

        int reservationIndex = view.getManageHotelDelegate()
                .getSelectedReservationIndex();
        int hotelIndex = view.getSelectedIndex();

        this.reservationSystem.removeReservation(hotelIndex, reservationIndex);
        this.updateList();
        this.view.getManageHotelDelegate().clearReservationListSelection();
        this.view.getManageHotelDelegate().setReservationDataVisible(false);

        this.view.getViewHotelDelegate().clearReservationListSelection();
        this.view.getViewHotelDelegate().setReservationDataVisible(false);
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
}
