package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;

/**
 * Represents an event listener that handles actions in the rename hotel
 * feature.
 */
public class RenameHotelListener implements ActionListener, KeyListener {
    /** The reservation system tied to this listener. */
    private ReservationSystem reservationSystem;

    /** The view tied to this listener. */
    private TopView view;

    /**
     * Initialize this listener.
     * 
     * @param reservationSystem the {@link ReservationSystem} to bind to this
     *                          listener
     * @param view              the {@link TopView} to bind to this listener
     */
    public RenameHotelListener(ReservationSystem reservationSystem,
            TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    /** {@inheritDoc} Renames the hotel when the button is clicked. */
    @Override
    public void actionPerformed(ActionEvent e) {
        renameHotel();
    }

    /**
     * Renames the hotel.
     * 
     * @see ReservationSystem#renameHotel(int, String)
     */
    public void renameHotel() {
        String newName = view.getManageHotelDelegate()
                .getRenameHotelFieldText();
        if (!view.confirmAction(
                "rename this hotel to " + newName, "Rename hotel")) {
            return;
        }

        int index = view.getSelectedIndex();
        if (reservationSystem.renameHotel(index, newName)) {
            view.setList(reservationSystem.getHotelNamesAsList());
            view.setSelectedIndex(index);
        } else {
            view.showHotelNameExistsError();
        }
    }

    /** {@inheritDoc} Renames the hotel when enter is pressed. */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            renameHotel();
        }
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    public void keyTyped(KeyEvent e) {
        /* Implementation left blank */
    }

    /** No behavior is defined for this implementation. {@inheritDoc} */
    @Override
    public void keyReleased(KeyEvent e) {
        /* Implementation left blank */
    }
}
