package src.controller.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import src.model.ReservationSystem;
import src.view.gui.TopView;

public class RenameHotelListener implements ActionListener, KeyListener {
    ReservationSystem reservationSystem;
    TopView view;
    
    public RenameHotelListener(ReservationSystem reservationSystem, TopView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renameHotel();
    }

    public void renameHotel() {
        int index = view.getHotelListSelectedIndex(); // saved as setting the hotel list removes the selection
        if (reservationSystem.renameHotel(index, view.getRenameHotelText())) {
            view.setHotelListData(reservationSystem.getHotelNamesAsList());
            view.setHotelListSelectedIndex(index);
        } else {
            view.showHotelNameExistsError();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            renameHotel();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
