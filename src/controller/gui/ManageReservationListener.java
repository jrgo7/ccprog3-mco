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
    /** Initializes the listener and updates the list */
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
        int reservationIndex = view.getManageHotelDelegate()
                .getSelectedReservationIndex();
        int hotelIndex = view.getSelectedIndex();

        /*
         * This normally cannot happen as the panel is hidden when there is no
         * selection. TODO: Remove?
         */
        if (reservationIndex < 0 || hotelIndex < 0)
            return;

        this.reservationSystem.removeReservation(hotelIndex, reservationIndex);

        this.updateList();

        this.view.getManageHotelDelegate().clearSelectedRoomIndex();
        this.view.getManageHotelDelegate().setManageRoomVisible(false);
    }
}
