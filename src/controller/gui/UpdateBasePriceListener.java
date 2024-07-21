package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class UpdateBasePriceListener implements ActionListener, KeyListener {
    ReservationSystem reservationSystem;
    TopView view;

    public UpdateBasePriceListener(ReservationSystem reservationSystem,
            TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBasePrice();
    }

    public void updateBasePrice() {
        int index = view.getHotelListSelectedIndex(); // saved as setting the
                                                      // hotel list removes the
                                                      // selection
        try {
            double newBasePrice = Double
                    .parseDouble(view.getUpdateBasePriceText());
            if (!reservationSystem.getHotel(index).setBasePrice(newBasePrice)) {
                view.invalidBasePriceUpdateError();
            }
        } catch (NumberFormatException e) {
            view.invalidBasePriceUpdateError();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            updateBasePrice();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
